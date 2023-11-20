package com.example.farmerapp.presentation.customer_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.farmerapp.domain.model.Customer
import com.example.farmerapp.domain.model.Farmer
import com.example.farmerapp.domain.use_case.customer.local.InsertCustomerToLocalUseCase
import com.example.farmerapp.domain.use_case.customer.remote.UpsetCustomerListToApiUseCase
import com.example.farmerapp.domain.use_case.farmer.GetFarmerByNickNameOrPhoneNumberToApiUseCase
import com.example.farmerapp.until.Resource
import com.example.farmerapp.until.extetensions.FarmerExtensions.toCustomer
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CustomerViewModel
@Inject constructor(
    private val insertCustomerUseCase: InsertCustomerToLocalUseCase,
    private val upsetCustomerListToApiUseCase: UpsetCustomerListToApiUseCase,
    private val getFarmerByNickNameOrPhoneNumberToApiUseCase: GetFarmerByNickNameOrPhoneNumberToApiUseCase
) : ViewModel() {
    private val _state = MutableStateFlow<CustomerFragmentState>(CustomerFragmentState.Idle)
    val state: StateFlow<CustomerFragmentState> = _state

    private suspend fun getCustomerPhoneOrNickName(phoneNumberOrNickName: String) {
        getFarmerByNickNameOrPhoneNumberToApiUseCase.getFarmerByNickNameOrPhoneNumber(
            phoneNumberOrNickName
        ).collect {
            when (it) {
                is Resource.Loading -> {
                    _state.value = CustomerFragmentState.Loading
                }

                is Resource.Success -> {
                    upsetCustomerListToApi(it.data!!, LatLng(0.0, 0.0))
                }

                is Resource.Error -> {
                    _state.value = CustomerFragmentState.Error(it.message!!)
                }
            }
        }
    }

    private suspend fun upsetCustomerListToApi(farmer: Farmer, location: LatLng) {
        upsetCustomerListToApiUseCase.upsetCustomerList(farmer, location).collect {
            when (it) {
                is Resource.Loading -> {
                    _state.value = CustomerFragmentState.Loading
                }

                is Resource.Success -> {
                    val farmerApiDto = it.data!!
                    insertCustomerToLocal(farmerApiDto.toCustomer())
                }

                is Resource.Error -> {
                    _state.value = CustomerFragmentState.Error(it.message!!)
                }
            }
        }
    }

    private suspend fun insertCustomerToLocal(customer: Customer) {
        insertCustomerUseCase.insertCustomer(customer).collect {
            when (it) {
                is Resource.Loading -> {
                    _state.value = CustomerFragmentState.Loading
                }

                is Resource.Success -> {
                    _state.value = CustomerFragmentState.FindCustomer(customer)
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
                //  viewModelScope.launch { checkedSavedUser(onEvent.customer) }
            }

            is CustomerFragmentOnEvent.FindCustomerByPhoneNumber -> {
                // viewModelScope.launch { findCustomerWithPhoneNumber(onEvent.phoneNumber) }
            }

            is CustomerFragmentOnEvent.FindCustomerByFarmerId -> {
                viewModelScope.launch { getCustomerPhoneOrNickName(onEvent.phoneOrCustomerNumber) }
            }
        }

    }

}