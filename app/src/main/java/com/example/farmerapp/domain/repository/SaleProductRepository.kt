package com.example.farmerapp.domain.repository

import com.example.farmerapp.data.dto.SalesProductDto
import com.example.farmerapp.domain.model.SalesProduct
import java.sql.Date

interface SaleProductRepository {
    suspend fun insertSaleProduct(salesProduct: SalesProduct)
    suspend fun updateSaleProduct(salesProduct: SalesProduct)
    suspend fun deleteSaleProduct(salesProduct: SalesProduct)
    suspend fun selectSaleProductWithId(saleProductId: Int):SalesProductDto
    suspend fun selectSaleProductWithSaleDate(saleDate: Date): List<SalesProductDto>
}