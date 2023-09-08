package com.example.farmerapp.until.extetensions

import com.example.farmerapp.data.local.dto.SalesProductDto
import com.example.farmerapp.data.local.relations.SaleProductWitOtherClass
import com.example.farmerapp.data.remote.dto.SaleApiDto
import com.example.farmerapp.domain.model.AmountPaid
import com.example.farmerapp.domain.model.Company
import com.example.farmerapp.domain.model.Customer
import com.example.farmerapp.domain.model.Farmer
import com.example.farmerapp.domain.model.Product
import com.example.farmerapp.domain.model.SalesProduct
import com.example.farmerapp.until.extetensions.CompanyExtensions.toCompany
import com.example.farmerapp.until.extetensions.CompanyExtensions.toCompanyApiDto
import com.example.farmerapp.until.extetensions.CustomerExtensions.toCustomer
import com.example.farmerapp.until.extetensions.CustomerExtensions.toCustomerApiDto
import com.example.farmerapp.until.extetensions.FarmerExtensions.toFarmer
import com.example.farmerapp.until.extetensions.FarmerExtensions.toFarmerApiDto
import com.example.farmerapp.until.extetensions.ProductExtensions.toProduct
import com.example.farmerapp.until.extetensions.ProductExtensions.toProductApiDto
import com.google.android.gms.maps.model.LatLng
import java.time.LocalDateTime

object SalesProductExtensions {
    fun SaleProductWitOtherClass.toSaleProduct(): SalesProduct {
        val location = LatLng(salesProductDto.latitude, salesProductDto.longitude)
        return SalesProduct(
            salesProductDto.id,
            companyDto.toCompany()!!,
            farmerDto.toFarmer(companyDto.toCompany()!!),
            productDto.toProduct(companyDto.toCompany()!!),
            customerDto.toCustomer(),
            salesProductDto.price,
            salesProductDto.isDept,
            salesProductDto.productNumber,
            location,
            salesProductDto.locationDescription,
            salesProductDto.salesDate,
            salesProductDto.isPaid,
            emptyList()
        )
    }

    fun SalesProduct.toSalesProductDto(): SalesProductDto {
        val salesProduct = SalesProductDto(
            company.id,
            product.id,
            customer.id,
            farmer.id,
            price,
            isDept,
            productNumber,
            location.latitude,
            location.longitude,
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
        val location = LatLng(latitude, longitude)
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

    fun SalesProduct.toSaleApiDto(): SaleApiDto {
        return SaleApiDto(
            "",
            company.toCompanyApiDto(),
            product.toProductApiDto(),
            customer.toCustomerApiDto(),
            farmer.toFarmerApiDto(),
            price,
            location,
            productNumber,
            salesDate.toString(),
            locationDescription,
            isPaid,
            amountPaint
        )
    }

    fun SaleApiDto.toSalesProduct(): SalesProduct {
        return SalesProduct(
            product.toProduct(),
            customer.toCustomer(),
            price,
            isPaid,
            productCount,
            location,
            locationDescription,
            LocalDateTime.now(),//LocalDateTime.parse(saleDate),
            isPaid,
            amountPaids
        )
    }


}