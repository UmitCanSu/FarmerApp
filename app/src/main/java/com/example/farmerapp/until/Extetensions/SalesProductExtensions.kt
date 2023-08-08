package com.example.farmerapp.until.Extetensions

import com.example.farmerapp.data.local.dto.SalesProductDto
import com.example.farmerapp.data.local.relations.SaleProductWitOtherClass
import com.example.farmerapp.domain.model.AmountPaid
import com.example.farmerapp.domain.model.Company
import com.example.farmerapp.domain.model.Customer
import com.example.farmerapp.domain.model.Farmer
import com.example.farmerapp.domain.model.Product
import com.example.farmerapp.domain.model.SalesProduct
import com.example.farmerapp.until.Extetensions.CompanyExtensions.toCompany
import com.example.farmerapp.until.Extetensions.CustomerExtensions.toCustomer
import com.example.farmerapp.until.Extetensions.FarmerExtensions.toFarmer
import com.example.farmerapp.until.Extetensions.ProductExtensions.toProduct

object SalesProductExtensions {

    fun SaleProductWitOtherClass.toSaleProduct(): SalesProduct {
        return SalesProduct(
            companyDto.toCompany()!!,
            productDto.toProduct(companyDto.toCompany()!!),
            customerDto.toCustomer(),
            farmerDto.toFarmer(companyDto.toCompany()!!),
            salesProductDto.price,
            salesProductDto.isDept,
            salesProductDto.productNumber,
            salesProductDto.location,
            salesProductDto.locationDescription,
            salesProductDto.salesDate,
            emptyList()
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

    fun SalesProductDto.toSalesProduct(
        company: Company, product: Product, customer: Customer,
        farmer: Farmer, amountPaidList: List<AmountPaid>
    ): SalesProduct {
        return SalesProduct(
            company,
            product,
            customer,
            farmer,
            price,
            isDept,
            productNumber,
            location,
            locationDescription,
            salesDate,
            amountPaidList
        )

    }


}