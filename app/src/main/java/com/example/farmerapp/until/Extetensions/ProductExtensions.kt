package com.example.farmerapp.until.Extetensions

import com.example.farmerapp.data.local.dto.ProductDto
import com.example.farmerapp.domain.model.Product

object ProductExtensions {
    fun Product.toProductDto(): ProductDto {
        return ProductDto(
            name,
            unitType,
            price,
            company.id
        )
    }
}