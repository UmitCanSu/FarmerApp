package com.example.farmerapp.until.extetensions

import com.example.farmerapp.data.local.dto.ProductDto
import com.example.farmerapp.data.local.relations.ProductRelations
import com.example.farmerapp.data.remote.dto.ProductApiDto
import com.example.farmerapp.domain.model.Company
import com.example.farmerapp.domain.model.Product
import com.example.farmerapp.until.Sesion
import com.example.farmerapp.until.extetensions.CompanyExtensions.toCompany
import java.time.LocalDateTime

object ProductExtensions {
    fun Product.toProductDto(): ProductDto {
        return ProductDto(
            name,
            unitType,
            price,
            company.id,
            apiId,
        )
    }

    fun ProductDto.toProduct(company: Company): Product {
        return Product(
            id,
            name,
            unitType,
            price,
            company,
            apiId
        )
    }

    fun ProductRelations.toProduct(): Product {
        val product = Product(
            productDto.id,
            productDto.name,
            productDto.unitType,
            productDto.price,
            company.toCompany()!!,
            productDto.apiId
        )
        product.id = product.id
        return product
    }

    fun Product.toProductApiDto(): ProductApiDto {
        return ProductApiDto(
            apiId,
            name,
            "",
            price,
            unitType,
            LocalDateTime.now().toString(),
            company.apiId,
            Sesion.getInstance().farmer!!.farmerApiId
        )
    }

    fun ProductApiDto.toProduct(): Product {
        return Product(
            0,
            name,
            unitType,
            price,
            Sesion.getInstance().company!!,
            id!!
        )
    }

}