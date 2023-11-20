package com.example.farmerapp.presentation.sing_in_fragment

import com.example.farmerapp.domain.model.Company
import com.example.farmerapp.domain.model.Farmer
import com.example.farmerapp.domain.model.Login

sealed class SingInFragmentOnEvent {
    data class Save(val farmer: Farmer, val login: Login, val company: Company) :
        SingInFragmentOnEvent()
}
