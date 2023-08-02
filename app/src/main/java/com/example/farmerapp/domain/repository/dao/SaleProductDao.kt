package com.example.farmerapp.domain.repository.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.farmerapp.data.dto.SalesProductDto
import com.example.farmerapp.domain.model.SalesProduct
import java.sql.Date


@Dao
interface SaleProductDao {
    @Insert
    suspend fun insertSaleProduct(salesProduct: SalesProduct)

    @Update
    suspend fun updateSaleProduct(salesProduct: SalesProduct)

    @Delete
    suspend fun deleteSaleProduct(salesProduct: SalesProduct)

    @Query("SELECT * FROM SALESPRODUCT WHERE id=:saleProductId")
    suspend fun selectSaleProductWithId(saleProductId: Int): SalesProductDto
    @Query("SELECT * FROM SALESPRODUCT WHERE salesDate like salesDate")
    suspend fun selectSaleProductWithSaleDate(saleDate: Date): List<SalesProductDto>
}