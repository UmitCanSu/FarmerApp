package com.example.farmerapp.domain.use_case.amaount_list

import com.example.farmerapp.domain.model.AmountPaid
import com.example.farmerapp.domain.repository.room.AmountPaidRepository
import com.example.farmerapp.until.Resource
import com.example.farmerapp.until.extetensions.AmountPaidExtensions.toAmountPaidDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class InsertAmountPaidUseCase
@Inject constructor(
    private val amountPaidRepository: AmountPaidRepository
) {
    fun insertAmountPaid(amountPaid: AmountPaid) = flow<Resource<Boolean>> {
        emit(Resource.Loading())
        val index = amountPaidRepository.insertAmountPaid(amountPaid.toAmountPaidDto())
        emit(Resource.Success(index > 0))
    }.catch {
        emit(Resource.Error(it.message!!))
    }.flowOn(Dispatchers.IO)
}