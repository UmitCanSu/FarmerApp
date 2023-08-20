package com.example.farmerapp.domain.use_case.farmer

import com.example.farmerapp.domain.model.Farmer
import com.example.farmerapp.domain.repository.FarmerRepository
import com.example.farmerapp.until.Resource
import com.example.farmerapp.until.extetensions.FarmerExtensions.toFarmerDto
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class InsertFarmerUseCase
@Inject constructor(
    private val farmerRepository: FarmerRepository
) {
    fun insertFarmer(farmer: Farmer) = flow<Resource<Boolean>> {
        emit(Resource.Loading())
        val insertSize = farmerRepository.insertFarmer(farmer.toFarmerDto())
        emit(Resource.Success(insertSize > 0))
    }.catch { emit(Resource.Error(it.message!!)) }
}