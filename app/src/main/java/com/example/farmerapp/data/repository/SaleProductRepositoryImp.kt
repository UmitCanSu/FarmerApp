package com.example.farmerapp.data.repository

import com.example.farmerapp.data.local.dto.SalesProductDto
import com.example.farmerapp.data.local.relations.SaleProductWitOtherClass
import com.example.farmerapp.domain.model.SalesProduct
import com.example.farmerapp.domain.repository.SaleProductRepository
import com.example.farmerapp.domain.repository.dao.SaleProductDao
import java.sql.Date
import javax.inject.Inject

class SaleProductRepositoryImp
@Inject constructor(
    private val salesProductDao: SaleProductDao
) : SaleProductRepository {
    override suspend fun insertSaleProduct(salesProduct: SalesProduct) {
        return salesProductDao.insertSaleProduct(salesProduct)
    }

    override suspend fun updateSaleProduct(salesProduct: SalesProduct) {
        return salesProductDao.updateSaleProduct(salesProduct)
    }

    override suspend fun deleteSaleProduct(salesProduct: SalesProduct) {
        return salesProductDao.deleteSaleProduct(salesProduct)
    }

    override suspend fun selectSaleProductWithId(saleProductId: Int): SaleProductWitOtherClass {
        return salesProductDao.selectSaleProductWithId(saleProductId)
    }

    override suspend fun selectSaleProductWithSaleDate(saleDate: Date): List<SaleProductWitOtherClass> {
        return salesProductDao.selectSaleProductWithSaleDate(saleDate)
    }
}