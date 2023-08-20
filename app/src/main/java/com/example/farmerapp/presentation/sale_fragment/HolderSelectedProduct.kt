package com.example.farmerapp.presentation.sale_fragment

import com.example.farmerapp.domain.model.Customer
import com.example.farmerapp.domain.model.Product
import java.time.LocalDateTime

data class HolderSelectedProduct(
    var productList: List<Product> = emptyList(),
    var customerList: List<Customer> = emptyList(),
    var product: Product ?= null,
    var customer: Customer ?= null,
    var price: Int =0,
    var isDept: Boolean = false,
    var productNumber: Int = 0,
    var location: Float = 0f,
    var locationDescription: String = "",
){

}
