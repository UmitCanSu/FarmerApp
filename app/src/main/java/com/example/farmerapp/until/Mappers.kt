package com.example.farmerapp.until

import com.example.farmerapp.data.local.relations.SaleProductWitOtherClass
import com.example.farmerapp.domain.model.SalesProduct
import com.example.farmerapp.until.extetensions.SalesProductExtensions.toSaleProduct

object Mappers {
    fun salesProductOtherClassListToSaleProductList(listSaleProductWitOtherClass: List<SaleProductWitOtherClass>): List<SalesProduct> {
        return listSaleProductWitOtherClass.map { saleProductWitOtherClass ->
            saleProductWitOtherClass.toSaleProduct()
        }
    }

}

