package com.example.farmerapp.data.repository

import com.example.farmerapp.domain.model.Farmer
import com.example.farmerapp.domain.model.Product
import com.example.farmerapp.domain.repository.ProductRepository
import com.example.farmerapp.domain.repository.dao.ProductDao
import javax.inject.Inject

class ProductRepositoryImp
@Inject constructor(
    private val productDao: ProductDao
) : ProductRepository {
    override suspend fun insertProduct(product: Product) {
        return productDao.insertProduct(product)
    }

    override suspend fun updateProduct(product: Product) {
        return productDao.updateProduct(product)
    }

    override suspend fun deleteProduct(product: Product) {
        return productDao.deleteProduct(product)
    }

    override suspend fun selectProductWithId(productId: Int) {
        return productDao.selectProductWithId(productId)
    }

    override suspend fun selectFarmersWithCompanyId(companyId: Int): List<Farmer> {
        return productDao.selectFarmersWithCompanyId(companyId)
    }
}