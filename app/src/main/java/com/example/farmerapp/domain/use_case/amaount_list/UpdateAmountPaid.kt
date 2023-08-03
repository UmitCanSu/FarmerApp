package com.example.farmerapp.domain.use_case.amaount_list

import com.example.farmerapp.domain.model.AmountPaid
import com.example.farmerapp.domain.repository.AmountPaidRepository
import com.example.farmerapp.until.Resource
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UpdateAmountPaid
@Inject constructor(
    private val amountPaidRepository: AmountPaidRepository
) {
    fun updateAmountPaid(amountPaid: AmountPaid) = flow<Resource<Boolean>> {
        emit(Resource.Loading())
        val index = amountPaidRepository.updateAmountPaid(amountPaid)
        emit(Resource.Success(index > 0))
    }.catch {
        emit(Resource.Error(it.message))
    }
}