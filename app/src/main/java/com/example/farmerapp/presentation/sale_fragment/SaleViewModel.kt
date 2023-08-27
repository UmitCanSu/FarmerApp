package com.example.farmerapp.presentation.sale_fragment

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.farmerapp.domain.model.SalesProduct
import com.example.farmerapp.domain.use_case.GetLocationUseCase
import com.example.farmerapp.domain.use_case.customer.GetAllCustomerListUseCase
import com.example.farmerapp.domain.use_case.product.GetProductListWithCompanyIdUseCase
import com.example.farmerapp.domain.use_case.sale_fragment.CalculateProductPriceUseCase
import com.example.farmerapp.domain.use_case.sales_product.InsertSalesProductUseCase
import com.example.farmerapp.until.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class SaleViewModel
@Inject constructor(
    private val getProductListWithCompanyId: GetProductListWithCompanyIdUseCase,
    private val calculateProductPriceUseCase: CalculateProductPriceUseCase,
    private val getAllCustomerList: GetAllCustomerListUseCase,
    private val insertSalesProductUseCase: InsertSalesProductUseCase,
    private val getLocationUseCase: GetLocationUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow<SaleFragmentState>(SaleFragmentState.Idle)
    val state: StateFlow<SaleFragmentState> = _state
    private val salesProductHolder = HolderSelectedProduct()

    init {
        viewModelScope.launch {
            getProductList()
            getCustomerList()
            getLocation()
        }
    }

    private suspend fun getProductList() {
        getProductListWithCompanyId.getProductListWithCompanyId(1)
            .collect {
                when (it) {
                    is Resource.Loading -> {
                        _state.value = SaleFragmentState.Loading
                    }

                    is Resource.Success -> {
                        salesProductHolder.productList = it.data ?: emptyList()
                        _state.value =
                            SaleFragmentState.ProdcutList(productList = it.data ?: emptyList())
                    }

                    is Resource.Error -> {
                        _state.value = SaleFragmentState.Error(it.message!!)
                    }
                }
            }
    }

    private fun calculateProductPrice(count: Int) {
        viewModelScope.launch {
            calculateProductPriceUseCase.calculateProductPrice(salesProductHolder.product!!, count)
                .collect {
                    when (it) {
                        is Resource.Loading -> {
                            _state.value = SaleFragmentState.Loading
                        }

                        is Resource.Success -> {
                            salesProductHolder.price = it.data ?: 0
                            _state.value = SaleFragmentState.Calculate((it.data ?: 0).toString())
                        }

                        is Resource.Error -> {
                            _state.value = SaleFragmentState.Error(it.message!!)
                        }
                    }
                }
        }
    }

    private suspend fun getCustomerList() {
        getAllCustomerList.getAllCustomersList().collect {
            when (it) {
                is Resource.Loading -> {
                    _state.value = SaleFragmentState.Loading
                }

                is Resource.Success -> {
                    salesProductHolder.customerList = it.data!!
                    _state.value = SaleFragmentState.CustomerList(it.data)
                }

                is Resource.Error -> {
                    _state.value = SaleFragmentState.Error(it.message!!)
                }
            }
        }
    }

    private suspend fun insertSalesProduct() {
        val localDateTime = LocalDateTime.now()
        val salesProduct = with(salesProductHolder) {
            SalesProduct(
                product!!,
                customer!!,
                price,
                isDept,
                productNumber,
                location,
                locationDescription,
                localDateTime,
                true,
                emptyList()
            )
        }

        insertSalesProductUseCase.insertSaleProduct(salesProduct).collect {
            when (it) {
                is Resource.Loading -> {
                    _state.value = SaleFragmentState.Loading
                }

                is Resource.Success -> {
                    _state.value = SaleFragmentState.IsSavesSalesProduct(it.data!!.toInt())
                }

                is Resource.Error -> {
                    _state.value = SaleFragmentState.Error(it.message!!)
                }
            }
        }
    }

    private suspend fun getLocation() {
        val jop = Job()
        getLocationUseCase.getLocation()
            .onStart { jop.join() }
            .collect {
                when (it) {
                    is Resource.Loading -> {
                        _state.value = SaleFragmentState.Loading
                    }

                    is Resource.Success -> {
                        salesProductHolder.location = it.data!!
                        jop.cancel()
                    }

                    is Resource.Error -> {
                        _state.value = SaleFragmentState.Error(it.message!!)
                    }
                }
            }
    }

    fun onEvent(onEvent: SaleFragmentOnEvent) {
        when (onEvent) {
            is SaleFragmentOnEvent.CalculatePrice -> {
                salesProductHolder.productNumber = onEvent.productCount
                calculateProductPrice(onEvent.productCount)

            }

            is SaleFragmentOnEvent.SelectProduct -> {
                val product = salesProductHolder.productList[onEvent.productIndex]
                salesProductHolder.product = product
                _state.value = SaleFragmentState.SelectedProduct(product)

            }

            is SaleFragmentOnEvent.SelectCustomer -> {
                salesProductHolder.customer = salesProductHolder.customerList[onEvent.customerIndex]
            }

            is SaleFragmentOnEvent.Save -> {
                viewModelScope.launch { insertSalesProduct() }
            }

            is SaleFragmentOnEvent.isDept -> {
                salesProductHolder.isDept = onEvent.isCheck
            }
        }
    }

}