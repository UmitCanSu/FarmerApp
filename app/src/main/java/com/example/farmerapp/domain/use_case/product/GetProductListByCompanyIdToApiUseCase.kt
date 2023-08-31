package com.example.farmerapp.domain.use_case.product

import com.example.farmerapp.domain.model.Product
import com.example.farmerapp.domain.repository.api.repository.ProductApiRepository
import com.example.farmerapp.until.Resource
import com.example.farmerapp.until.extetensions.ProductExtensions.toProduct
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetProductListByCompanyIdToApiUseCase
@Inject constructor(
    private val productApiRepository: ProductApiRepository
) {

    fun getProductList(companyId: String) = flow<Resource<List<Product>>> {
        emit(Resource.Loading())
        val products: List<Product> =
            productApiRepository.getProductListByCompanyId(companyId).map {
                it.toProduct()
            }
        emit(Resource.Success(products))
    }.catch { emit(Resource.Error(it.message!!)) }
}