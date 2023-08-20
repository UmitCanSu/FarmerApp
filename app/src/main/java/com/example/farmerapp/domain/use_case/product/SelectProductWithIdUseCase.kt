package com.example.farmerapp.domain.use_case.product

import com.example.farmerapp.domain.model.Product
import com.example.farmerapp.domain.repository.ProductRepository
import com.example.farmerapp.until.extetensions.ProductExtensions.toProduct
import com.example.farmerapp.until.Resource
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SelectProductWithIdUseCase
@Inject constructor(
    private val productRepository: ProductRepository
) {
    fun selectProductWithId(productId: Int) = flow<Resource<Product>> {
        emit(Resource.Loading())
        val productRelations = productRepository.selectProductWithId(productId)
        emit(Resource.Success(productRelations.toProduct()))
    }.catch { emit(Resource.Error(it.message!!)) }
}