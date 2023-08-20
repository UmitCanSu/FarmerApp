package com.example.farmerapp.until

import com.example.farmerapp.domain.model.Company
import com.example.farmerapp.domain.model.Farmer

class UserSingleton {
     var company: Company? = null
     var farmer: Farmer? = null

    companion object {

        @Volatile
        private var instance: UserSingleton? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: UserSingleton().also { instance = it }
            }
    }
}