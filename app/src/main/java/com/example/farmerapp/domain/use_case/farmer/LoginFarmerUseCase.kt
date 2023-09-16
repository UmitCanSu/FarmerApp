package com.example.farmerapp.domain.use_case.farmer

import com.example.farmerapp.data.remote.dto.LoginApiDto
import com.example.farmerapp.domain.model.Farmer
import com.example.farmerapp.domain.repository.remote.FarmerApiRepository
import com.example.farmerapp.until.Resource
import com.example.farmerapp.until.extetensions.FarmerExtensions.toFarmer
import com.google.gson.Gson
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginToApiFarmerUseCase
@Inject constructor(
    private val farmerApiRepository: FarmerApiRepository
){
    fun login(login: LoginApiDto)= flow<Resource<Farmer>> {
        emit(Resource.Loading())
        val farmerLoginJson = Gson().toJson(login)
        val farmerApiDto = farmerApiRepository.login(farmerLoginJson)
        emit(Resource.Success(farmerApiDto.toFarmer()))
    }.catch { emit(Resource.Error(it.message!!)) }
}