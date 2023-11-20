package com.example.farmerapp.until.extetensions

import com.example.farmerapp.data.local.dto.CompanyDto
import com.example.farmerapp.data.remote.dto.CompanyApiDto
import com.example.farmerapp.domain.model.Company

object CompanyExtensions {
    fun CompanyDto.toCompany(): Company? {
        return Company(
            id,
            name,
            address,
            phone,
            apiId

        )
    }

    fun Company.toCompanyDto(): CompanyDto {
        return CompanyDto(
            name,
            address,
            phone,
            apiId
        )
    }

    fun CompanyApiDto.toCompany(): Company {
        return Company(name, address, phoneNumber,id)
    }

    fun Company.toCompanyApiDto(): CompanyApiDto {
        return CompanyApiDto(
            apiId, name, address, phone
        )
    }

}