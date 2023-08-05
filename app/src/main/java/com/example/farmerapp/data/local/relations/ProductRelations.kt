package com.example.farmerapp.data.local.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.farmerapp.domain.model.Company
import com.example.farmerapp.domain.model.Product
import javax.inject.Inject

data class ProductRelations
@Inject constructor(
    @Embedded
    val company:Company,
    @Relation(
        entityColumn = "id",
        parentColumn = "companyId"
    )
    val product: Product
)