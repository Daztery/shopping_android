package com.daztery.shopping.presentation.screen.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.daztery.shopping.domain.model.Product
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
  modifier: Modifier,
  viewModel: HomeViewModel = hiltViewModel()
) {
  val productList by viewModel.products.collectAsState()
  val total by viewModel.total.collectAsState()
  
  var name by remember { mutableStateOf("") }
  var priceText by remember { mutableStateOf("") }
  
  Column(
    modifier = modifier
      .padding(
        horizontal = 10.dp
      )
  ) {
    Text(
      text = "Total: S/. %.2f".format(total),
      style = MaterialTheme.typography.headlineMedium
    )
    
    Spacer(modifier = Modifier.height(16.dp))
    
    OutlinedTextField(
      value = name,
      onValueChange = { name = it },
      label = { Text("Nombre del producto") },
      modifier = Modifier.fillMaxWidth(),
      maxLines = 1
    )
    
    Spacer(modifier = Modifier.height(8.dp))
    
    OutlinedTextField(
      value = priceText,
      onValueChange = { newValue ->
        val filtered = newValue.filter { it.isDigit() || it == '.' }
        if (filtered.count { it == '.' } <= 1) {
          priceText = filtered
        }
      },
      label = { Text("Precio") },
      modifier = Modifier.fillMaxWidth(),
      maxLines = 1,
      singleLine = true,
      trailingIcon = { Text("S/.") },
      keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
    
    Spacer(modifier = Modifier.height(8.dp))
    
    Row(
      modifier = Modifier.align(Alignment.End)
    ) {
      if (!name.isBlank() && !priceText.isBlank()) {
        Button(
          onClick = {
            name = ""
            priceText = ""
          }
        ) {
          Text("Limpiar")
        }
      }
      
      Button(
        onClick = {
          val price = priceText.toDoubleOrNull()
          if (!name.isBlank() && price != null) {
            viewModel.addProduct(name, price)
            name = ""
            priceText = ""
          }
        },
        modifier = Modifier.padding(horizontal = 5.dp)
      ) {
        Text("Agregar")
      }
    }
    
    Spacer(modifier = Modifier.height(16.dp))
    
    if (productList.isEmpty()) {
      Text(
        text = "No hay productos agregados",
        textAlign = TextAlign.Center,
        modifier = Modifier
          .fillMaxWidth()
          .padding(vertical = 32.dp)
      )
    } else {
      LazyColumn(
        modifier = Modifier
          .weight(1f)
          .padding(4.dp)
      ) {
        items(productList, key = { it.id }) { product ->
          val visible = remember { mutableStateOf(true) }
          val shouldDelete = remember { mutableStateOf(false) }
          
          LaunchedEffect(shouldDelete.value) {
            if (shouldDelete.value) {
              kotlinx.coroutines.delay(300)
              viewModel.deleteProduct(product)
            }
          }
          
          AnimatedVisibility(
            visible = visible.value,
            modifier = Modifier.animateItemPlacement()
          ) {
            ProductItem(
              product = product,
              onDeleteClick = {
                visible.value = false
                shouldDelete.value = true
              }
            )
          }
        }
      }
    }
    
    
  }
}


@Composable
fun ProductItem(product: Product, onDeleteClick: (Product) -> Unit) {
  Card(
    modifier = Modifier
      .fillMaxWidth()
      .padding(vertical = 4.dp),
    elevation = CardDefaults.cardElevation(4.dp)
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(12.dp),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.SpaceBetween
    ) {
      Column(modifier = Modifier.weight(1f)) {
        Text(
          product.name,
          style = MaterialTheme.typography.titleMedium,
          fontStyle = FontStyle.Italic,
          modifier = Modifier.padding(bottom = 5.dp)
        )
        Text(
          "S/. %.2f".format(product.price),
          style = MaterialTheme.typography.bodyLarge,
          fontSize = 18.sp,
          fontWeight = FontWeight.Bold
        )
      }
      
      Button(
        onClick = { onDeleteClick(product) },
        modifier = Modifier.padding(start = 8.dp)
      ) {
        Text("Eliminar")
      }
    }
  }
}