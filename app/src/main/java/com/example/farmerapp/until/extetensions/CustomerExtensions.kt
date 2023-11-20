package com.example.farmerapp.until.extetensions

import com.example.farmerapp.data.local.dto.CustomerDto
import com.example.farmerapp.data.remote.dto.FarmerApiDto
import com.example.farmerapp.domain.model.Customer
import com.example.farmerapp.domain.model.Farmer
import com.example.farmerapp.until.Sesion
import com.example.farmerapp.until.extetensions.FarmerExtensions.toFarmerApiDto

object CustomerExtensions {
    fun CustomerDto.toCustomer(): Customer {
        return Customer(
            id,
            Sesion.getInstance().company!!,
            name,
            sourName,
            years,
            farmerStatus,
            customerApiId
        )
    }

    fun Customer.toCustomerDto(): CustomerDto {
        return CustomerDto(
            name,
            sourName,
            years,
            farmerStatus,
            apiId
        )
    }

    fun Customer.toFarmerApiDto(): FarmerApiDto {
        return Farmer(
            id,
            Sesion.getInstance().company,
            name,
            sourName,
            years,
            farmerStatus,
            apiId
        ).toFarmerApiDto()
    }


}