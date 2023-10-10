package com.example.farmerapp.domain.use_case.customer

import com.example.farmerapp.data.remote.api.CustomerApi
import com.example.farmerapp.data.remote.dto.CustomerApiDto
import com.example.farmerapp.data.remote.dto.CustomerListApiDto
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

class UpsetCustomerListUseCase
@Inject constructor(
    private val customerApi: CustomerApi
) {
    fun upsetCustomerListUseCase(customer: Farmer, location: LatLng) =
        flow<Resource<CustomerListApiDto>> {
            emit(Resource.Loading())
            val customerListApi = CustomerListApiDto(
                Sesion.getInstance().company!!.id.toString(),
                arrayOf(getCustomerList(customer.toFarmerApiDto(), getSaved(location))).asList()
            )
            val customerListApiString = Gson().toJson(customerListApi)
            customerApi.upsetCustomer(customerListApiString)
            emit(Resource.Success(customerListApi))
        }.catch { emit(Resource.Error(it.message!!)) }

    private fun getSaved(location: LatLng): SavedApiDto {
        return SavedApiDto(
            Sesion.getInstance().farmer!!.toFarmerApiDto(),
            LocalDateTime.now().toString(),
            location
        )
    }

    private fun getCustomerList(
        farmerApiDto: FarmerApiDto,
        savedApiDto: SavedApiDto
    ): CustomerApiDto {
        return CustomerApiDto(farmerApiDto, savedApiDto)
    }


}