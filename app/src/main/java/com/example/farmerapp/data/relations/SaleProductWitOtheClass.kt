package com.example.farmerapp.data.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.farmerapp.domain.model.Company
import com.example.farmerapp.domain.model.Customer
import com.example.farmerapp.domain.model.Farmer
import com.example.farmerapp.domain.model.Product
import com.example.farmerapp.domain.model.SalesProduct

data class SaleProductWitOtheClass(
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