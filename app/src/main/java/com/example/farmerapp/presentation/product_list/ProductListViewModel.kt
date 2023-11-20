package com.example.farmerapp.presentation.product_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.farmerapp.domain.use_case.IsInternetUseCase
import com.example.farmerapp.domain.use_case.product.remote.GetProductListByCompanyIdToApiAndSavedProductListToLocalUseCase
import com.example.farmerapp.domain.use_case.product.remote.GetProductListByCompanyIdToApiUseCase
import com.example.farmerapp.domain.use_case.product.local.GetProductListByCompanyIdToLocalUseCase
import com.example.farmerapp.until.Resource
import com.example.farmerapp.until.Sesion
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel
@Inject constructor(
    private val getProductListByCompanyIdUseCase: GetProductListByCompanyIdToLocalUseCase,
    private val getProductListByCompanyIdToApiUseCase: GetProductListByCompanyIdToApiUseCase,
    private val isInternetUseCase: IsInternetUseCase,
    private val getProductListByCompanyIdToApiAndSavedProductListToLocalUseCase: GetProductListByCompanyIdToApiAndSavedProductListToLocalUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow<ProductListState>(ProductListState.Idle)
    val state: StateFlow<ProductListState> = _state

    init {
        viewModelScope.launch { isInternet() }
    }

    private suspend fun isInternet() {
        isInternetUseCase.isInternet().collect {
            when (it) {
                is Resource.Loading -> {
                    _state.value = ProductListState.Loading
                }

                is Resource.Success -> {
                    val company = Sesion.getInstance().company!!
                    if (it.data!!) {
                        getProductListByCompanyIdToApiAndSavedProductListToLocal(
                            company.apiId,
                            company.id
                        )
                    } else {
                        getProductListByCompanyIdToLocal(company.id)
                    }
                }

                is Resource.Error -> {
                    _state.value = ProductListState.Error(it.message!!)
                }
            }
        }
    }

    private suspend fun getProductListByCompanyIdToApiAndSavedProductListToLocal(
        apiId: String,
        localId: Int
    ) {
        getProductListByCompanyIdToApiAndSavedProductListToLocalUseCase.getProductList(
            apiId,
            localId
        ).collect {
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

    private suspend fun getProductListByCompanyIdToLocal(companyLocalId: Int) {
        getProductListByCompanyIdUseCase.getProductListWithCompanyId(companyLocalId)
            .collect {
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

    private suspend fun getProductListByCompanyIdByToApi(companyApiId: String) {
        getProductListByCompanyIdToApiUseCase.getProductList(companyApiId).collect {
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