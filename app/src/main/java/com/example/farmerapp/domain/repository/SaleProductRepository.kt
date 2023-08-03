package com.example.farmerapp.domain.repository

import com.example.farmerapp.data.local.dto.SalesProductDto
import com.example.farmerapp.data.local.relations.SaleProductWitOtherClass
import com.example.farmerapp.domain.model.SalesProduct
import java.sql.Date

interface SaleProductRepository {
    suspend fun insertSaleProduct(salesProduct: SalesProduct)
    suspend fun updateSaleProduct(salesProduct: SalesProduct)
    suspend fun deleteSaleProduct(salesProduct: SalesProduct)
    suspend fun selectSaleProductWithId(saleProductId: Int): SaleProductWitOtherClass
    suspend fun selectSaleProductWithSaleDate(saleDate: Date): List<SaleProductWitOtherClass>
}