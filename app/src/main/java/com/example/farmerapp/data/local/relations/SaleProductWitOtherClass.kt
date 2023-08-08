package com.example.farmerapp.data.local.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.farmerapp.data.local.dto.CompanyDto
import com.example.farmerapp.data.local.dto.CustomerDto
import com.example.farmerapp.data.local.dto.FarmerDto
import com.example.farmerapp.data.local.dto.ProductDto
import com.example.farmerapp.data.local.dto.SalesProductDto

data class SaleProductWitOtherClass(
    @Embedded
    val salesProductDto: SalesProductDto,
    @Relation(
        parentColumn = "companyId",
        entityColumn = "id"
    )
    val companyDto: CompanyDto,
    @Relation(
        parentColumn = "productId",
        entityColumn = "id"
    )
    val productDto: ProductDto,
    @Relation(
        parentColumn = "customerId",
        entityColumn = "id"
    )
    val customerDto: CustomerDto,
    @Relation(
        parentColumn = "farmerId",
        entityColumn = "id"
    )
    val farmerDto: FarmerDto,
    )