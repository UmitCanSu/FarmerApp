package com.example.farmerapp.until.extetensions

import com.example.farmerapp.data.local.dto.ProductDto
import com.example.farmerapp.data.local.relations.ProductRelations
import com.example.farmerapp.data.remote.dto.ProductApiDto
import com.example.farmerapp.domain.model.Company
import com.example.farmerapp.domain.model.Product
import com.example.farmerapp.until.UserSingleton
import com.example.farmerapp.until.extetensions.CompanyExtensions.toCompany
import java.time.LocalDateTime

object ProductExtensions {
    fun Product.toProductDto(): ProductDto {
        return ProductDto(
            name,
            unitType,
            price,
            company.id
        )
    }

    fun ProductDto.toProduct(company: Company): Product {
        return Product(
            id,
            name,
            unitType,
            price,
            company
        )
    }

    fun ProductRelations.toProduct(): Product {
        val product = Product(
            productDto.id,
            productDto.name,
            productDto.unitType,
            productDto.price,
            company.toCompany()!!
        )
        product.id = product.id
        return product
    }

    fun Product.toProductApiDto(): ProductApiDto {
        return ProductApiDto(
            id.toString(), name, "", price,unitType, LocalDateTime.now().toString(), company.id.toString()
        )
    }

    fun ProductApiDto.toProduct(): Product {
        return Product(
            name,
            unitType,
            price,
            UserSingleton.getInstance().company!!
        )
    }

}