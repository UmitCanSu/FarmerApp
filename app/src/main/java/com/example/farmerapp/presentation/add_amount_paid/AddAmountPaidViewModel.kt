package com.example.farmerapp.presentation.add_amount_paid

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.farmerapp.domain.model.AmountPaid
import com.example.farmerapp.domain.model.SalesProduct
import com.example.farmerapp.domain.use_case.IsInternetUseCase
import com.example.farmerapp.domain.use_case.amaount_list.AddAmountPaidToApiUseCase
import com.example.farmerapp.domain.use_case.amaount_list.InsertAmountPaidUseCase
import com.example.farmerapp.domain.use_case.amaount_list.RemainingDeptUseCase
import com.example.farmerapp.domain.use_case.customer.local.GetAllCustomerListUseCase
import com.example.farmerapp.domain.use_case.customer.remote.GetCustomerByCompanyIdToApiUseCase
import com.example.farmerapp.domain.use_case.sales_product.GetSaleBySaleIdToApiUseCase
import com.example.farmerapp.domain.use_case.sales_product.SelectSalesProductWithIdUseCase
import com.example.farmerapp.domain.use_case.sales_product.UpdateSalesProductUseCase
import com.example.farmerapp.until.Resource
import com.example.farmerapp.until.Sesion
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class AddAmountPaidViewModel
@Inject constructor(
    private val getAllCustomerListUseCase: GetAllCustomerListUseCase,
    private val getSalesProductWithIdUseCase: SelectSalesProductWithIdUseCase,
    private val updateSalesProductUseCase: UpdateSalesProductUseCase,
    private val insertAmountPaidUseCase: InsertAmountPaidUseCase,
    private val getRemainingDeptUseCase: RemainingDeptUseCase,
    private val addAmountPaidToApiUseCase: AddAmountPaidToApiUseCase,
    private val getCustomerByCompanyIdToApiUseCase: GetCustomerByCompanyIdToApiUseCase,
    private val isInternetUseCase: IsInternetUseCase,
    private val getSaleBySaleIdToApiUseCase: GetSaleBySaleIdToApiUseCase,
    productUseCase: UpdateSalesProductUseCase
) : ViewModel() {
    private val _state =
        MutableStateFlow<AddAmountPaidFragmentState>(AddAmountPaidFragmentState.Idle)
    val state: StateFlow<AddAmountPaidFragmentState> = _state
    private val data = MutableStateFlow(AddAmountPaidData())

    init {
        viewModelScope.launch {
            checkInternet()
        }
    }

    private suspend fun checkInternet() {
        isInternetUseCase.isInternet().collect {
            when (it) {
                is Resource.Loading -> {

                }

                is Resource.Success -> {
                    val isInternet = it.data!!
                    if (isInternet)
                        getCustomerListToApi(Sesion.getInstance().company!!.apiId)
                    else
                        getAllCustomerList()

                }

                is Resource.Error -> {}
            }
        }
    }

    private suspend fun getSaleToApi(saleApiId: String) {
        getSaleBySaleIdToApiUseCase.getSalesBySaleId(saleApiId).collect {
            when (it) {
                is Resource.Loading -> {
                    _state.value = AddAmountPaidFragmentState.Loading
                }

                is Resource.Success -> {
                    data.value = data.value.copy(salesProduct = it.data)
                    getRemainingTotal(it.data!!)
                }

                is Resource.Error -> {
                    _state.value = AddAmountPaidFragmentState.Error(it.message!!)
                }
            }
        }
    }

    private suspend fun getAllCustomerList() {
        getAllCustomerListUseCase.getAllCustomersList().collect {
            when (it) {
                is Resource.Loading -> {
                    _state.value = AddAmountPaidFragmentState.Loading
                }

                is Resource.Success -> {
                     data.value = data.value.copy(customerList = it.data!!)
                     _state.value = AddAmountPaidFragmentState.CustomerList(it.data!!)
                }

                is Resource.Error -> {
                    _state.value = AddAmountPaidFragmentState.Error(it.message!!)
                }
            }
        }
    }

    private suspend fun getCustomerListToApi(companyId: String) {
        getCustomerByCompanyIdToApiUseCase.getCustomerByCompanyIdToApi(companyId).collect {
            when (it) {
                is Resource.Loading -> {
                    _state.value = AddAmountPaidFragmentState.Loading
                }

                is Resource.Success -> {
                    data.value = data.value.copy(customerList = it.data!!)
                    _state.value = AddAmountPaidFragmentState.CustomerList(it.data!!)
                }

                is Resource.Error -> {
                    _state.value = AddAmountPaidFragmentState.Error(it.message!!)
                }
            }
        }
    }

    private suspend fun updateSalesProduct() {
        updateSalesProductUseCase.updateSalesProduct(data.value.salesProduct!!).collect {
            when (it) {
                is Resource.Loading -> {
                    _state.value = AddAmountPaidFragmentState.Loading
                }

                is Resource.Success -> {
                    _state.value = AddAmountPaidFragmentState.IsSavedAmountPaid(it.data!!)
                }

                is Resource.Error -> {
                    _state.value = AddAmountPaidFragmentState.Error(it.message!!)
                }
            }
        }
    }

    private suspend fun getSalesProductToLocal(salesProductId: Int) {
        getSalesProductWithIdUseCase.selectSalesProductWithId(salesProductId).collect {
            when (it) {
                is Resource.Loading -> {
                    _state.value = AddAmountPaidFragmentState.Loading
                }

                is Resource.Success -> {
                    data.value = data.value.copy(salesProduct = it.data)
                    getRemainingDeptToLocal(salesProductId)
                }

                is Resource.Error -> {
                    _state.value = AddAmountPaidFragmentState.Error(it.message!!)
                }
            }
        }
    }

    private fun getRemainingTotal(salesProduct: SalesProduct) {
        var price = 0f
        for (amountPaint in salesProduct.amountPaint) {
            price += amountPaint.price
        }
        val remainingTotal = salesProduct.price - price
        data.value = data.value.copy(remainingDept = remainingTotal)
        _state.value = AddAmountPaidFragmentState.RemainingDept(remainingTotal)
    }

    private suspend fun insertAmountPaid(amountPaid: AmountPaid) {
        insertAmountPaidUseCase.insertAmountPaid(amountPaid).collect {
            when (it) {
                is Resource.Loading -> {
                    _state.value = AddAmountPaidFragmentState.Loading
                }

                is Resource.Success -> {
                    if (checkEnterPriceEqualRemainingDept()) {
                        val salesProduct = data.value.salesProduct!!
                        salesProduct.isPaid = false
                        data.value = data.value.copy(salesProduct = salesProduct)
                        updateSalesProduct()
                    } else {
                        _state.value = AddAmountPaidFragmentState.IsSavedAmountPaid(it.data!!)
                    }
                    addAmountPaidToApi(amountPaid)
                }

                is Resource.Error -> {
                    _state.value = AddAmountPaidFragmentState.Error(it.message!!)
                }
            }
        }
    }

    private suspend fun getRemainingDeptToLocal(salesProductId: Int) {
        getRemainingDeptUseCase.calculateRemainingDept(salesProductId).collect {
            when (it) {
                is Resource.Loading -> {
                    _state.value = AddAmountPaidFragmentState.Loading
                }

                is Resource.Success -> {
                    var remainingDept: Float? = it.data
                    if (remainingDept == null) {
                        remainingDept = data.value.salesProduct!!.price
                    }
                    data.value = data.value.copy(remainingDept = remainingDept)
                    _state.value = AddAmountPaidFragmentState.RemainingDept(remainingDept)
                }

                is Resource.Error -> {
                    _state.value = AddAmountPaidFragmentState.Error(it.message!!)
                }
            }
        }
    }

    private suspend fun addAmountPaidToApi(amountPaid: AmountPaid) {
        val isPaid = !checkEnterPriceEqualRemainingDept();
        addAmountPaidToApiUseCase.addAmountPaid(amountPaid, isPaid).collect {
            when (it) {
                is Resource.Loading -> {
                    _state.value = AddAmountPaidFragmentState.Loading
                }

                is Resource.Success -> {
                    _state.value = AddAmountPaidFragmentState.IsSavedAmountPaid(true)
                }

                is Resource.Error -> {
                    _state.value = AddAmountPaidFragmentState.Error(it.message!!)
                }
            }
        }
    }

    private fun checkEnterPriceBigRemainingDept(): Boolean {
        return (data.value.remainingDept - data.value.enterPrice) >= 0
    }

    private fun checkEnterPriceEqualRemainingDept(): Boolean {
        return (data.value.remainingDept - data.value.enterPrice) == 0f
    }

    fun onEvent(onEvent: AddAmountPaidFragmentOnEvent) {
        when (onEvent) {
            is AddAmountPaidFragmentOnEvent.SaveAmountPaid -> {
                data.value = data.value.copy(enterPrice = onEvent.price)
                val amountPaid = AmountPaid(
                    "",
                    data.value.salesProduct,
                    data.value.customerList[onEvent.selectedCustomerIndex],
                    onEvent.price,
                    LocalDateTime.now()!!,
                    LatLng(0.0, 0.0)
                )
                if (checkEnterPriceBigRemainingDept()) {
                    viewModelScope.launch {
                        if (Sesion.getInstance().isInternet)
                            addAmountPaidToApi(amountPaid)
                        else
                            insertAmountPaid(amountPaid)
                    }
                } else {
                    _state.value = AddAmountPaidFragmentState.Error("")
                }

            }

            is AddAmountPaidFragmentOnEvent.GetSalesProduct -> {
                viewModelScope.launch {
                    if (Sesion.getInstance().isInternet)
                         getSaleToApi(onEvent.saleApiId)
                    else
                        getSalesProductToLocal(onEvent.salesProductId)
                }

            }

            is AddAmountPaidFragmentOnEvent.CalculateAmountPaid -> {
                //   viewModelScope.launch { getRemainingDeptToLocal(onEvent.salesProductId) }
            }
        }


    }
}
