package com.example.farmerapp.domain.repository.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.farmerapp.data.local.dto.SalesProductDto
import com.example.farmerapp.data.local.relations.SaleProductWitOtherClass


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


    @Query("SELECT * FROM SALESPRODUCT WHERE salesDate like :saleDate")
    @Transaction
    suspend fun selectSaleProductWithSaleDate(saleDate: String): List<SaleProductWitOtherClass>


    @Query("SELECT * FROM SalesProduct WHERE companyId=:companyId order by id desc ")
    @Transaction
    suspend fun selectSalesProductWithCompanyId(companyId: Int): List<SaleProductWitOtherClass>

    @Query("SELECT * FROM SalesProduct WHERE customerId=:customerId order by id desc ")
    @Transaction
    suspend fun selectSalesProductWithCustomerId(customerId: Int): List<SaleProductWitOtherClass>

    @Query(
        "SELECT * FROM SalesProduct WHERE " +
                "companyId=:companyId " +
                "and customerId=:customerId " +
                "and farmerId=:farmerId " +
                "and productId=:productId " +
                "and isPaid =:isPaid " +
                //"and salesDate BETWEEN :startDate and :endDate " +
                "order by id desc "
    )
    @Transaction
    suspend fun selectSalesProductFilter(
        companyId: Int,
        customerId: Int,
        farmerId: Int,
        productId: Int,
        isPaid: Boolean
    ): List<SaleProductWitOtherClass>

}