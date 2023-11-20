package com.example.farmerapp.until.extetensions

import com.example.farmerapp.data.local.dto.SalesProductDto
import com.example.farmerapp.data.local.relations.SaleProductWitOtherClass
import com.example.farmerapp.data.remote.dto.CustomerApiDto
import com.example.farmerapp.data.remote.dto.SaleApiDto
import com.example.farmerapp.domain.model.SalesProduct
import com.example.farmerapp.until.extetensions.AmountPaidExtensions.toAmountPaid
import com.example.farmerapp.until.extetensions.AmountPaidExtensions.toAmountPaidApiDto
import com.example.farmerapp.until.extetensions.CompanyExtensions.toCompany
import com.example.farmerapp.until.extetensions.CompanyExtensions.toCompanyApiDto
import com.example.farmerapp.until.extetensions.CustomerExtensions.toCustomer
import com.example.farmerapp.until.extetensions.CustomerExtensions.toFarmerApiDto
import com.example.farmerapp.until.extetensions.FarmerExtensions.toCustomer
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
            salesProductDto.apiId,
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
            apiId,
            company.id,
            product.id,
            customer!!.id,
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


    fun SalesProduct.toSaleApiDto(): SaleApiDto {
        return SaleApiDto(
            apiId,
            company.toCompanyApiDto(),
            product.toProductApiDto(),
            CustomerApiDto(customer.toFarmerApiDto(), null),
            farmer.toFarmerApiDto(),
            price,
            location,
            productNumber,
            salesDate.toString(),
            locationDescription,
            isPaid,
            amountPaint.map { it.toAmountPaidApiDto() }
        )
    }

    fun SaleApiDto.toSalesProduct(): SalesProduct {
        return SalesProduct(
            id = 0,
            id,
            company.toCompany(),
            farmer.toFarmer(),
            product.toProduct(),
            customer!!.farmer.toCustomer(),
            price,
            isPaid,
            productCount,
            location,
            locationDescription,
            LocalDateTime.now(),
            isPaid,
            amountPaids.map { it.toAmountPaid() }
        )
    }


}