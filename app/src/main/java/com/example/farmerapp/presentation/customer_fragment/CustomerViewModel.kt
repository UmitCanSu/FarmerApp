package com.example.farmerapp.presentation.customer_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.farmerapp.domain.model.Customer
import com.example.farmerapp.domain.use_case.customer.AddCustomerToApiUseCase
import com.example.farmerapp.domain.use_case.customer.InsertCustomerUseCase
import com.example.farmerapp.domain.use_case.customer.SelectCustomerWithPhoneNumber
import com.example.farmerapp.until.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CustomerViewModel
@Inject constructor(
    private val insertCustomerUseCase: InsertCustomerUseCase,
    private val selectCustomerWithPhoneNumber: SelectCustomerWithPhoneNumber,
    private val addCustomerToApiUseCase: AddCustomerToApiUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow<CustomerFragmentState>(CustomerFragmentState.Idle)
    val state: StateFlow<CustomerFragmentState> = _state

    private suspend fun saveCustomer(customer: Customer) {
        insertCustomerUseCase.insertCustomer(customer).collect {
            when (it) {
                is Resource.Loading -> {
                    _state.value = CustomerFragmentState.Loading
                }

                is Resource.Success -> {
                   // _state.value = CustomerFragmentState.SaveCustomer(it.data!!)
                    addCustomer(customer)
                }

                is Resource.Error -> {
                    _state.value = CustomerFragmentState.Error(it.message!!)
                }
            }
        }
    }

    private suspend fun checkedSavedUser(customer: Customer) {
        selectCustomerWithPhoneNumber.selectCustomerWithPhoneNumber(customer.phone).collect {
            when (it) {
                is Resource.Loading -> {
                    _state.value = CustomerFragmentState.Loading
                }

                is Resource.Success -> {
                    if (it.data != null) {
                        _state.value = CustomerFragmentState.Error("Find User")
                    } else {
                        saveCustomer(customer)
                    }
                }

                is Resource.Error -> {
                    _state.value = CustomerFragmentState.Error(it.message!!)
                }
            }
        }
    }

    private suspend fun findCustomerWithPhoneNumber(phoneNumber: String) {
        selectCustomerWithPhoneNumber.selectCustomerWithPhoneNumber(phoneNumber).collect {
            when (it) {
                is Resource.Loading -> {
                    _state.value = CustomerFragmentState.Loading
                }

                is Resource.Success -> {
                    if (it.data != null) {
                        _state.value = CustomerFragmentState.FindCustomer(it.data)
                    } else {
                        _state.value = CustomerFragmentState.Error("Can not find user")
                    }
                }

                is Resource.Error -> {
                    _state.value = CustomerFragmentState.Error(it.message!!)
                }
            }
        }
    }

    private suspend fun addCustomer(customer: Customer){
        addCustomerToApiUseCase.addCustomer(customer).collect{
            when(it){
                is Resource.Loading -> {
                    _state.value = CustomerFragmentState.Loading
                }

                is Resource.Success -> {
                    if (it.data != null) {
                        _state.value = CustomerFragmentState.FindCustomer(it.data)
                    } else {
                        _state.value = CustomerFragmentState.Error("Can not find user")
                    }
                }

                is Resource.Error -> {
                    _state.value = CustomerFragmentState.Error(it.message!!)
                }
            }
        }
    }

    fun onEvent(onEvent: CustomerFragmentOnEvent) {
        when (onEvent) {
            is CustomerFragmentOnEvent.SavedCustomer -> {
                viewModelScope.launch { checkedSavedUser(onEvent.customer) }
            }

            is CustomerFragmentOnEvent.FindCustomerWithPhoneNumber -> {
                viewModelScope.launch { findCustomerWithPhoneNumber(onEvent.phoneNumber) }
            }
        }

    }

}