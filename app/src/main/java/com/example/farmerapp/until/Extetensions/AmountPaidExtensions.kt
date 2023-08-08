package com.example.farmerapp.until.Extetensions

import com.example.farmerapp.data.local.dto.AmountPaidDto
import com.example.farmerapp.data.local.relations.AmountPaidRelations
import com.example.farmerapp.domain.model.AmountPaid
import com.example.farmerapp.until.Extetensions.CustomerExtensions.toCustomer

object AmountPaidExtensions {

    fun AmountPaidRelations.toAmountPaid(): AmountPaid {
        return AmountPaid(
            null,
            customerDto.toCustomer(),
            amountPaidDto.price,
            amountPaidDto.date,
            amountPaidDto.location
        )
    }

    fun AmountPaid.toAmountPaidDto(): AmountPaidDto {
        return AmountPaidDto(
            salesProduct?.id ?: 0,
            customer.id,
            price,
            date,
            location
        )
    }
}