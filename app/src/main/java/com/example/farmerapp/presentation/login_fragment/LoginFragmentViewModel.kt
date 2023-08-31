package com.example.farmerapp.presentation.login_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.farmerapp.data.Login
import com.example.farmerapp.data.remote.dto.CompanyApiDto
import com.example.farmerapp.data.remote.dto.FarmerApiDto
import com.example.farmerapp.domain.model.Farmer
import com.example.farmerapp.domain.use_case.farmer.AddFarmerToApiUseCase
import com.example.farmerapp.domain.use_case.farmer.LoginToApiFarmerUseCase
import com.example.farmerapp.until.FarmerStatus
import com.example.farmerapp.until.Resource
import com.example.farmerapp.until.UserSingleton
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class LoginFragmentViewModel
@Inject constructor(
    private val addFarmerToApiUseCase: AddFarmerToApiUseCase,
    private val loginToApiFarmerUseCase: LoginToApiFarmerUseCase
) : ViewModel() {
    private val _state = MutableStateFlow<LoginFragmentState>(LoginFragmentState.Idle)
    val state: StateFlow<LoginFragmentState> = _state
    private suspend fun loginFarmer(login: Login) {
        loginToApiFarmerUseCase.login(login).collect {
            when (it) {
                is Resource.Loading -> {
                    _state.value = LoginFragmentState.Loading
                }

                is Resource.Success -> {
                    UserSingleton.getInstance().farmer = it.data
                    UserSingleton.getInstance().company = it.data!!.company
                    _state.value = LoginFragmentState.Success
                    timerToState()
                }

                is Resource.Error -> {
                    _state.value = LoginFragmentState.Error(it.message!!)
                }
            }
        }
    }

    private suspend fun addFarmer(farmer: Farmer?) {
        val companyApiDto = CompanyApiDto(
            "64ebe9d5de261bd507f7a56d",
            "Deneme",
            "Artvin/Savsat/Armutlu mah.",
            "05340000000"
        )
        val farmerApiDto = FarmerApiDto(
            "",
            companyApiDto,
            "Farmer",
            " -1",
            "05222222222",
            LocalDateTime.now().toString(),
            LocalDateTime.now().toString(),
            "",
            FarmerStatus.Farmer.name,
            "1"
        )
        addFarmerToApiUseCase.addFarmer(farmerApiDto).collect {
            when (it) {
                is Resource.Loading -> {

                }

                is Resource.Success -> {
                    it.data
                }

                is Resource.Error -> {
                    it.message!!
                }
            }
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
