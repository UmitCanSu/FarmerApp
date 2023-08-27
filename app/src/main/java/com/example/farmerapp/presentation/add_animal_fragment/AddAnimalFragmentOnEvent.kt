package com.example.farmerapp.presentation.add_animal_fragment

import com.example.farmerapp.domain.model.Animal

sealed class AddAnimalFragmentOnEvent {
    data class AddAnimal(val animal: Animal):AddAnimalFragmentOnEvent()
}