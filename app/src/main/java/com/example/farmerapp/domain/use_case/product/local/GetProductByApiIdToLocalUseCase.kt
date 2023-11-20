package com.example.farmerapp.domain.use_case.product.local

import com.example.farmerapp.domain.model.Product
import com.example.farmerapp.domain.repository.local.ProductRepository
import com.example.farmerapp.until.Resource
import com.example.farmerapp.until.extetensions.ProductExtensions.toProduct
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetProductByApiIdToLocalUseCase
@Inject constructor(
    private val productRepository: ProductRepository
) {
     fun getProductByApiIdToLocal(apiId: String) = flow<Resource<Product?>> {
        val productRelation = productRepository.selectProductByApiId(apiId)
        if (productRelation != null)
            emit(Resource.Success(productRelation.toProduct()))
        else
            emit(Resource.Success(null))
    }.catch { emit(Resource.Error(it.message!!)) }
}