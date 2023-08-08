package com.example.farmerapp.presentation.sale_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.farmerapp.domain.model.Product
import com.example.farmerapp.domain.use_case.product.GetAllProductUseCase
import com.example.farmerapp.domain.use_case.product.GetProductListWithCompanyIdUseCase
import com.example.farmerapp.domain.use_case.sale_fragment.CalculateProductPriceUseCase
import com.example.farmerapp.until.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SaleViewModel
@Inject constructor(
    private val getProductListWithCompanyId: GetProductListWithCompanyIdUseCase,
    private val calculateProductPriceUseCase: CalculateProductPriceUseCase
) : ViewModel() {
    private val _state = MutableStateFlow<SaleFragmentState>(SaleFragmentState.Idle)
    val state: StateFlow<SaleFragmentState> = _state
    private var productList: List<Product> = emptyList()
    private lateinit var selectedProduct: Product

    init {
        getProductList()
    }

    private fun getProductList() {
        viewModelScope.launch {
            getProductListWithCompanyId.getProductListWithCompanyId(1)
                .collect {
                    when (it) {
                        is Resource.Loading -> {
                            _state.value = SaleFragmentState.Loading
                        }

                        is Resource.Success -> {
                            productList = it.data ?: emptyList()
                            _state.value =
                                SaleFragmentState.ProdcutList(productList = it.data ?: emptyList())
                        }

                        is Resource.Error -> {
                            _state.value = SaleFragmentState.Error(it.message!!)
                        }
                    }
                }
        }
    }


    private fun calculateProductPrice(count: Int) {
        viewModelScope.launch {
            calculateProductPriceUseCase.calculateProductPrice(selectedProduct, count).collect {
                when (it) {
                    is Resource.Loading -> {
                        _state.value = SaleFragmentState.Loading
                    }

                    is Resource.Success -> {
                        _state.value = SaleFragmentState.Calculate((it.data ?: 0).toString())
                    }

                    is Resource.Error -> {
                        _state.value = SaleFragmentState.Error(it.message!!)
                    }
                }
            }
        }
    }

    fun onEvent(onEvent: SaleFragmentOnEvent) {
        when (onEvent) {
            is SaleFragmentOnEvent.CalculatePrice -> calculateProductPrice(onEvent.productCount)

            is SaleFragmentOnEvent.SelectProduct -> {
                val product = productList[onEvent.productIndex]
                selectedProduct = product
                _state.value = SaleFragmentState.SelectedProduct(product)

            }

        }
    }

}