package com.example.farmerapp.presentation.sing_in_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.farmerapp.domain.model.Company
import com.example.farmerapp.domain.model.Farmer
import com.example.farmerapp.domain.model.Login
import com.example.farmerapp.domain.use_case.IsInternetUseCase
import com.example.farmerapp.domain.use_case.company.AddCompanyToApi
import com.example.farmerapp.domain.use_case.company.InsertCompanyToLocalUseCase
import com.example.farmerapp.domain.use_case.company.SelectCompanyByCompanyApiIdUseCase
import com.example.farmerapp.domain.use_case.farmer.AddFarmerToApiUseCase
import com.example.farmerapp.domain.use_case.farmer.InsertFarmerToLocalUseCase
import com.example.farmerapp.domain.use_case.login_fragment.AddLoginToLocalUseCase
import com.example.farmerapp.until.Resource
import com.example.farmerapp.until.extetensions.FarmerExtensions.toFarmerApiDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SingInViewModel
@Inject constructor(
    private val addFarmerToApiUseCase: AddFarmerToApiUseCase,
    private val addFarmerToLocalUseCase: InsertFarmerToLocalUseCase,
    private val addLoginToLocalUseCase: AddLoginToLocalUseCase,
    private val isInternetUseCase: IsInternetUseCase,
    private val insertCompanyToLocalUseCase: InsertCompanyToLocalUseCase,
    private val addCompanyToApiUseCase: AddCompanyToApi,
    private val checkCompanyByCompanyId: SelectCompanyByCompanyApiIdUseCase
) : ViewModel() {
    private val _state = MutableStateFlow<SingInFragmentState>(SingInFragmentState.Idle)
    val state: StateFlow<SingInFragmentState> = _state

    init {
        viewModelScope.launch { isInternet() }
    }

    private suspend fun isInternet() {
        isInternetUseCase.isInternet().collect {
            when (it) {
                is Resource.Loading -> {
                    _state.value = SingInFragmentState.Loading
                }

                is Resource.Success -> {
                    _state.value = SingInFragmentState.IsInternet(it.data!!)
                }

                is Resource.Error -> {
                    _state.value = SingInFragmentState.Error(it.message!!)
                }
            }
        }
    }

    private suspend fun addCompanyToApi(
        company: Company,
        farmer: Farmer,
        login: Login
    ) {
        addCompanyToApiUseCase.addCompanyToApi(company).collect {
            when (it) {
                is Resource.Loading -> {
                    _state.value = SingInFragmentState.Loading
                }

                is Resource.Success -> {
                    addCompanyToLocalDatabase(it.data!!, farmer, login)
                }

                is Resource.Error -> {
                    _state.value = SingInFragmentState.Error(it.message!!)
                }
            }
        }
    }

    private suspend fun addCompanyToLocalDatabase(
        company: Company,
        farmer: Farmer,
        login: Login
    ) {
        insertCompanyToLocalUseCase.insertCompany(company).collect {
            when (it) {
                is Resource.Loading -> {
                    _state.value = SingInFragmentState.Loading
                }

                is Resource.Success -> {
                    farmer.company = company
                    addFarmerToApi(farmer, login)
                }

                is Resource.Error -> {
                    _state.value = SingInFragmentState.Error(it.message!!)
                }
            }
        }
    }

    private suspend fun addFarmerToApi(farmer: Farmer, login: Login) {
        addFarmerToApiUseCase.addFarmer(farmer.toFarmerApiDto()).collect {
            when (it) {
                is Resource.Loading -> {
                    _state.value = SingInFragmentState.Loading
                }

                is Resource.Success -> {
                    farmer.farmerApiId = it.data!!.id
                    addFarmerToLocal(farmer, login)
                }

                is Resource.Error -> {
                    _state.value = SingInFragmentState.Error(it.message!!)
                }
            }
        }
    }

    private suspend fun addFarmerToLocal(farmer: Farmer, login: Login) {
        addFarmerToLocalUseCase.insertFarmer(farmer).collect {
            when (it) {
                is Resource.Loading -> {
                    _state.value = SingInFragmentState.Loading
                }

                is Resource.Success -> {
                    addLogin(login, it.data!!.toInt())
                }

                is Resource.Error -> {
                    _state.value = SingInFragmentState.Error(it.message!!)
                }
            }
        }
    }

    private suspend fun addLogin(login: Login, farmerId: Int) {
        addLoginToLocalUseCase.addLogin(login, farmerId).collect {
            when (it) {
                is Resource.Loading -> {
                    _state.value = SingInFragmentState.Loading
                }

                is Resource.Success -> {
                    _state.value = SingInFragmentState.Success
                }

                is Resource.Error -> {
                    _state.value = SingInFragmentState.Error(it.message!!)
                }
            }
        }
    }

    fun onEvent(onEvent: SingInFragmentOnEvent) {
        when (onEvent) {
            is SingInFragmentOnEvent.Save -> {
                viewModelScope.launch {
                    addCompanyToApi(onEvent.company, onEvent.farmer, onEvent.login)
                }
            }
        }
    }

}