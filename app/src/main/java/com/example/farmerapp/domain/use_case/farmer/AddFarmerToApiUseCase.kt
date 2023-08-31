package com.example.farmerapp.domain.use_case.farmer

import android.util.Log
import com.example.farmerapp.data.remote.dto.FarmerApiDto
import com.example.farmerapp.domain.repository.api.repository.FarmerApiRepository
import com.example.farmerapp.until.Resource
import com.google.gson.Gson
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AddFarmerToApiUseCase
    @Inject constructor(
    private val farmerApiRepository: FarmerApiRepository
) {

    fun addFarmer(farmer:FarmerApiDto)= flow<Resource<FarmerApiDto>> {
        emit(Resource.Loading())
        var farmerJson = Gson().toJson(farmer)
        var farmerApiDto= farmerApiRepository.addFarmerApi(farmerJson)
        Log.e("S->",farmerJson)
        emit(Resource.Success(farmerApiDto))
    }.catch { emit(Resource.Error(it.message!!)) }
}