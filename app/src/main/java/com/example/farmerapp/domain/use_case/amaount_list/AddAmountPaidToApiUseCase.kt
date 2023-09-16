package com.example.farmerapp.domain.use_case.amaount_list

import com.example.farmerapp.data.remote.dto.SaleApiDto
import com.example.farmerapp.domain.model.AmountPaid
import com.example.farmerapp.domain.repository.remote.SaleApiRepository
import com.example.farmerapp.until.Resource
import com.example.farmerapp.until.extetensions.AmountPaidExtensions.toAmountPaidApiDto
import com.google.gson.Gson
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AddAmountPaidToApiUseCase
@Inject constructor(
    private val saleApiRepository: SaleApiRepository
) {
    fun addAmountPaid(amountPaid: AmountPaid) = flow<Resource<SaleApiDto>> {
        emit(Resource.Loading())
        val saleId ="64f22b83704607f6c428aa0b" //amountPaid.salesProduct!!.id.toString()
        val amountPaidApiDto = amountPaid.toAmountPaidApiDto()
        val amountPaidApiDtoJson = Gson().toJson(amountPaidApiDto)
        val saleApiDto = saleApiRepository.addAmountPaid(saleId, amountPaidApiDtoJson)
        emit(Resource.Success(saleApiDto))
    }.catch { emit(Resource.Error(it.message!!)) }
}