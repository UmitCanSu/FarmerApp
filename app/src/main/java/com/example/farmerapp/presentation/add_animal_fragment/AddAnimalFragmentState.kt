package com.example.farmerapp.presentation.add_animal_fragment

sealed class AddAnimalFragmentState {
    object Idle : AddAnimalFragmentState()
    object Loading : AddAnimalFragmentState()
    object Success : AddAnimalFragmentState()
    data class Error(val errorMessage: String) : AddAnimalFragmentState()
}