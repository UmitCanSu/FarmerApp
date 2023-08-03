package com.example.farmerapp.data.local.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.farmerapp.domain.model.Company

data class FarmerRelations(
    @Embedded
    val company: Company,
    @Relation(
        entityColumn = "id",
        parentColumn = "companyId"
    )
    val name: String,
    val sourName: String,
    val years: Int,
    val farmerStatus: Int
)
