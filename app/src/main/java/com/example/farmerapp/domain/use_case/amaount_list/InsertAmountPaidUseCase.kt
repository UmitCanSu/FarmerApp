package com.example.farmerapp.domain.use_case.amaount_list

import com.example.farmerapp.domain.model.AmountPaid
import com.example.farmerapp.domain.repository.AmountPaidRepository
import com.example.farmerapp.until.Extetensions.AmountPaidExtensions.toAmountPaidDto
import com.example.farmerapp.until.Resource
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class InsertAmountPaidUseCase
@Inject constructor(
    private val amountPaidRepository: AmountPaidRepository
) {
    fun insertAmountPaid(amountPaid: AmountPaid) = flow<Resource<Boolean>> {
        emit(Resource.Loading())
        val index = amountPaidRepository.insertAmountPaid(amountPaid.toAmountPaidDto())
        Resource.Success(index > 0)
    }.catch {
        emit(Resource.Error(it.message!!))
    }
}