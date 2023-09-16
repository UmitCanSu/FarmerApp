package com.example.farmerapp.data.local.repository

import com.example.farmerapp.data.local.dto.SalesProductDto
import com.example.farmerapp.data.local.relations.SaleProductWitOtherClass
import com.example.farmerapp.domain.repository.local.SaleProductRepository
import com.example.farmerapp.domain.repository.dao.SaleProductDao
import java.time.LocalDate
import javax.inject.Inject

class SaleProductRepositoryImp
@Inject constructor(
    private val salesProductDao: SaleProductDao
) : SaleProductRepository {
    override suspend fun insertSaleProduct(salesProductDto: SalesProductDto): Long {
        return salesProductDao.insertSaleProduct(salesProductDto)
    }

    override suspend fun updateSaleProduct(salesProductDto: SalesProductDto): Int {
        return salesProductDao.updateSaleProduct(salesProductDto)
    }

    override suspend fun deleteSaleProduct(salesProductDto: SalesProductDto): Int {
        return salesProductDao.deleteSaleProduct(salesProductDto)
    }

    override suspend fun selectSaleProductWithId(saleProductId: Int): SaleProductWitOtherClass {
        return salesProductDao.selectSaleProductWithId(saleProductId)
    }

    override suspend fun selectSaleProductWithSaleDate(saleDate: String): List<SaleProductWitOtherClass> {
        return salesProductDao.selectSaleProductWithSaleDate(saleDate)
    }

    override suspend fun selectSalesProductWithCompanyId(companyId: Int): List<SaleProductWitOtherClass> {
        return salesProductDao.selectSalesProductWithCompanyId(companyId)
    }

    override suspend fun selectSalesProductFilter(
        companyId: Int,
        customerId: Int,
        farmerId: Int,
        productId: Int,
        isPaid: Boolean,
        startDate: LocalDate,
        endDate: LocalDate
    ): List<SaleProductWitOtherClass> {
        return salesProductDao.selectSalesProductFilter(
            companyId,
            customerId,
            farmerId,
            productId,
            isPaid,
        )
    }
}