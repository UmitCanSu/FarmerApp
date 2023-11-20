package com.example.farmerapp.domain.use_case.farmer

import com.example.farmerapp.data.remote.api.FarmerAppApi
import com.example.farmerapp.domain.model.Farmer
import com.example.farmerapp.until.Resource
import com.example.farmerapp.until.extetensions.FarmerExtensions.toFarmer
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetFarmerByPhoneNumberUseCase
@Inject constructor(
    private val farmerAppApi: FarmerAppApi
){
    public fun getFarmerByPhoneNumberUseCase(phoneNumber:String)=flow<Resource<Farmer>>{
        emit(Resource.Loading())
        val farmer =farmerAppApi.getFarmerByNickNameOrPhoneNumber(phoneNumber).toFarmer()
        emit(Resource.Success(farmer))
    }.catch { emit(Resource.Error(it.message!!)) }
}