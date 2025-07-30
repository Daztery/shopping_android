package com.daztery.shopping.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.daztery.shopping.data.local.entity.ProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertProduct(product: ProductEntity)
  
  @Delete
  suspend fun deleteProduct(product: ProductEntity)
  
  @Query("SELECT * FROM products")
  fun getAllProducts(): Flow<List<ProductEntity>>
  
  @Query("SELECT SUM(price) FROM products")
  fun getTotalPrice(): Flow<Double>
  
}