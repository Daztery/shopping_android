package com.daztery.shopping.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.daztery.shopping.domain.model.Product
import com.daztery.shopping.domain.usecase.ProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
  private val useCase: ProductUseCase
) : ViewModel() {
  private val _products = MutableStateFlow<List<Product>>(emptyList())
  val products: StateFlow<List<Product>> = _products.asStateFlow()
  
  private val _total = MutableStateFlow(0.0)
  val total: StateFlow<Double> = _total.asStateFlow()
  
  init {
    observeProducts()
    observeTotal()
  }
  
  private fun observeProducts() {
    viewModelScope.launch {
      useCase.getAllProducts().collect {
        _products.value = it
      }
    }
  }
  
  
  private fun observeTotal() {
    viewModelScope.launch {
      useCase.getCartTotal().collect {
        _total.value = it
      }
    }
  }
  
  fun addProduct(name: String, price: Double) {
    viewModelScope.launch {
      val product = Product(name = name, price = price)
      useCase.addProduct(product)
    }
  }
  
  fun deleteProduct(product: Product) {
    viewModelScope.launch {
      useCase.deleteProduct(product)
    }
  }
  
}