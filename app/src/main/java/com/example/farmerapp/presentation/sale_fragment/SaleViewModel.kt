package com.example.farmerapp.presentation.sale_fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.farmerapp.domain.use_case.product.GetAllProductUseCase
import com.example.farmerapp.until.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SaleViewModel
@Inject constructor(
    private val getAllProductUseCase: GetAllProductUseCase
) : ViewModel() {
    private val _state = MutableStateFlow<SaleFragmentState>(SaleFragmentState.Idle)
    val state: StateFlow<SaleFragmentState> = _state

    init {
        getProductList()
    }


    private fun getProductList() {
        viewModelScope.launch {
            getAllProductUseCase.getAllProduct().onEach {
                when (it) {
                    is Resource.Loading -> {
                        _state.value = SaleFragmentState.Loading
                    }

                    is Resource.Success -> {
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
        _state.value.product?.let { selectedProduct ->
            val amount = selectedProduct.price * count
            _state.value = _state.value.copy(amountPrice = amount)

        }

    }

    fun onEvent(onEvent: SaleFragmentOnEvent) {
        when (onEvent) {
            is SaleFragmentOnEvent.CalculatePrice -> {
                calculateProductPrice(onEvent.productCount)
            }

            is SaleFragmentOnEvent.SelectProduct -> {
                _state.value = _state.value.copy(product = onEvent.product)
            }

        }
    }

}