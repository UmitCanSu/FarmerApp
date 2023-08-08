package com.example.farmerapp.until.Extetensions

import com.example.farmerapp.data.local.dto.CompanyDto
import com.example.farmerapp.domain.model.Company

object CompanyExtensions {
    fun CompanyDto.toCompany(): Company ?{
       return Company(
            name,
            address,
            phone
        )
    }

    fun Company.toCompanyDto(): CompanyDto {
        return CompanyDto(
            name,
            address,
            phone
        )
    }
}