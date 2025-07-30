package com.daztery.shopping.data.repository

import com.daztery.shopping.data.local.dao.ProductDao
import com.daztery.shopping.data.mapper.toDomain
import com.daztery.shopping.data.mapper.toEntity
import com.daztery.shopping.domain.model.Product
import com.daztery.shopping.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor (private val dao: ProductDao) : ProductRepository {
  override suspend fun insertProduct(product: Product) {
    dao.insertProduct(product.toEntity())
  }
  
  override suspend fun deleteProduct(product: Product) {
    dao.deleteProduct(product.toEntity())
  }
  
  override fun getAllProducts(): Flow<List<Product>> {
    return dao.getAllProducts().map { list -> list.map { it.toDomain() } }
  }
  
  override fun getTotalPrice(): Flow<Double> {
    return dao.getTotalPrice()
  }
  
}