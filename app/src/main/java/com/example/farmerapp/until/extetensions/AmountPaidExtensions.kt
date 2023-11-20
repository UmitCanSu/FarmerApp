package com.example.farmerapp.until.extetensions

import com.example.farmerapp.data.local.dto.AmountPaidDto
import com.example.farmerapp.data.local.relations.AmountPaidRelations
import com.example.farmerapp.data.remote.dto.AmountPaidApiDto
import com.example.farmerapp.domain.model.AmountPaid
import com.example.farmerapp.domain.model.Customer
import com.example.farmerapp.until.extetensions.CustomerExtensions.toCustomer
import com.example.farmerapp.until.extetensions.CustomerExtensions.toFarmerApiDto
import com.example.farmerapp.until.extetensions.FarmerExtensions.toCustomer
import com.google.android.gms.maps.model.LatLng
import java.time.LocalDateTime

object AmountPaidExtensions {

    fun AmountPaidRelations.toAmountPaid(): AmountPaid {
        return AmountPaid(
            amountPaidDto.apiId,
            null,
            customerDto.toCustomer(),
            amountPaidDto.price,
            amountPaidDto.date,
            LatLng(amountPaidDto.latitude, amountPaidDto.longitude)
        )
    }

    fun AmountPaid.toAmountPaidDto(): AmountPaidDto {
        return AmountPaidDto(
            apiId,
            salesProduct?.id ?: 0,
            customer.id,
            price,
            date,
            location.latitude,
            location.longitude,
        )
    }

    fun AmountPaidApiDto.toAmountPaid(customer: Customer): AmountPaid {
        return AmountPaid(
            id, null, customer, price, LocalDateTime.now(), location
        )
    }

    fun AmountPaid.toAmountPaidApiDto(): AmountPaidApiDto {
        return AmountPaidApiDto(apiId, customer.toFarmerApiDto(), price, date.toString(), location)
    }

    fun AmountPaidApiDto.toAmountPaid(): AmountPaid {
        return AmountPaid(id, null, customer.toCustomer(), price, LocalDateTime.now(), location)
    }
}