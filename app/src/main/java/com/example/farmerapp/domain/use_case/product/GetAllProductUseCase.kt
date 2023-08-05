package com.example.farmerapp.domain.use_case.product

import com.example.farmerapp.domain.model.Product
import com.example.farmerapp.domain.repository.ProductRepository
import com.example.farmerapp.until.Extetensions.ProductExtensions.toProduct
import com.example.farmerapp.until.Resource
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllProductUseCase
@Inject constructor(
    private val productRepository: ProductRepository
) {
    fun getAllProduct() = flow<Resource<List<Product>>> {
        emit(Resource.Loading())
        val allProduct = productRepository.getAllProductList().map { productRelations ->
            productRelations.toProduct()
        }
        emit(Resource.Success(allProduct))
    }.catch { emit(Resource.Error(it.message)) }
}