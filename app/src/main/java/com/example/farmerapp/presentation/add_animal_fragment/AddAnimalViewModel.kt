package com.example.farmerapp.presentation.add_animal_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.farmerapp.domain.model.Animal
import com.example.farmerapp.domain.use_case.animal.AddAnimalToApiUseCase
import com.example.farmerapp.domain.use_case.animal.InsertAnimalUseCase
import com.example.farmerapp.until.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddAnimalViewModel
@Inject constructor(
    private val insertAnimalUseCase: InsertAnimalUseCase,
    private val addAnimalToApiUseCase: AddAnimalToApiUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow<AddAnimalFragmentState>(AddAnimalFragmentState.Idle)
    val state: StateFlow<AddAnimalFragmentState> = _state
    private suspend fun insertAnimal(animal: Animal) {
        insertAnimalUseCase.insertAnimalUseCase(animal).collect {
            when (it) {
                is Resource.Loading -> {
                    _state.value = AddAnimalFragmentState.Loading
                }

                is Resource.Success -> {
                   // _state.value = AddAnimalFragmentState.Success
                    addAnimalToApi(animal)
                }

                is Resource.Error -> {
                    _state.value = AddAnimalFragmentState.Error(it.message!!)
                }
            }
        }
    }

    private suspend fun addAnimalToApi(animal: Animal) {
        addAnimalToApiUseCase.addAnimal(animal).collect {
            when (it) {
                is Resource.Loading -> {
                    _state.value = AddAnimalFragmentState.Loading
                }

                is Resource.Success -> {
                    _state.value = AddAnimalFragmentState.Success
                }

                is Resource.Error -> {
                    _state.value = AddAnimalFragmentState.Error(it.message!!)
                }
            }
        }
    }

    fun onEvent(onEvent: AddAnimalFragmentOnEvent) {
        when (onEvent) {
            is AddAnimalFragmentOnEvent.AddAnimal -> {
                viewModelScope.launch { insertAnimal(onEvent.animal) }
            }
        }
    }

}