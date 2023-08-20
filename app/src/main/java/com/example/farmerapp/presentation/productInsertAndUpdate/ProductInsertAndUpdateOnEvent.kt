package com.example.farmerapp.presentation.productInsertAndUpdate

import com.example.farmerapp.domain.model.Product

sealed class ProductInsertAndUpdateOnEvent {
    data class insertProduct(val product: Product) : ProductInsertAndUpdateOnEvent()
    data class updateProduct(val product: Product) : ProductInsertAndUpdateOnEvent()
}