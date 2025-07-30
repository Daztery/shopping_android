package com.daztery.shopping.data.mapper

import com.daztery.shopping.data.local.entity.ProductEntity
import com.daztery.shopping.domain.model.Product

fun ProductEntity.toDomain(): Product = Product(id = id, name = name, price = price)
fun Product.toEntity(): ProductEntity = ProductEntity(id = id, name = name, price = price)