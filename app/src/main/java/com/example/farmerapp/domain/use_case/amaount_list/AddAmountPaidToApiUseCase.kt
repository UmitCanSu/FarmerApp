package com.example.farmerapp.domain.use_case.amaount_list

import com.example.farmerapp.domain.model.AmountPaid
import com.example.farmerapp.domain.model.SalesProduct
import com.example.farmerapp.domain.repository.remote.SaleApiRepository
import com.example.farmerapp.until.Resource
import com.example.farmerapp.until.extetensions.AmountPaidExtensions.toAmountPaidApiDto
import com.example.farmerapp.until.extetensions.SalesProductExtensions.toSalesProduct
import com.google.gson.Gson
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AddAmountPaidToApiUseCase
@Inject constructor(
    private val saleApiRepository: SaleApiRepository
) {
    fun addAmountPaid(amountPaid: AmountPaid, isPaid: Boolean) = flow<Resource<SalesProduct>> {
        emit(Resource.Loading())
        val saleId = amountPaid.salesProduct!!.apiId
        val amountPaidApiDto = amountPaid.toAmountPaidApiDto()
        val amountPaidApiDtoJson = Gson().toJson(amountPaidApiDto)
        val sale = saleApiRepository.addAmountPaid(saleId, amountPaidApiDtoJson, isPaid)
            .toSalesProduct()
        emit(Resource.Success(sale))
    }.catch { emit(Resource.Error(it.message!!)) }
}