package com.example.farmerapp.presentation.main_fragment

sealed class MainFragmentState {
    object Idle : MainFragmentState()
    object Loading : MainFragmentState()
    data class Success(val a :String) : MainFragmentState()
    data class Error(val errorMessage :String) : MainFragmentState()
}