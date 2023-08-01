package com.example.farmerapp.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.farmerapp.model.Farmer
import com.example.farmerapp.model.Product

@Dao
interface ProductDao {
    @Insert
    fun insertProduct(product: Product)

    @Update
    fun updateProduct(product: Product)

    @Delete
    fun deleteProduct(product: Product)

    @Query("Select * from Product where id=:productId")
    fun selectProductWithId(productId: Int)

    @Query("Select * from  Product where id=:companyId ")
    fun selectFarmersWithCompanyId(companyId: Int): List<Farmer>
}