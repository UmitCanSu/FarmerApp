package com.example.farmerapp.data.local.repository

import com.example.farmerapp.data.local.dto.ProductDto
import com.example.farmerapp.data.local.relations.ProductRelations
import com.example.farmerapp.domain.repository.room.ProductRepository
import com.example.farmerapp.domain.repository.dao.ProductDao
import javax.inject.Inject

class ProductRepositoryImp
@Inject constructor(
    private val productDao: ProductDao
) : ProductRepository {
    override suspend fun insertProduct(productDto: ProductDto): Long {
        return productDao.insertProduct(productDto)
    }

    override suspend fun updateProduct(productDto: ProductDto): Int {
        return productDao.updateProduct(productDto)
    }

    override suspend fun deleteProduct(productDto: ProductDto): Int {
        return productDao.deleteProduct(productDto)
    }

    override suspend fun selectProductWithId(productId: Int): ProductRelations {
        return productDao.selectProductWithId(productId)
    }

    override suspend fun selectFarmersWithCompanyId(companyId: Int): List<ProductRelations> {
        return productDao.selectFarmersWithCompanyId(companyId)
    }

    override suspend fun getAllProductList(): List<ProductRelations> {
        return productDao.getAllProductList()
    }

}