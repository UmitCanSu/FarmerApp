package com.example.farmerapp.domain.use_case.product

import com.example.farmerapp.domain.model.Product
import com.example.farmerapp.domain.repository.ProductRepository
import com.example.farmerapp.until.Extetensions.ProductExtensions.toProduct
import com.example.farmerapp.until.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetProductListWithCompanyIdUseCase
@Inject constructor(
    private val productRepository: ProductRepository
) {

    fun getProductListWithCompanyId(companyId: Int) = flow<Resource<List<Product>>> {
        emit(Resource.Loading())
        val productList = productRepository.selectFarmersWithCompanyId(companyId)
            .map { productRepository ->
                productRepository.toProduct()
            }
        emit(Resource.Success(productList))

    }
        .catch { emit(Resource.Error(it.message!!)) }
        .flowOn(Dispatchers.IO)


}