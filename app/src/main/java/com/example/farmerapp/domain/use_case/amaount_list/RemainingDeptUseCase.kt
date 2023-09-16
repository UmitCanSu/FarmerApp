package com.example.farmerapp.domain.use_case.amaount_list

import com.example.farmerapp.domain.repository.local.AmountPaidRepository
import com.example.farmerapp.until.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RemainingDeptUseCase
@Inject constructor(
    private val amountPaidRepository: AmountPaidRepository
) {
    fun calculateRemainingDept(salesId: Int) = flow<Resource<Float>> {
        emit(Resource.Loading())
        emit(Resource.Success(amountPaidRepository.getRemainingDept(salesId)))

    }.catch { emit(Resource.Error(it.message!!)) }.flowOn(Dispatchers.IO)
}