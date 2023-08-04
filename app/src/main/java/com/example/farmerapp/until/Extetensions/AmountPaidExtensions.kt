package com.example.farmerapp.until.Extetensions

import com.example.farmerapp.data.local.dto.AmountPaidDto
import com.example.farmerapp.data.local.relations.AmountPaidRelations
import com.example.farmerapp.domain.model.AmountPaid

object AmountPaidExtensions {
    fun AmountPaidRelations.toAmountPaid(): AmountPaid {
        return AmountPaid(
            salesProduct,
            customer,
            amountPaid.price,
            amountPaid.date,
            amountPaid.location
        )
    }



    fun AmountPaid.toAmountPaidDto(): AmountPaidDto {
        return AmountPaidDto(
            salesProduct.id,
            customer.id,
            price,
            date,
            location
        )
    }
}