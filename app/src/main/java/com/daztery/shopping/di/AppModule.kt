package com.daztery.shopping.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.daztery.shopping.data.local.dao.ProductDao
import com.daztery.shopping.data.local.database.AppDatabase
import com.daztery.shopping.data.repository.ProductRepositoryImpl
import com.daztery.shopping.domain.repository.ProductRepository
import com.daztery.shopping.domain.usecase.AddProductUseCase
import com.daztery.shopping.domain.usecase.DeleteProductUseCase
import com.daztery.shopping.domain.usecase.GetAllProductsUseCase
import com.daztery.shopping.domain.usecase.GetCartTotalUseCase
import com.daztery.shopping.domain.usecase.ProductUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
  
  @Provides
  @Singleton
  fun provideDatabase(application: Application): AppDatabase = Room.databaseBuilder(application,
    AppDatabase::class.java, "shopping_db").build()

  @Provides
  @Singleton
  fun provideProductDao(database: AppDatabase) = database.productDao()

  @Provides
  @Singleton
  fun provideProductRepository(dao: ProductDao): ProductRepository = ProductRepositoryImpl(dao)
  
  
  @Provides
  @Singleton
  fun provideProductUseCase(repository: ProductRepository) = ProductUseCase(
    addProduct = AddProductUseCase(repository),
    getAllProducts = GetAllProductsUseCase(repository),
    getCartTotal = GetCartTotalUseCase(repository),
    deleteProduct = DeleteProductUseCase(repository)
  )
}