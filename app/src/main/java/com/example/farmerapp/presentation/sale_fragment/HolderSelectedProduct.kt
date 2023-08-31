package com.example.farmerapp.presentation.sale_fragment

import com.example.farmerapp.domain.model.Customer
import com.example.farmerapp.domain.model.Product
import com.google.android.gms.maps.model.LatLng

data class HolderSelectedProduct(
    var productList: List<Product> = emptyList(),
    var customerList: List<Customer> = emptyList(),
    var product: Product? = null,
    var customer: Customer? = null,
    var price: Float = 0f,
    var isDept: Boolean = false,
    var productNumber: Int = 0,
    var location: LatLng = LatLng(0.0,0.0),
    var locationDescription: String = "",
)
