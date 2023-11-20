package com.example.farmerapp.domain.use_case.product.local

import com.example.farmerapp.domain.model.Product
import com.example.farmerapp.domain.repository.local.ProductRepository
import com.example.farmerapp.until.Resource
import com.example.farmerapp.until.extetensions.ProductExtensions.toProductDto
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class InsertProductUseCase
@Inject constructor(
    private val productRepository: ProductRepository
) {
    fun insertProdcut(product: Product) = flow<Resource<Boolean>> {
        emit(Resource.Loading())
        val insertedSize = productRepository.insertProduct(product.toProductDto())
        emit(Resource.Success(insertedSize > 0))
    }.catch { emit(Resource.Error(it.message!!)) }
}