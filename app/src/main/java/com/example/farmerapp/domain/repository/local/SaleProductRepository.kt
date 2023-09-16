package com.example.farmerapp.domain.repository.local

import com.example.farmerapp.data.local.dto.SalesProductDto
import com.example.farmerapp.data.local.relations.SaleProductWitOtherClass
import java.time.LocalDate

interface SaleProductRepository {
    suspend fun insertSaleProduct(salesProductDto: SalesProductDto): Long
    suspend fun updateSaleProduct(salesProductDto: SalesProductDto): Int
    suspend fun deleteSaleProduct(salesProductDto: SalesProductDto): Int
    suspend fun selectSaleProductWithId(saleProductId: Int): SaleProductWitOtherClass
    suspend fun selectSaleProductWithSaleDate(saleDate: String): List<SaleProductWitOtherClass>
    suspend fun selectSalesProductWithCompanyId(companyId: Int): List<SaleProductWitOtherClass>
    suspend fun selectSalesProductFilter(
        companyId: Int,
        customerId: Int,
        farmerId: Int,
        productId: Int,
        isPaid: Boolean,
        startDate: LocalDate,
        endDate: LocalDate
    ): List<SaleProductWitOtherClass>
}