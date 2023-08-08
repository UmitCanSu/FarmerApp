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
    override suspend fun insertSaleProduct(salesProductDto: SalesProductDto):Long {
        return salesProductDao.insertSaleProduct(salesProductDto)
    }

    override suspend fun updateSaleProduct(salesProductDto: SalesProductDto):Int {
        return salesProductDao.updateSaleProduct(salesProductDto)
    }

    override suspend fun deleteSaleProduct(salesProductDto: SalesProductDto):Int {
        return salesProductDao.deleteSaleProduct(salesProductDto)
    }

    override suspend fun selectSaleProductWithId(saleProductId: Int): SaleProductWitOtherClass {
        return salesProductDao.selectSaleProductWithId(saleProductId)
    }

    override suspend fun selectSaleProductWithSaleDate(saleDate: String): List<SaleProductWitOtherClass> {
        return salesProductDao.selectSaleProductWithSaleDate(saleDate)
    }
}