package com.example.farmerapp.model

import androidx.room.Embedded
import androidx.room.Relation

data class SaleProductWith(
    @Embedded
    val company: Company,
    @Relation(
        parentColumn = "id",
        entityColumn = "companyId"
    )
    @Embedded
    val product: Product,
    @Relation(
        parentColumn = "id",
        entityColumn = "productId"
    )
    @Embedded
    val customer: Customer,
    @Relation(
        parentColumn = "id",
        entityColumn = "customerId"
    )
    @Embedded
    val farmer: Farmer,
    @Relation(
        parentColumn = "id",
        entityColumn = "farmerId"
    )
    val salesProduct: SalesProduct
)