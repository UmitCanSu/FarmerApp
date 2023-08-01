package com.example.farmerapp.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.farmerapp.model.SalesProduct
import java.sql.Date


@Dao
interface SaleProductDao {
    @Insert
    fun insertSaleProduct(salesProduct: SalesProduct)

    @Update
    fun updateSaleProduct(salesProduct: SalesProduct)

    @Delete
    fun deleteSaleProduct(salesProduct: SalesProduct)

    @Query("Select * from SalesProduct where id=:saleProductId")
    fun selectSaleProductWithId(saleProductId: Int)

    @Query("Select * from SalesProduct where salesDate=:saleDate")
    fun selectSaleProductWithSaleDate(saleDate: Date): String
}