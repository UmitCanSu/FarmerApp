package com.example.farmerapp.until.Extetensions

import com.example.farmerapp.data.local.dto.FarmerDto
import com.example.farmerapp.data.local.relations.FarmerRelations
import com.example.farmerapp.domain.model.Farmer
import com.example.farmerapp.until.FarmerStatus

object FarmerExtensions {
    fun FarmerRelations.toFarmer(): Farmer {
        return Farmer(
            company,
            name,
            sourName,
            years,
            FarmerStatus.values()[farmerStatus]
        )
    }
    fun Farmer.toFarmerDto(): FarmerDto {
        return FarmerDto(
            company.id,
            name,
            sourName,
            years,
            farmerStatus.ordinal
        )
    }
}