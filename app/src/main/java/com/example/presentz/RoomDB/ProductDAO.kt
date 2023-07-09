package com.example.presentz.RoomDB

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ProductDAO {
    @Insert
    suspend fun insertProduct(product:ProductModel)

    @Delete
    suspend fun deleteProduct(product: ProductModel)

    @Query("SELECT * FROM products")
    fun getAllProducts():LiveData<List<ProductModel>>

    @Query("SELECT * FROM products WHERE productId =:id")
    fun isExist(id: String): ProductModel
}