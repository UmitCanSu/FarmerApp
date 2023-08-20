package com.example.farmerapp.until.extetensions

import com.example.farmerapp.data.local.dto.FarmerDto
import com.example.farmerapp.data.local.relations.FarmerRelations
import com.example.farmerapp.domain.model.Company
import com.example.farmerapp.domain.model.Farmer
import com.example.farmerapp.until.extetensions.CompanyExtensions.toCompany
import com.example.farmerapp.until.FarmerStatus

object FarmerExtensions {
    fun FarmerRelations.toFarmer(): Farmer {
        return Farmer(
            farmerDto.id,
            company.toCompany(),
            this.farmerDto.name,
            this.farmerDto.sourName,
            this.farmerDto.years,
            FarmerStatus.values()[farmerDto.farmerStatus]
        )
    }

    fun Farmer.toFarmerDto(): FarmerDto {
        return FarmerDto(
            company!!.id,
            name,
            sourName,
            years,
            farmerStatus.ordinal
        )
    }

    fun FarmerDto.toFarmer(company: Company): Farmer {
        return Farmer(
            id,
            company,
            name,
            sourName,
            years,
            FarmerStatus.values()[farmerStatus]
        )
    }
}