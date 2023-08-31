package com.example.farmerapp.domain.use_case.sale_fragment

import com.example.farmerapp.domain.model.Product
import com.example.farmerapp.until.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CalculateProductPriceUseCase
@Inject constructor(

) {
    fun calculateProductPrice(product: Product, count: Int) = flow<Resource<Float>> {
        emit(Resource.Loading())
        val price = product.price * count
        emit(Resource.Success(price))
    }
        .catch { emit(Resource.Error(it.message!!)) }
        .flowOn(Dispatchers.IO)
}