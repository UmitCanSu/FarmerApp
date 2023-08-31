package com.example.farmerapp.presentation.login_fragment

import com.example.farmerapp.data.Login


sealed class LoginFragmentOnEvent{
    data class OnLogin(val loginFarmer: Login):LoginFragmentOnEvent()
}
