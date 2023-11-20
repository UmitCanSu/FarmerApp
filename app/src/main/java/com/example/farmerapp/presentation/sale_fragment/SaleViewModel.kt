package com.example.farmerapp.presentation.sale_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.farmerapp.domain.model.SalesProduct
import com.example.farmerapp.domain.use_case.GetLocationUseCase
import com.example.farmerapp.domain.use_case.IsInternetUseCase
import com.example.farmerapp.domain.use_case.customer.local.GetAllCustomerListUseCase
import com.example.farmerapp.domain.use_case.customer.remote.GetCustomerListToApiAndSavedCustomerListUseCase
import com.example.farmerapp.domain.use_case.product.remote.GetProductListByCompanyIdToApiAndSavedProductListToLocalUseCase
import com.example.farmerapp.domain.use_case.product.local.GetProductListByCompanyIdToLocalUseCase
import com.example.farmerapp.domain.use_case.sale_fragment.CalculateProductPriceUseCase
import com.example.farmerapp.domain.use_case.sales_product.InsertSalesProductToLocalUseCase
import com.example.farmerapp.domain.use_case.sales_product.view.SaveApiAfterLocalUseCase
import com.example.farmerapp.until.Resource
import com.example.farmerapp.until.Sesion
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
    private val getProductListByCompanyId: GetProductListByCompanyIdToLocalUseCase,
    private val calculateProductPriceUseCase: CalculateProductPriceUseCase,
    private val insertSalesProductUseCase: InsertSalesProductToLocalUseCase,
    private val getLocationUseCase: GetLocationUseCase,
    private val isInternetUseCase: IsInternetUseCase,
    //
    private val getCustomerListToApiAndSavedCustomerListUseCase: GetCustomerListToApiAndSavedCustomerListUseCase,
    private val getProductListByCompanyIdToApiAndSavedProductListToLocalUseCase: GetProductListByCompanyIdToApiAndSavedProductListToLocalUseCase,
    private val saveApiAfterLocalUseCase: SaveApiAfterLocalUseCase,
    private val getAllCustomerListUseCase: GetAllCustomerListUseCase
) : ViewModel() {
    private val _state = MutableStateFlow<SaleFragmentState>(SaleFragmentState.Idle)
    val state: StateFlow<SaleFragmentState> = _state
    private val salesProductHolder = HolderSelectedProduct()

    init {
        viewModelScope.launch {
            isInternet()
        }
    }

    private suspend fun isInternet() {
        isInternetUseCase.isInternet().collect {
            when (it) {
                is Resource.Loading -> {
                    _state.value = SaleFragmentState.Loading
                }

                is Resource.Success -> {
                    val company = Sesion.getInstance().company!!
                    if (it.data!!) {
                        getProductList(company.apiId, company.id)
                        getCustomerList(company.apiId, company.id)
                    } else {
                        getProductListByCompanyIdToLocal(company.id)
                        getCustomerListToLocal()
                    }
                }

                is Resource.Error -> {
                    _state.value = SaleFragmentState.Error(it.message!!)
                }
            }
        }
    }

    private suspend fun getProductListByCompanyIdToLocal(companyId: Int) {
        getProductListByCompanyId.getProductListWithCompanyId(companyId)
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

    private suspend fun getCustomerListToLocal() {
        getAllCustomerListUseCase.getAllCustomersList().collect {
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

    private suspend fun getProductList(companyApiId: String, companyLocalId: Int) {
        getProductListByCompanyIdToApiAndSavedProductListToLocalUseCase.getProductList(
            companyApiId,
            companyLocalId
        ).collect {
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
                            salesProductHolder.price = it.data ?: 0f
                            _state.value = SaleFragmentState.Calculate((it.data ?: 0).toString())
                        }

                        is Resource.Error -> {
                            _state.value = SaleFragmentState.Error(it.message!!)
                        }
                    }
                }
        }
    }

    private suspend fun insertSalesProduct(salesProduct: SalesProduct) {
        insertSalesProductUseCase.insertSaleProduct(salesProduct).collect {
            when (it) {
                is Resource.Loading -> {
                    _state.value = SaleFragmentState.Loading
                }

                is Resource.Success -> {
                    _state.value = SaleFragmentState.IsSavesSalesProduct(
                        it.data!!.id,
                        it.data.apiId
                    )
                }

                is Resource.Error -> {
                    _state.value = SaleFragmentState.Error(it.message!!)
                }
            }
        }
    }

    private suspend fun addSaleToApiAfterLocal(salesProduct: SalesProduct) {
        saveApiAfterLocalUseCase.saveApiAfterLocal(salesProduct).collect {
            when (it) {
                is Resource.Loading -> {
                    _state.value = SaleFragmentState.Loading
                }

                is Resource.Success -> {
                    _state.value = SaleFragmentState.IsSavesSalesProduct(
                        it.data!!.id,
                        it.data.apiId
                    )
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

    private fun getSalesProduct(): SalesProduct {
        val localDateTime = LocalDateTime.now()
        return with(salesProductHolder) {
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
    }

    private suspend fun getCustomerList(companyId: String, companyLocalId: Int) {
        getCustomerListToApiAndSavedCustomerListUseCase.getCustomer(companyId, companyLocalId)
            .collect {
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
                viewModelScope.launch {
                    val salesProduct = getSalesProduct()
                    if (Sesion.getInstance().isInternet) {
                        addSaleToApiAfterLocal(salesProduct)
                    } else {
                        insertSalesProduct(salesProduct)
                    }
                }
            }

            is SaleFragmentOnEvent.isDept -> {
                salesProductHolder.isDept = onEvent.isCheck
            }
        }
    }

}