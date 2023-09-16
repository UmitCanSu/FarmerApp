package com.example.farmerapp.presentation.login_fragment

import com.example.farmerapp.data.remote.dto.LoginApiDto


sealed class LoginFragmentOnEvent{
    data class OnLogin(val loginFarmer: LoginApiDto):LoginFragmentOnEvent()
}
