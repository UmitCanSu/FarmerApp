package com.example.farmerapp.data.local.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.farmerapp.data.local.dto.AmountPaidDto
import com.example.farmerapp.data.local.dto.CustomerDto
import com.example.farmerapp.data.local.dto.FarmerDto
import com.example.farmerapp.data.local.dto.SalesProductDto
import com.example.farmerapp.domain.model.Product

data class AmountPaidRelations(
    @Embedded
    val amountPaidDto: AmountPaidDto,
    @Relation(
        parentColumn = "customerId",
        entityColumn = "id"
    )
    val customerDto: CustomerDto,
    /*
    @Relation(
        parentColumn = "salesProductId",
        entityColumn = "id"
    )
    val salesProductDto: SalesProductDto,

     */
)