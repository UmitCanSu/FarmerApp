package com.example.farmerapp.until.extetensions

import com.example.farmerapp.data.local.dto.CustomerDto
import com.example.farmerapp.domain.model.Customer

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


}