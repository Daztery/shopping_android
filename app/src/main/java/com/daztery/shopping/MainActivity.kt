package com.daztery.shopping

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.daztery.shopping.presentation.screen.home.HomeScreen
import com.daztery.shopping.ui.theme.ShoppingTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      ShoppingTheme {
        Scaffold { innerPadding ->
          HomeScreen(modifier = Modifier.padding(innerPadding))
        }
      }
    }
  }
}

