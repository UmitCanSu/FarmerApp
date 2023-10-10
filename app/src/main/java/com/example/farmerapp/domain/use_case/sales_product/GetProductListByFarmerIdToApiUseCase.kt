package com.example.farmerapp.domain.use_case.sales_product

import com.example.farmerapp.data.remote.api.ProductApi
import com.example.farmerapp.domain.model.Product
import com.example.farmerapp.until.Resource
import com.example.farmerapp.until.extetensions.ProductExtensions.toProduct
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetProductListByFarmerIdToApiUseCase
@Inject constructor(
    private val productApi: ProductApi
) {
    fun getProductListByFarmerIdToApiUseCase(farmerId: String) =
        flow<Resource<List<Product>>> {
            emit(Resource.Loading())
            val products = productApi.getProductListByFarmerId(farmerId).map { it.toProduct() }
            emit(Resource.Success(products))
        }.catch { emit(Resource.Error(it.message!!)) }
}