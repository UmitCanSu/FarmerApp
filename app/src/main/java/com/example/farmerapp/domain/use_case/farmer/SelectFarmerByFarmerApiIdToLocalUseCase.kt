package com.example.farmerapp.domain.use_case.farmer

import com.example.farmerapp.domain.model.Farmer
import com.example.farmerapp.domain.repository.local.FarmerRepository
import com.example.farmerapp.until.Resource
import com.example.farmerapp.until.extetensions.FarmerExtensions.toFarmer
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SelectFarmerByFarmerApiIdToLocalUseCase
@Inject constructor(
    private val farmerRepository: FarmerRepository
) {
    fun selectFarmerByFarmerApiIdToLocal(farmerApiId: String) = flow<Resource<Farmer>> {
        emit(Resource.Loading())
        // val farmer = farmerRepository.selectFarmerByApiId(customerApiId).toFarmerApiDto()
        val farmerRelations = farmerRepository.selectFarmerByApiId(farmerApiId)
        val farmer = farmerRelations.toFarmer()
        emit(Resource.Success(farmer))
    }.catch { emit(Resource.Error(it.message!!)) }
}