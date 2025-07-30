package com.daztery.shopping.domain.repository

import com.daztery.shopping.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
  suspend fun insertProduct(product: Product)
  suspend fun deleteProduct(product: Product)
  fun getAllProducts(): Flow<List<Product>>
  fun getTotalPrice(): Flow<Double>
}