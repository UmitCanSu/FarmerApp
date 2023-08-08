package com.example.farmerapp.until.Extetensions

import com.example.farmerapp.data.local.dto.CustomerDto
import com.example.farmerapp.domain.model.Customer

object CustomerExtensions {
    fun CustomerDto.toCustomer(): Customer {
        return Customer(
            name,
            sourName,
            phone,
            address,
            phoneNumber
        )
    }

    fun Customer.toCustomerDto(): CustomerDto {
        return CustomerDto(
            name,
            sourName,
            phone,
            address,
            phoneNumber
        )
    }


}