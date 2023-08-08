package com.example.farmerapp.domain.repository.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.farmerapp.data.local.dto.SalesProductDto
import com.example.farmerapp.data.local.relations.SaleProductWitOtherClass
import com.example.farmerapp.domain.model.SalesProduct
import java.sql.Date


@Dao
interface SaleProductDao {
    @Insert
    suspend fun insertSaleProduct(salesProduct: SalesProductDto): Long

    @Update
    suspend fun updateSaleProduct(salesProduct: SalesProductDto): Int

    @Delete
    suspend fun deleteSaleProduct(salesProduct: SalesProductDto): Int

    @Transaction
    @Query("SELECT * FROM SALESPRODUCT WHERE id=:saleProductId")
    suspend fun selectSaleProductWithId(saleProductId: Int): SaleProductWitOtherClass

    @Transaction
    @Query("SELECT * FROM SALESPRODUCT WHERE salesDate like :saleDate")
    suspend fun selectSaleProductWithSaleDate(saleDate: String): List<SaleProductWitOtherClass>
}