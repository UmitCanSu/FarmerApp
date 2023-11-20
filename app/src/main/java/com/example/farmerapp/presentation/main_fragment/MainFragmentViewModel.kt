package com.example.farmerapp.presentation.main_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.farmerapp.domain.use_case.IsInternetUseCase
import com.example.farmerapp.domain.use_case.main.EqualizeLocalDbToApiUseCase
import com.example.farmerapp.until.Resource
import com.example.farmerapp.until.Sesion
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainFragmentViewModel
@Inject constructor(
    private val equalizeLocalDbToApiUseCase: EqualizeLocalDbToApiUseCase,
    private val isInternetUseCase: IsInternetUseCase
) : ViewModel() {
    private val _state = MutableStateFlow<MainFragmentState>(MainFragmentState.Idle)
    val state: StateFlow<MainFragmentState> = _state

    init {
        viewModelScope.launch {
            equalizeLocalDbToApi()
        }
    }

    private suspend fun equalizeLocalDbToApi() {
        val company = Sesion.getInstance().company!!
        equalizeLocalDbToApiUseCase.equalizeLocalDbToApi(company.apiId, company.id).collect {
            when (it) {
                is Resource.Loading -> {
                    _state.value = MainFragmentState.Loading
                }

                is Resource.Success -> {
                    _state.value = MainFragmentState.Success("Success")
                }

                is Resource.Error -> {
                    _state.value = MainFragmentState.Error(it.message!!)
                }
            }
        }
    }


    fun onEvent() {

    }
}