package com.example.farmerapp.presentation.login_fragment

sealed class LoginFragmentState{
    object Idle:LoginFragmentState()
    object Loading:LoginFragmentState()
    object Success:LoginFragmentState()
    data class Error(val errorMessage:String):LoginFragmentState()
}
