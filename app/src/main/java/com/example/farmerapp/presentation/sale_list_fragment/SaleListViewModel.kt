package com.example.farmerapp.presentation.sale_list_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.farmerapp.domain.model.SalesProduct
import com.example.farmerapp.domain.use_case.sales_list_fragment.SalesListFilterUseCase
import com.example.farmerapp.domain.use_case.sales_product.SelectSalesProductWithCompanyIdUseCase
import com.example.farmerapp.until.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SaleListViewModel
@Inject constructor(
    private val selectSalesProductWithCompanyIdUseCase: SelectSalesProductWithCompanyIdUseCase,
    private val salesProductListFilter: SalesListFilterUseCase
) : ViewModel() {
    private val _state = MutableStateFlow<SaleListState>(SaleListState.Idle)
    val state: StateFlow<SaleListState> = _state
    private var salesProductList: List<SalesProduct> = ArrayList()

    init {
        viewModelScope.launch {
            selectWithCompanyId(1)
            getFilter(1, 1, false)
        }
    }


    private suspend fun selectWithCompanyId(companyId: Int) {
        selectSalesProductWithCompanyIdUseCase.selectSalesProductWithCompanyId(companyId).collect {
            when (it) {
                is Resource.Loading -> {
                    _state.value = SaleListState.Loading
                }

                is Resource.Success -> {
                    salesProductList = it.data!!
                    _state.value = SaleListState.SaleList(it.data!!)
                }

                is Resource.Error -> {
                    _state.value = SaleListState.Error(it.message!!)
                }
            }
        }
    }

    private fun getFilter(customerId: Int?, productId: Int?, isPaid: Boolean?) {
        salesProductList = salesProductList.filter {
            it.customer.id == customerId && it.product.id == productId && it.isPaid == isPaid
        }
        _state.value = SaleListState.SaleList(salesProductList)
    }

    private fun isPaidFilter(isPaid: Boolean) {
        salesProductList = salesProductList.filter {
            it.isPaid == isPaid
        }
        _state.value = SaleListState.SaleList(salesProductList)
    }

    fun onEvent(onEvent: SaleListOnEvent) {
        when (onEvent) {
            is SaleListOnEvent.SaleList -> {
                viewModelScope.launch { selectWithCompanyId(onEvent.companyId) }
            }

            is SaleListOnEvent.IsPaid -> {
                isPaidFilter(onEvent.isPaid)
            }
        }
    }

}