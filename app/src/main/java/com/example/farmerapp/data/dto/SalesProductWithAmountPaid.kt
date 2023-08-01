package com.example.farmerapp.data.dto

import androidx.room.Embedded
import androidx.room.Relation
import com.example.farmerapp.model.AmountPaid
import com.example.farmerapp.model.SalesProduct

data class SalesProductWithAmountPaid(
    @Embedded
    val salesProduct: SalesProduct,
    @Relation(
        entityColumn = "id",
        parentColumn = "salesProductId"
    )
    val amountPaid: List<AmountPaid>

)