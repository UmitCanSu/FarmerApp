package com.example.farmerapp.data.local.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.farmerapp.data.local.dto.CompanyDto
import com.example.farmerapp.data.local.dto.FarmerDto
import com.example.farmerapp.domain.model.Company

data class FarmerRelations(
    @Embedded
    val farmerDto: FarmerDto,
    @Relation(
        parentColumn = "companyId",
        entityColumn = "id"

    )
    val company: CompanyDto,

)
