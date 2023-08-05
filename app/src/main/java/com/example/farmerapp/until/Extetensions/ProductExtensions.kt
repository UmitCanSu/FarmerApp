package com.example.farmerapp.until.Extetensions

import com.example.farmerapp.data.local.dto.ProductDto
import com.example.farmerapp.data.local.relations.ProductRelations
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
    fun ProductRelations.toProduct():Product{
        return Product(
            product.name,
            product.unitType,
            product.price,
            company
        )
    }

}