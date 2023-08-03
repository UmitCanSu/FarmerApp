package com.example.farmerapp.data.local.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.farmerapp.domain.model.AmountPaid
import com.example.farmerapp.domain.model.Customer
import com.example.farmerapp.domain.model.SalesProduct

data class AmountPaidRelations(
    @Embedded
    val salesProduct: SalesProduct,
    @Relation(
        parentColumn = "id",
        entityColumn = "salesProductId"
    )
    @Embedded
    val customer: Customer,
    @Relation(
        parentColumn = "id",
        entityColumn = "customerId"
    )
    val amountPaid: AmountPaid

)