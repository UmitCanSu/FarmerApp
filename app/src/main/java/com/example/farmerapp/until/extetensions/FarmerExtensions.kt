package com.example.farmerapp.until.extetensions

import com.example.farmerapp.data.local.dto.FarmerDto
import com.example.farmerapp.data.local.relations.FarmerRelations
import com.example.farmerapp.data.remote.dto.CompanyApiDto
import com.example.farmerapp.data.remote.dto.FarmerApiDto
import com.example.farmerapp.domain.model.Company
import com.example.farmerapp.domain.model.Customer
import com.example.farmerapp.domain.model.Farmer
import com.example.farmerapp.until.FarmerStatus
import com.example.farmerapp.until.extetensions.CompanyExtensions.toCompany
import com.example.farmerapp.until.extetensions.CompanyExtensions.toCompanyApiDto
import java.time.LocalDateTime

object FarmerExtensions {
    fun FarmerRelations.toFarmer(): Farmer {
        return Farmer(
            farmerDto.id,
            company.toCompany(),
            this.farmerDto.name,
            this.farmerDto.sourName,
            this.farmerDto.years,
            FarmerStatus.values()[farmerDto.farmerStatus],
            farmerDto.farmerApiId
        )
    }

    fun Farmer.toFarmerDto(): FarmerDto {
        return FarmerDto(
            company!!.id,
            name,
            sourName,
            years,
            farmerStatus.ordinal,
            farmerApiId
        )
    }

    fun FarmerDto.toFarmer(company: Company): Farmer {
        return Farmer(
            id,
            company,
            name,
            sourName,
            years,
            FarmerStatus.values()[farmerStatus],
            farmerApiId
        )
    }

    fun FarmerApiDto.toFarmer(): Farmer {
        return Farmer(
            0,
            toLastCompany().toCompany(),
            name, surname, 0, FarmerStatus.valueOf(farmerStatus),
            id
        )
    }
    fun FarmerApiDto.toCustomer(): Customer {
        return Customer(
            0,
            toLastCompany().toCompany(),
            name, surname, 0, FarmerStatus.valueOf(farmerStatus),
            id
        )
    }


    fun Farmer.toFarmerApiDto(): FarmerApiDto {
        return FarmerApiDto(
            farmerApiId.toString(),
            listOf(company!!.toCompanyApiDto()),
            name,
            sourName,
            "",
            LocalDateTime.now().toString(),
            LocalDateTime.now().toString(),
            "",
            farmerStatus.name,
            ""
        )
    }

    fun FarmerApiDto.toLastCompany(): CompanyApiDto {
        return companies!!.reversed().first()
    }

    fun Farmer.toFirstCompany(): Company {
        return Company(
            name,
            "",
            ""
        )
    }
}