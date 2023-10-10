package com.example.farmerapp.until

import com.example.farmerapp.domain.model.Company
import com.example.farmerapp.domain.model.Farmer

class Sesion {
    var company: Company? = null
    var farmer: Farmer? = null
    var isInternet = false

    companion object {

        @Volatile
        private var instance: Sesion? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: Sesion().also { instance = it }
            }
    }
}