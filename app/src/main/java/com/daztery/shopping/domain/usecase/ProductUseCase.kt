package com.daztery.shopping.domain.usecase

data class ProductUseCase(
  val addProduct: AddProductUseCase,
  val getAllProducts: GetAllProductsUseCase,
  val getCartTotal: GetCartTotalUseCase,
  val deleteProduct: DeleteProductUseCase
)
