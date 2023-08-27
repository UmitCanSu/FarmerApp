package com.example.farmerapp.presentation.animal_list_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.farmerapp.domain.use_case.animal.SelectedAnimalWithCompanyIdUseCase
import com.example.farmerapp.until.Resource
import com.example.farmerapp.until.UserSingleton
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimalListViewModel
@Inject constructor(
    private val getAnimalWithCompanyIdUseCase: SelectedAnimalWithCompanyIdUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<AnimalListState>(AnimalListState.Idle)
    val state: StateFlow<AnimalListState> = _state

    private suspend fun getAnimalListWithCompanyId() {
        val companyId = UserSingleton.getInstance().company!!.id
        getAnimalWithCompanyIdUseCase.selectedAnimalWithCompanyIdUseCase(companyId).collect {
            when (it) {
                is Resource.Loading -> {
                    _state.value = AnimalListState.Loading
                }

                is Resource.Success -> {
                    _state.value = AnimalListState.Success(it.data!!)
                }

                is Resource.Error -> {
                    _state.value = AnimalListState.Error(it.message!!)
                }
            }
        }
    }

    fun onEvent(onEvent: AnimalListFragmentEvent) {
        when (onEvent) {
            is AnimalListFragmentEvent.GetAnimalList -> {
                viewModelScope.launch { getAnimalListWithCompanyId() }
            }
        }
    }
}