package com.example.farmerapp.domain.use_case.farmer

import com.example.farmerapp.domain.model.Farmer
import com.example.farmerapp.domain.repository.FarmerRepository
import com.example.farmerapp.until.extetensions.FarmerExtensions.toFarmer
import com.example.farmerapp.until.Resource
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SelectFarmerWithIdUseCase
@Inject constructor(
    private val farmerRepository: FarmerRepository
) {
    fun selectFarmerWithId(farmerId:Int) = flow<Resource<Farmer>> {
        emit(Resource.Loading())
        val farmerRelations = farmerRepository.selectFarmerWithId(farmerId)
        emit(Resource.Success(farmerRelations.toFarmer()))
    }.catch { emit(Resource.Error(it.message!!)) }
}