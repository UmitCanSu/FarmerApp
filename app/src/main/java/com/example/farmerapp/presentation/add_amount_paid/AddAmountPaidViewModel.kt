package com.example.farmerapp.presentation.add_amount_paid

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.farmerapp.domain.model.AmountPaid
import com.example.farmerapp.domain.use_case.amaount_list.AddAmountPaidToApiUseCase
import com.example.farmerapp.domain.use_case.amaount_list.InsertAmountPaidUseCase
import com.example.farmerapp.domain.use_case.amaount_list.RemainingDeptUseCase
import com.example.farmerapp.domain.use_case.customer.GetAllCustomerListUseCase
import com.example.farmerapp.domain.use_case.sales_product.SelectSalesProductWithIdUseCase
import com.example.farmerapp.domain.use_case.sales_product.UpdateSalesProductUseCase
import com.example.farmerapp.until.Resource
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
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
) : ViewModel() {
    private val _state =
        MutableStateFlow<AddAmountPaidFragmentState>(AddAmountPaidFragmentState.Idle)
    val state: StateFlow<AddAmountPaidFragmentState> = _state
    private val data = MutableStateFlow(AddAmountPaidData())

    init {
        viewModelScope.launch { getAllCustomerList() }
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

    private suspend fun getSalesProduct(salesProductId: Int) {
        getSalesProductWithIdUseCase.selectSalesProductWithId(salesProductId).collect {
            when (it) {
                is Resource.Loading -> {
                    _state.value = AddAmountPaidFragmentState.Loading
                }

                is Resource.Success -> {
                    data.value = data.value.copy(salesProduct = it.data)
                    getRemainingDept(salesProductId)
                }

                is Resource.Error -> {
                    _state.value = AddAmountPaidFragmentState.Error(it.message!!)
                }
            }
        }
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

    private suspend fun getRemainingDept(salesProductId: Int) {
        getRemainingDeptUseCase.calculateRemainingDept(salesProductId).collect {
            when (it) {
                is Resource.Loading -> {
                    _state.value = AddAmountPaidFragmentState.Loading
                }

                is Resource.Success -> {
                    var remainingDept:Float = it.data!!
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

    private suspend fun addAmountPaidToApi(amountPaid: AmountPaid){
        addAmountPaidToApiUseCase.addAmountPaid(amountPaid).collect{
            when (it) {
                is Resource.Loading -> {
                    _state.value = AddAmountPaidFragmentState.Loading
                }

                is Resource.Success -> {
                    it.data
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
                    data.value.salesProduct,
                    data.value.customerList[onEvent.selectedCustomerIndex],
                    onEvent.price,
                    LocalDateTime.now()!!,
                    LatLng(0.0,0.0)
                )
                if (checkEnterPriceBigRemainingDept()) {
                    viewModelScope.launch { insertAmountPaid(amountPaid) }
                } else {
                    _state.value = AddAmountPaidFragmentState.Error("")
                }
                viewModelScope.launch { addAmountPaidToApi(amountPaid) }
            }

            is AddAmountPaidFragmentOnEvent.GetSalesProduct -> {
                viewModelScope.launch { getSalesProduct(onEvent.salesProductId) }
            }

            is AddAmountPaidFragmentOnEvent.CalculateAmountPaid -> {
                //   viewModelScope.launch { getRemainingDept(onEvent.salesProductId) }
            }
        }


    }
}
