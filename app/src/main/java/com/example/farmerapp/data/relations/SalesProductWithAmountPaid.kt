package com.example.farmerapp.data.relations

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation
import com.example.farmerapp.domain.model.AmountPaid
import com.example.farmerapp.domain.model.SalesProduct


data class SalesProductWithAmountPaid(
    @Embedded
    val salesProduct: SalesProduct,
    @Relation(
        entityColumn = "id",
        parentColumn = "salesProductId"
    )
    val amountPaid: List<AmountPaid>

)