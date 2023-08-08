package com.example.farmerapp.domain.use_case.farmer

import com.example.farmerapp.domain.model.Farmer
import com.example.farmerapp.domain.repository.FarmerRepository
import com.example.farmerapp.until.Extetensions.FarmerExtensions.toFarmerDto
import com.example.farmerapp.until.Resource
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UpdateFarmerUseCase
@Inject constructor(
    private val farmerRepository: FarmerRepository
) {
    fun updateFarmer(farmer: Farmer) = flow<Resource<Boolean>> {
        emit(Resource.Loading())
        val updeteSize = farmerRepository.updateFarmer(farmer.toFarmerDto())
        emit(Resource.Success(updeteSize > 0))
    }.catch { emit(Resource.Error(it.message!!)) }
}