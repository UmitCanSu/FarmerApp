package com.example.farmerapp.presentation.product_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.farmerapp.domain.use_case.product.GetProductListByCompanyIdToApiUseCase
import com.example.farmerapp.domain.use_case.product.GetProductListByCompanyIdUseCase
import com.example.farmerapp.until.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel
@Inject constructor(
    private val getProductListByCompanyIdUseCase: GetProductListByCompanyIdUseCase,
    private val getProductListByCompanyIdToApiUseCase: GetProductListByCompanyIdToApiUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow<ProductListState>(ProductListState.Idle)
    val state: StateFlow<ProductListState> = _state

    init {
        viewModelScope.launch { getProductListByCompanyId(1) }
    }

    private suspend fun getProductListByCompanyId(companyId: Int) {
        getProductListByCompanyIdUseCase.getProductListWithCompanyId(companyId).collect {
            when (it) {
                is Resource.Loading -> {
                    _state.value = ProductListState.Loading
                }

                is Resource.Success -> {
                  //  _state.value = ProductListState.ProductList(it.data!!)
                    getProductListByCompanyIdBy(companyId.toString())
                }

                is Resource.Error -> {
                    _state.value = ProductListState.Error(it.message!!)
                }
            }
        }
    }
    private suspend fun getProductListByCompanyIdBy(companyId: String){
        getProductListByCompanyIdToApiUseCase.getProductList(companyId).collect{
            when (it) {
                is Resource.Loading -> {
                    _state.value = ProductListState.Loading
                }

                is Resource.Success -> {
                    _state.value = ProductListState.ProductList(it.data!!)
                }

                is Resource.Error -> {
                    _state.value = ProductListState.Error(it.message!!)
                }
            }
        }
    }
}