package com.example.farmerapp.domain.use_case.farmer

import com.example.farmerapp.domain.model.Farmer
import com.example.farmerapp.domain.repository.remote.FarmerApiRepository
import com.example.farmerapp.until.Resource
import com.example.farmerapp.until.extetensions.FarmerExtensions.toFarmer
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetFarmerByNickNameOrPhoneNumberToApiUseCase
@Inject constructor(
    private val farmerApiRepository: FarmerApiRepository
) {
    fun getFarmerByNickNameOrPhoneNumber(nickNameOrPhoneNumber: String) =
        flow<Resource<Farmer>> {
            emit(Resource.Loading())
            val farmer = farmerApiRepository.getFarmerByNickNameOrPhoneNumber(nickNameOrPhoneNumber)
                .toFarmer()
            emit(Resource.Success(farmer))
        }.catch { emit(Resource.Error(it.message!!)) }
}