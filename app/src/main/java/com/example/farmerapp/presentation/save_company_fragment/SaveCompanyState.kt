package com.example.farmerapp.presentation.save_company_fragment

sealed class SaveCompanyState {
    object Idle : SaveCompanyState()
    object Loading : SaveCompanyState()
    data class Error(val errorMessage: String) : SaveCompanyState()
    object SavedCompany : SaveCompanyState()
}