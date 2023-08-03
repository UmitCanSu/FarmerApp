package com.example.farmerapp.domain.use_case.product

import com.example.farmerapp.domain.model.Product
import com.example.farmerapp.domain.repository.ProductRepository
import com.example.farmerapp.domain.repository.SaleProductRepository
import com.example.farmerapp.until.Resource
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class InsertProduct
@Inject constructor(
    private val productRepository: ProductRepository
) {
    fun insertProdcut(product: Product) = flow<Resource<Boolean>> {
        emit(Resource.Loading())
        val insertedSize = productRepository.insertProduct(product)
        emit(Resource.Success(insertedSize > 0))
    }.catch { emit(Resource.Error(it.message)) }
}