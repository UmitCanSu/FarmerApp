package com.example.farmerapp.presentation.save_company_fragment

import com.example.farmerapp.domain.model.Company

sealed class SaveCompanyOnEvent {
    data class SaveCompany(val company: Company):SaveCompanyOnEvent()
}