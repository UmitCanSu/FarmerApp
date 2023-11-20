package com.example.farmerapp.presentation.login_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.farmerapp.data.remote.dto.CompanyApiDto
import com.example.farmerapp.data.remote.dto.LoginApiDto
import com.example.farmerapp.domain.model.Company
import com.example.farmerapp.domain.model.Farmer
import com.example.farmerapp.domain.model.Login
import com.example.farmerapp.domain.use_case.IsInternetUseCase
import com.example.farmerapp.domain.use_case.company.InsertCompanyToLocalUseCase
import com.example.farmerapp.domain.use_case.company.SelectCompanyByCompanyApiIdUseCase
import com.example.farmerapp.domain.use_case.farmer.InsertFarmerToLocalUseCase
import com.example.farmerapp.domain.use_case.farmer.LoginToApiFarmerUseCase
import com.example.farmerapp.domain.use_case.login_fragment.GetLocalLoginUseCase
import com.example.farmerapp.domain.use_case.login_fragment.GetLoginToApiAndSaveCompanyAndFarmerAndLoginUseCase
import com.example.farmerapp.until.Resource
import com.example.farmerapp.until.Sesion
import com.example.farmerapp.until.extetensions.CompanyExtensions.toCompany
import com.example.farmerapp.until.extetensions.LoginExtensions.toLogin
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginFragmentViewModel
@Inject constructor(
    private val loginToApiFarmerUseCase: LoginToApiFarmerUseCase,
    private val isInternetUseCase: IsInternetUseCase,
    private val getLocalLoginToLocalUseCase: GetLocalLoginUseCase,
    private val addCompanyToLocalUseCase: InsertCompanyToLocalUseCase,
    private val addFarmerToLocalUseCase: InsertFarmerToLocalUseCase,
    private val checkCompanyToLocalUseCase: SelectCompanyByCompanyApiIdUseCase,
    private val getLoginToApiAndSaveCompanyAndFarmerAndLogin: GetLoginToApiAndSaveCompanyAndFarmerAndLoginUseCase
) : ViewModel() {
    private val _state = MutableStateFlow<LoginFragmentState>(LoginFragmentState.Idle)
    val state: StateFlow<LoginFragmentState> = _state
    private suspend fun loginFarmer(loginApiDto: LoginApiDto) {
        isInternetUseCase.isInternet().collect {
            when (it) {
                is Resource.Loading -> {
                    _state.value = LoginFragmentState.Loading
                }

                is Resource.Success -> {
                    val isInternet = it.data!!
                    Sesion.getInstance().isInternet = isInternet
                    if (isInternet)
                        getLoginToApi(loginApiDto)
                    else
                        loginLocalFarmer(loginApiDto.toLogin())
                }

                is Resource.Error -> {
                    _state.value = LoginFragmentState.Error(it.message!!)
                }
            }
        }
    }


    private suspend fun loginApiFarmer(login: LoginApiDto) {
        loginToApiFarmerUseCase.login(login).collect {
            when (it) {
                is Resource.Loading -> {
                    _state.value = LoginFragmentState.Loading
                }

                is Resource.Success -> {
                    // setCompanyAndFarmerSingleton(it.data!!)
                    //_state.value = LoginFragmentState.Success
                    //timerToState()
                    checkCompanyAndSave(it.data!!.company!!)
                }

                is Resource.Error -> {
                    _state.value = LoginFragmentState.Error(it.message!!)
                }
            }
        }
    }

    private suspend fun getLoginToApi(loginApiDto: LoginApiDto) {
        getLoginToApiAndSaveCompanyAndFarmerAndLogin
            .getLoginToApiAndSaveCompanyAndFarmerAndLogin2(loginApiDto).collect {
                when (it) {
                    is Resource.Loading -> {
                        _state.value = LoginFragmentState.Loading
                    }

                    is Resource.Success -> {
                        val farmer = it.data!!
                        with(Sesion.getInstance()) {
                            this.company = farmer.company
                            this.farmer = farmer
                        }
                        _state.value = LoginFragmentState.Success
                    }

                    is Resource.Error -> {
                        _state.value = LoginFragmentState.Error(it.message!!)
                    }
                }
            }
    }

    private suspend fun loginLocalFarmer(login: Login) {
        getLocalLoginToLocalUseCase.getLogin(login).collect {
            when (it) {
                is Resource.Loading -> {
                    _state.value = LoginFragmentState.Loading
                }

                is Resource.Success -> {
                    val farmer = it.data!!
                    with(Sesion.getInstance()) {
                        this.company = farmer.company
                        this.farmer = farmer
                    }
                    _state.value = LoginFragmentState.Success
                }

                is Resource.Error -> {
                    _state.value = LoginFragmentState.Error(it.message!!)
                }
            }
        }
    }

    private suspend fun checkCompanyAndSave(company: Company) {
        checkCompanyToLocalUseCase.selectCompanyByCompanyApiId(company.apiId).collect {
            when (it) {
                is Resource.Loading -> {
                    _state.value = LoginFragmentState.Loading
                }

                is Resource.Success -> {
                    if (company != null) {

                    } else {
                        saveCompany(company)
                    }
                }

                is Resource.Error -> {
                    _state.value = LoginFragmentState.Error(it.message!!)
                }
            }
        }
    }

    private suspend fun saveCompany(company: Company) {
        addCompanyToLocalUseCase.insertCompany(company).collect {
            when (it) {
                is Resource.Loading -> {
                    _state.value = LoginFragmentState.Loading
                }

                is Resource.Success -> {

                }

                is Resource.Error -> {
                    _state.value = LoginFragmentState.Error(it.message!!)
                }
            }
        }
    }

    private suspend fun addFarmerToLocal(farmer: Farmer) {
        addFarmerToLocalUseCase.insertFarmer(farmer).collect {
            when (it) {
                is Resource.Loading -> {
                    _state.value = LoginFragmentState.Loading
                }

                is Resource.Success -> {

                }

                is Resource.Error -> {
                    _state.value = LoginFragmentState.Error(it.message!!)
                }
            }
        }
    }


    private fun setCompanyAndFarmerSingleton(farmer: Farmer) {
        val companyApiDto = CompanyApiDto(
            "64ebe9d5de261bd507f7a56d",
            "Deneme",
            "Artvin/Savsat/Armutlu mah.",
            "05340000000"
        )
        Sesion.getInstance().apply {
            this.farmer = farmer
            this.company = companyApiDto.toCompany()
        }
    }

    private suspend fun timerToState() {
        delay(1000)
        _state.value = LoginFragmentState.Idle
    }

    fun onEvent(onEvent: LoginFragmentOnEvent) {
        when (onEvent) {
            is LoginFragmentOnEvent.OnLogin -> {
                viewModelScope.launch { loginFarmer(onEvent.loginFarmer) }
            }
        }
    }
}
