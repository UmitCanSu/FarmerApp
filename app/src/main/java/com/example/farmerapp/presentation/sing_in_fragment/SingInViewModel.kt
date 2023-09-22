package com.example.farmerapp.presentation.sing_in_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.farmerapp.data.remote.dto.CompanyApiDto
import com.example.farmerapp.data.remote.dto.FarmerApiDto
import com.example.farmerapp.domain.model.Company
import com.example.farmerapp.domain.model.Farmer
import com.example.farmerapp.domain.model.Login
import com.example.farmerapp.domain.use_case.IsInternetUseCase
import com.example.farmerapp.domain.use_case.company.InsertCompanyToLocalUseCase
import com.example.farmerapp.domain.use_case.company.SelectCompanyByCompanyIdUseCase
import com.example.farmerapp.domain.use_case.farmer.AddFarmerToApiUseCase
import com.example.farmerapp.domain.use_case.farmer.InsertFarmerToLocalUseCase
import com.example.farmerapp.domain.use_case.login_fragment.AddLoginToLocalUseCase
import com.example.farmerapp.until.FarmerStatus
import com.example.farmerapp.until.Resource
import com.example.farmerapp.until.extetensions.CompanyExtensions.toCompany
import com.example.farmerapp.until.extetensions.FarmerExtensions.toFarmer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class SingInViewModel
@Inject constructor(
    private val addFarmerToApiUseCase: AddFarmerToApiUseCase,
    private val addFarmerToLocalUseCase: InsertFarmerToLocalUseCase,
    private val addLoginToLocalUseCase: AddLoginToLocalUseCase,
    private val isInternetUseCase: IsInternetUseCase,
    private val insertCompanyUseCase: InsertCompanyToLocalUseCase,
    private val checkCompanyByCompanyId: SelectCompanyByCompanyIdUseCase
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

    private suspend fun addDefaultCompanyToLocalDatabase(
        defaultCompany: Company,
        farmer: Farmer,
        login: Login
    ) {
        insertCompanyUseCase.insertCompany(defaultCompany).collect {
            when (it) {
                is Resource.Loading -> {
                    _state.value = SingInFragmentState.Loading
                }

                is Resource.Success -> {
                    farmer.company = defaultCompany
                    addFarmerToLocal(farmer, login)
                }

                is Resource.Error -> {
                    _state.value = SingInFragmentState.Error(it.message!!)
                }
            }
        }
    }

    private suspend fun checkSavedDefaultCompany(
        defaultCompany: Company,
        farmer: Farmer,
        login: Login
    ) {
        val companyId = 1
        farmer.company = defaultCompany
        checkCompanyByCompanyId.selectCompanyByCompanyId(companyId).collect {
            when (it) {
                is Resource.Loading -> {
                    _state.value = SingInFragmentState.Loading
                }

                is Resource.Success -> {
                    if (it.data == null)
                        addDefaultCompanyToLocalDatabase(defaultCompany,farmer,login)
                    else
                        addFarmerToLocal(farmer, login)

                }

                is Resource.Error -> {
                    _state.value = SingInFragmentState.Error(it.message!!)
                }
            }
        }
    }

    private suspend fun addFarmerToApi(farmer: Farmer, login: Login) {

        val companyApiDto = CompanyApiDto(
            "64ebe9d5de261bd507f7a56d",
            "Deneme",
            "Artvin/Savsat/Armutlu mah.",
            "05340000000"
        )
        val farmerApiDto = FarmerApiDto(
            "",
            companyApiDto,
            farmer.name,
            farmer.sourName,
            "",
            LocalDateTime.now().toString(),
            LocalDateTime.now().toString(),
            "",
            FarmerStatus.Farmer.name,
            login.passwordHash
        )

        addFarmerToApiUseCase.addFarmer(farmerApiDto).collect {
            when (it) {
                is Resource.Loading -> {
                    _state.value = SingInFragmentState.Loading
                }

                is Resource.Success -> {
                    val farmer = it.data!!.toFarmer()
                    val company = companyApiDto.toCompany()
                    company.id = 1
                    farmer.company = company
                    checkSavedDefaultCompany(company, farmer, login)
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
                    addFarmerToApi(onEvent.farmer, onEvent.login)
                }
            }
        }
    }

}