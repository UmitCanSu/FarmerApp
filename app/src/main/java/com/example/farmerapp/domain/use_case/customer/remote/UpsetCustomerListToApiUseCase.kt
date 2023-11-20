package com.example.farmerapp.domain.use_case.customer.remote

import com.example.farmerapp.data.remote.api.CustomerApi
import com.example.farmerapp.data.remote.dto.CustomerApiDto
import com.example.farmerapp.data.remote.dto.FarmerApiDto
import com.example.farmerapp.data.remote.dto.SavedApiDto
import com.example.farmerapp.domain.model.Farmer
import com.example.farmerapp.until.Resource
import com.example.farmerapp.until.Sesion
import com.example.farmerapp.until.extetensions.FarmerExtensions.toFarmerApiDto
import com.google.android.gms.maps.model.LatLng
import com.google.gson.Gson
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import java.time.LocalDateTime
import javax.inject.Inject

class UpsetCustomerListToApiUseCase
@Inject constructor(
    private val customerApi: CustomerApi
) {
    fun upsetCustomerList(customer: Farmer, location: LatLng) = flow {
        emit(Resource.Loading())
        val customerString = Gson().toJson(customer.toFarmerApiDto())
        val savedString = Gson().toJson(getSaved(location))
        val companyId = Sesion.getInstance().company!!.apiId
        val customerListApiDto = customerApi.upsetCustomer(customerString, savedString,companyId)
        emit(Resource.Success(customerListApiDto.customers[0]))
    }.catch { emit(Resource.Error(it.message!!)) }

    private fun getSaved(location: LatLng): SavedApiDto {
        return SavedApiDto(
            Sesion.getInstance().farmer!!.toFarmerApiDto(),
            LocalDateTime.now().toString(),
            location
        )
    }


}