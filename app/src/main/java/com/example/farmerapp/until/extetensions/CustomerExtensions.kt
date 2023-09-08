package com.example.farmerapp.until.extetensions

import com.example.farmerapp.data.local.dto.CustomerDto
import com.example.farmerapp.data.remote.dto.CustomerApiDto
import com.example.farmerapp.domain.model.Customer
import com.example.farmerapp.domain.repository.api.CustomerApi

object CustomerExtensions {
    fun CustomerDto.toCustomer(): Customer {
        return Customer(
            id,
            name,
            sourName,
            phone,
            address
        )
    }

    fun Customer.toCustomerDto(): CustomerDto {
        return CustomerDto(
            name,
            surName,
            phone,
            address
        )
    }
    fun CustomerApiDto.toCustomer():Customer{
        return Customer(name,surName,phoneNumber,address)
    }
    fun Customer.toCustomerApiDto():CustomerApiDto{
        return CustomerApiDto(
            id.toString(),"",name,surName,phone,address
        )
    }


}