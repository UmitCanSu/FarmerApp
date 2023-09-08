package com.example.farmerapp.presentation.save_company_fragment

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.farmerapp.domain.model.Company
import com.example.farmerapp.domain.model.Farmer
import com.example.farmerapp.domain.use_case.company.AddCompanyToApi
import com.example.farmerapp.domain.use_case.company.GetCompanyToApi
import com.example.farmerapp.domain.use_case.company.InsertCompanyUseCase
import com.example.farmerapp.domain.use_case.company.SelectCompanyWithCompanyIdUseCase
import com.example.farmerapp.domain.use_case.default_data.DefaultSaveProductUseCase
import com.example.farmerapp.until.FarmerStatus
import com.example.farmerapp.until.Resource
import com.example.farmerapp.until.UserSingleton
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SaveCompanyViewModel
@Inject constructor(
    private val insertCopanyUseCase: InsertCompanyUseCase,
    private val saveDefaultProductUseCase: DefaultSaveProductUseCase,
    private val selectCompanyWithCompanyIdUseCase: SelectCompanyWithCompanyIdUseCase,
    private val addCompanyToApi: AddCompanyToApi,
    private val getCompanyToApi: GetCompanyToApi,
) : ViewModel() {
    private val _state = MutableStateFlow<SaveCompanyState>(SaveCompanyState.Idle)
    val state: StateFlow<SaveCompanyState> = _state.asStateFlow()

    private fun insertCompany(company: Company) {
        viewModelScope.launch {
            insertCopanyUseCase.insertCompany(company).collect {
                when (it) {
                    is Resource.Loading -> {
                        _state.value = SaveCompanyState.Loading
                    }

                    is Resource.Success -> {
                        company.id = 1
                        UserSingleton.getInstance().company = company
                        saveDefaultProduct(company)
                        addCompanyApi(company)

                    }

                    is Resource.Error -> {
                        _state.value = SaveCompanyState.Error(it.message!!)
                    }
                }
            }
        }
    }

    private suspend fun addCompanyApi(company: Company) {
        addCompanyToApi.addCompanyToApi(company).collect {
            when (it) {
                is Resource.Loading -> {
                    _state.value = SaveCompanyState.Loading
                }

                is Resource.Success -> {
                    it.data
                }

                is Resource.Error -> {
                    Log.e("S->", it.message!!)
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
    private suspend fun getCompanyToApi(companyId:String){
        getCompanyToApi.getCompanyToApi(companyId).collect{
            when (it) {
                is Resource.Loading -> {
                    _state.value = SaveCompanyState.Loading
                }

                is Resource.Success -> {
                  val company = it.data!!
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
                            UserSingleton.getInstance().company = it.data
                            val farmer = Farmer(it.data, "Farmer ", "- 1", 18, FarmerStatus.Farmer)
                            farmer.id = 1
                            UserSingleton.getInstance().farmer = farmer
                            _state.value = SaveCompanyState.SavedCompany
                        } else {
                            val company =
                                Company("Deneme", "Artvin/Savsat/Armutlu mah.", "05340000000")
                            company.id = 1
                            insertCompany(company)
                            getCompanyToApi("64ebe9d5de261bd507f7a56d")
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
                viewModelScope.launch {
                    checkSavedCompany(1)
                }
            }
        }
    }


}