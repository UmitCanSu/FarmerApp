package com.example.farmerapp.until.Extetensions

import com.example.farmerapp.data.local.dto.SalesProductDto
import com.example.farmerapp.data.local.relations.SaleProductWitOtherClass
import com.example.farmerapp.domain.model.SalesProduct

object SalesProductExtensions {
    fun SaleProductWitOtherClass.toSaleProduct(): SalesProduct {
        return SalesProduct(
            company,
            product,
            customer,
            farmer,
            salesProduct.price,
            salesProduct.isDept,
            salesProduct.productNumber,
            salesProduct.location,
            salesProduct.locationDescription,
            salesProduct.salesDate,
            salesProduct.amountPaint
        )
    }
    fun SalesProduct.toSalesProductDto(): SalesProductDto {
        return SalesProductDto(
            company.id,
            product.id,
            customer.id,
            farmer.id,
            price,
            isDept,
            productNumber,
            location,
            locationDescription,
            salesDate
        )
    }


}