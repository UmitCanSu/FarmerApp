package com.example.farmerapp.presentation.animal_list_fragment

import com.example.farmerapp.domain.model.Animal
import com.example.farmerapp.presentation.add_animal_fragment.AddAnimalFragmentState

sealed class AnimalListState{
    object Idle : AnimalListState()
    object Loading : AnimalListState()
    data class Success(val animals:List<Animal>) : AnimalListState()
    data class Error(val errorMessage: String) : AnimalListState()
}
