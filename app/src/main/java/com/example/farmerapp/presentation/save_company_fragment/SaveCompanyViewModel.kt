package com.example.farmerapp.presentation.save_company_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.farmerapp.domain.model.Company
import com.example.farmerapp.domain.model.Product
import com.example.farmerapp.domain.repository.CompanyRepository
import com.example.farmerapp.domain.repository.ProductRepository
import com.example.farmerapp.domain.use_case.company.InsertCompanyUseCase
import com.example.farmerapp.domain.use_case.company.SelectCompanyWithCompanyIdUseCase
import com.example.farmerapp.domain.use_case.default_data.DefaultSaveProductUseCase
import com.example.farmerapp.until.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SaveCompanyViewModel
@Inject constructor(
    private val insertCopanyUseCase: InsertCompanyUseCase,
    private val saveDefaultProductUseCase: DefaultSaveProductUseCase,
    private val selectCompanyWithCompanyIdUseCase: SelectCompanyWithCompanyIdUseCase
) : ViewModel() {
    private val _state = MutableStateFlow<SaveCompanyState>(SaveCompanyState.Idle)
    val state: StateFlow<SaveCompanyState> = _state.asStateFlow()

    private  fun insertCompany(company: Company) {
        viewModelScope.launch {
            insertCopanyUseCase.insertCompany(company).collect {
                when (it) {
                    is Resource.Loading -> {
                        _state.value = SaveCompanyState.Loading
                    }

                    is Resource.Success -> {
                        saveDefaultProduct(company)
                    }

                    is Resource.Error -> {
                        _state.value = SaveCompanyState.Error(it.message!!)
                    }
                }
            }
        }
    }

    private suspend fun saveDefaultProduct(company: Company) {
        saveDefaultProductUseCase.saveDefaultProduct(company).collect {
            when (it) {
                is Resource.Loading -> {
                    _state.value = SaveCompanyState.Loading
                }

                is Resource.Success -> {
                    _state.value = SaveCompanyState.SavedCompany
                }

                is Resource.Error -> {
                    _state.value = SaveCompanyState.Error(it.message!!)
                }
            }
        }
    }

    private suspend fun checkSavedCompany(companyId: Int) {
        selectCompanyWithCompanyIdUseCase.selectCompanyWithCompanyId(companyId)
            .collect {
                when (it) {
                    is Resource.Loading -> {
                        _state.value = SaveCompanyState.Loading
                    }

                    is Resource.Success -> {
                        if (it.data != null) {
                            _state.value = SaveCompanyState.SavedCompany
                        } else {
                            val company =
                                Company("Deneme", "Artvin/Savsat/Armutlu mah.", "05340000000")
                            company.id = 1
                            insertCompany(company)
                        }
                    }

                    is Resource.Error -> {
                        _state.value = SaveCompanyState.Error(it.message!!)
                    }
                }
            }
    }

    fun onEvent(onEvent: SaveCompanyOnEvent) {
        when (onEvent) {
            is SaveCompanyOnEvent.SaveCompany -> {
                viewModelScope.launch{
                    checkSavedCompany(1)
                }
            }
        }
    }


}