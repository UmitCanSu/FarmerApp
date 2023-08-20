package com.example.farmerapp.presentation.sales_detail_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.farmerapp.domain.use_case.amaount_list.SelectAmountPaidWithSalesProductIdUseCase
import com.example.farmerapp.domain.use_case.sales_product.SelectSalesProductWithIdUseCase
import com.example.farmerapp.until.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SaleDetailViewModel
@Inject constructor(
    private val selectSalesProductWithIdUseCase: SelectSalesProductWithIdUseCase,
    private val selectAmountPaidWithSalesProductIdUseCase: SelectAmountPaidWithSalesProductIdUseCase
) : ViewModel() {
    private val _state = MutableStateFlow<SaleDetailState>(SaleDetailState.Idle)
    val state: SharedFlow<SaleDetailState> = _state
    private suspend fun getSalesProductWithId(salesProductId: Int) {
        selectSalesProductWithIdUseCase.selectSalesProductWithId(salesProductId).collect {
            when (it) {
                is Resource.Loading -> {
                    _state.value = SaleDetailState.Loading
                }

                is Resource.Success -> {
                    _state.value = SaleDetailState.ShowSalesProduct(it.data!!)
                    getAmountPaidListWithSalesProductID(salesProductId)
                }

                is Resource.Error -> {
                    _state.value = SaleDetailState.Error(it.message!!)
                }
            }
        }
    }

    private suspend fun getAmountPaidListWithSalesProductID(salesProductId: Int) {
        selectAmountPaidWithSalesProductIdUseCase.selectAmountPaidWithSalesProductId(salesProductId)
            .collect {
                when (it) {
                    is Resource.Loading -> {
                        _state.value = SaleDetailState.Loading
                    }

                    is Resource.Success -> {
                        _state.value = SaleDetailState.ShowAmountPaidList(it.data!!)
                    }

                    is Resource.Error -> {
                        _state.value = SaleDetailState.Error(it.message!!)
                    }
                }
            }
    }

    fun onEvent(onEvent: SaleDetailOnEvent) {
        when (onEvent) {
            is SaleDetailOnEvent.SelectProduct -> {
                viewModelScope.launch {
                    getSalesProductWithId(onEvent.salesProductID)
                }
            }
        }
    }

}