package com.example.farmerapp.domain.use_case.product

import com.example.farmerapp.domain.model.Product
import com.example.farmerapp.domain.repository.local.ProductRepository
import com.example.farmerapp.until.extetensions.ProductExtensions.toProductDto
import com.example.farmerapp.until.Resource
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UpdateProductUseCase
@Inject constructor(
    private val productRepository: ProductRepository
) {
    fun updateRroduct(product: Product) = flow<Resource<Boolean>> {
        emit(Resource.Loading())
        val updatedProduct = productRepository.updateProduct(product.toProductDto())
        emit(Resource.Success(updatedProduct > 0))
    }.catch { emit(Resource.Error(it.message!!)) }
}