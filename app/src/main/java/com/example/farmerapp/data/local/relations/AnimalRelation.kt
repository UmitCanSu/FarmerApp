package com.example.farmerapp.data.local.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.farmerapp.data.local.dto.AnimalDto
import com.example.farmerapp.data.local.dto.CompanyDto
import com.example.farmerapp.data.local.dto.FarmerDto

data class AnimalRelation(
    @Embedded
    val animalDto: AnimalDto,
    @Relation(
        parentColumn = "companyId",
        entityColumn = "id"
    )
    val companyDto: CompanyDto,
    @Relation(
        parentColumn = "addFarmerId",
        entityColumn = "id"
    )
    val farmerDto: FarmerDto,
)