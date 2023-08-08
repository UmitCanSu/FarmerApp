package com.example.farmerapp.presentation.sale_fragment

import com.example.farmerapp.domain.model.Product

data class SaleFragmentData(
    val productList: List<Product> = emptyList(),
    val selectedProduct: Product,
    val salesCount: Int,
    val amountPrice: Int
)