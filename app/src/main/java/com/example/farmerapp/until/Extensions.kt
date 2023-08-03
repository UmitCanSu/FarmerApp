package com.example.farmerapp.until

import com.example.farmerapp.data.local.dto.AmountPaidDto
import com.example.farmerapp.data.local.relations.AmountPaidRelations
import com.example.farmerapp.data.local.relations.FarmerRelations
import com.example.farmerapp.data.local.relations.SaleProductWitOtherClass
import com.example.farmerapp.domain.model.AmountPaid
import com.example.farmerapp.domain.model.Farmer
import com.example.farmerapp.domain.model.SalesProduct

object Extensions {
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
            salesProduct.salesDate,
            salesProduct.amountPaint
        )
    }

    fun AmountPaidRelations.toAmountPaid(): AmountPaid {
        return AmountPaid(
            salesProduct,
            customer,
            amountPaid.price,
            amountPaid.date
        )
    }

    fun FarmerRelations.toFarmer(): Farmer {
        return Farmer(
            company,
            name,
            sourName,
            years,
            FarmerStatus.values()[farmerStatus]
        )
    }
}