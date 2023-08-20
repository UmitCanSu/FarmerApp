package com.example.farmerapp.until.extetensions

import com.example.farmerapp.data.local.dto.SalesProductDto
import com.example.farmerapp.data.local.relations.SaleProductWitOtherClass
import com.example.farmerapp.domain.model.AmountPaid
import com.example.farmerapp.domain.model.Company
import com.example.farmerapp.domain.model.Customer
import com.example.farmerapp.domain.model.Farmer
import com.example.farmerapp.domain.model.Product
import com.example.farmerapp.domain.model.SalesProduct
import com.example.farmerapp.until.extetensions.CompanyExtensions.toCompany
import com.example.farmerapp.until.extetensions.CustomerExtensions.toCustomer
import com.example.farmerapp.until.extetensions.FarmerExtensions.toFarmer
import com.example.farmerapp.until.extetensions.ProductExtensions.toProduct

object SalesProductExtensions {
    fun SaleProductWitOtherClass.toSaleProduct(): SalesProduct {
        return SalesProduct(
            salesProductDto.id,
            companyDto.toCompany()!!,
            farmerDto.toFarmer(companyDto.toCompany()!!),
            productDto.toProduct(companyDto.toCompany()!!),
            customerDto.toCustomer(),
            salesProductDto.price,
            salesProductDto.isDept,
            salesProductDto.productNumber,
            salesProductDto.location,
            salesProductDto.locationDescription,
            salesProductDto.salesDate,
            salesProductDto.isPaid,
            emptyList()
        )
    }

    fun SalesProduct.toSalesProductDto(): SalesProductDto {
       val salesProduct =   SalesProductDto(
            company.id,
            product.id,
            customer.id,
            farmer.id,
            price,
            isDept,
            productNumber,
            location,
            locationDescription,
            salesDate,
            isPaid
        )
        salesProduct.id = id
        return salesProduct
    }

    fun SalesProductDto.toSalesProduct(
        company: Company, product: Product, customer: Customer,
        farmer: Farmer, amountPaidList: List<AmountPaid>
    ): SalesProduct {
        return SalesProduct(
            company,
            farmer,
            product,
            customer,
            price,
            isDept,
            productNumber,
            location,
            locationDescription,
            salesDate,
            isPaid,
            amountPaidList
        )

    }


}