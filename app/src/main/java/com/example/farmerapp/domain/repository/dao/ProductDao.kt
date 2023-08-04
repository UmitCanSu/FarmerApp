package com.example.farmerapp.domain.repository.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.farmerapp.data.local.dto.ProductDto
import com.example.farmerapp.domain.model.Farmer
import com.example.farmerapp.domain.model.Product

@Dao
interface ProductDao {
    @Insert
    suspend   fun insertProduct(product: ProductDto):Long

    @Update
    suspend fun updateProduct(product: ProductDto):Int

    @Delete
    suspend fun deleteProduct(product: ProductDto):Int

    @Query("Select * from Product where id=:productId")
    suspend fun selectProductWithId(productId: Int):Product

    @Query("Select * from  Product where id=:companyId ")
    suspend fun selectFarmersWithCompanyId(companyId: Int): List<Farmer>
}