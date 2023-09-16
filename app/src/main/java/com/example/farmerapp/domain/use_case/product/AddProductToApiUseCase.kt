package com.example.farmerapp.domain.use_case.product

import com.example.farmerapp.domain.model.Product
import com.example.farmerapp.domain.repository.remote.ProductApiRepository
import com.example.farmerapp.until.Resource
import com.example.farmerapp.until.extetensions.ProductExtensions.toProductApiDto
import com.google.gson.Gson
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AddProductToApiUseCase
@Inject constructor(
    private val productApiRepository: ProductApiRepository
) {
    fun addProduct(product: Product) = flow<Resource<Boolean>> {
        emit(Resource.Loading())
        val json = Gson().toJson(product.toProductApiDto())
        val getProduct = productApiRepository.addProduct(json)
        emit(Resource.Success(getProduct != null))
    }.catch { emit(Resource.Error(it.message!!)) }
}