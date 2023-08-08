package com.example.farmerapp.domain.use_case.farmer

import com.example.farmerapp.domain.model.Farmer
import com.example.farmerapp.domain.repository.FarmerRepository
import com.example.farmerapp.until.Extetensions.FarmerExtensions.toFarmer
import com.example.farmerapp.until.Resource
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SelectFarmersWithCompanyIdUseCase
@Inject constructor(
    private val farmerRepository: FarmerRepository
) {
    fun selectFarmersWithCompanyId(companyId: Int) = flow<Resource<List<Farmer>>> {
        emit(Resource.Loading())
        val farmerList =
            farmerRepository.selectFarmersWithCompanyId(companyId).map { farmerRelations ->
                farmerRelations.toFarmer()
            }
        emit(Resource.Success(farmerList))
    }.catch { emit(Resource.Error(it.message!!)) }
}
