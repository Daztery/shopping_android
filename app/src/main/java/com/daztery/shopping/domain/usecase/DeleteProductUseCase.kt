package com.daztery.shopping.domain.usecase

import com.daztery.shopping.domain.model.Product
import com.daztery.shopping.domain.repository.ProductRepository
import javax.inject.Inject

class DeleteProductUseCase @Inject constructor(private val repository: ProductRepository) {
  suspend operator fun invoke(product: Product) {
    repository.deleteProduct(product)
  }
}