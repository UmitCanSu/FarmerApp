package com.example.farmerapp.presentation.sing_in_fragment

sealed class SingInFragmentState {
    object Idle : SingInFragmentState()
    object Loading : SingInFragmentState()
    object Success : SingInFragmentState()
    data class IsInternet(val isInternet: Boolean) : SingInFragmentState()
    data class Error(val errorMessage: String) : SingInFragmentState()
}