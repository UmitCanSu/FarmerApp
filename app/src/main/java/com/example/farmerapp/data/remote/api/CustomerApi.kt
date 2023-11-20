package com.example.farmerapp.data.remote.api

import com.example.farmerapp.data.remote.dto.CustomerApiDto
import com.example.farmerapp.data.remote.dto.CustomerListApiDto
import retrofit2.http.POST
import retrofit2.http.Query

interface CustomerApi {
    @POST("Customer/AddCustomer")
    suspend fun addCustomer(
        @Query("customerJson") customerJson: String
    ): CustomerApiDto

    @POST("")
    suspend fun getAllCustomer(): List<CustomerApiDto>

    @POST("Customer/GetCustomerWithCustomerNoOrPhoneNumber")
    suspend fun getCustomerWithCustomerNoOrPhoneNumber(@Query("phoneOrCustomerId") phoneOrCustomerId: String): CustomerApiDto

    @POST("CustomerListControllers/UpsetCustomer")
    suspend fun upsetCustomer(
        @Query("updateFarmerJson") customerJson: String,
        @Query("savedJson") savedJson: String,
        @Query("companyId") companyId: String
    ): CustomerListApiDto

    @POST("CustomerListControllers/GetCustomerList")
    suspend fun getCustomerList(@Query("companyId") companyId: String): CustomerListApiDto
}