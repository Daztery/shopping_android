package com.daztery.shopping.domain.usecase

import com.daztery.shopping.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCartTotalUseCase @Inject constructor(private val repository: ProductRepository) {
  operator fun invoke(): Flow<Double> = repository.getTotalPrice()
}