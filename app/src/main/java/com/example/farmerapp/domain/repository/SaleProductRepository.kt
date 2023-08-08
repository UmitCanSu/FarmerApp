package com.example.farmerapp.domain.repository

import com.example.farmerapp.data.local.dto.SalesProductDto
import com.example.farmerapp.data.local.relations.SaleProductWitOtherClass
import com.example.farmerapp.domain.model.SalesProduct
import java.sql.Date

interface SaleProductRepository {
    suspend fun insertSaleProduct(salesProductDto: SalesProductDto):Long
    suspend fun updateSaleProduct(salesProductDto: SalesProductDto):Int
    suspend fun deleteSaleProduct(salesProductDto: SalesProductDto):Int
    suspend fun selectSaleProductWithId(saleProductId: Int): SaleProductWitOtherClass
    suspend fun selectSaleProductWithSaleDate(saleDate: String): List<SaleProductWitOtherClass>
}