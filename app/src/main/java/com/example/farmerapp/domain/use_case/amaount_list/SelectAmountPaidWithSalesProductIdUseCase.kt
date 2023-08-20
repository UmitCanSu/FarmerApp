package com.example.farmerapp.domain.use_case.amaount_list

import com.example.farmerapp.domain.model.AmountPaid
import com.example.farmerapp.domain.repository.AmountPaidRepository
import com.example.farmerapp.until.extetensions.AmountPaidExtensions.toAmountPaid

import com.example.farmerapp.until.Resource
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SelectAmountPaidWithSalesProductIdUseCase
@Inject constructor(
    private val amountPaidRepository: AmountPaidRepository
) {
    fun selectAmountPaidWithSalesProductId(salesProductId: Int) = flow<Resource<List<AmountPaid>>> {
        emit(Resource.Loading())
        val amountPaidList = amountPaidRepository.selectAmountPaidWithSalesProductId(salesProductId).map {amountPaidRelations ->
            amountPaidRelations.toAmountPaid()
        }
        emit(Resource.Success(amountPaidList))
    }.catch {
        emit(Resource.Error(it.message!!))
    }
}