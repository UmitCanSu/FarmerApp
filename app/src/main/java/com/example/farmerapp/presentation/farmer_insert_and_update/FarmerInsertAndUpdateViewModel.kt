package com.example.farmerapp.presentation.farmer_insert_and_update

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.farmerapp.domain.model.Farmer
import com.example.farmerapp.domain.use_case.farmer.InsertFarmerToLocalUseCase
import com.example.farmerapp.domain.use_case.farmer.UpdateFarmerUseCase
import com.example.farmerapp.until.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FarmerInsertAndUpdateViewModel
@Inject constructor(
    private val insertFarmerUseCase: InsertFarmerToLocalUseCase,
    private val updadeUseCase: UpdateFarmerUseCase
) : ViewModel() {
    private val _state = MutableStateFlow<FarmerFragmentState>(FarmerFragmentState.Idle)
    val state: StateFlow<FarmerFragmentState> = _state

    private suspend fun insertFarmer(farmer: Farmer) {
        insertFarmerUseCase.insertFarmer(farmer).collect {
            when (it) {
                is Resource.Loading -> {
                    _state.value = FarmerFragmentState.Loading
                }

                is Resource.Success -> {
                    _state.value = FarmerFragmentState.IsInsert(it.data!!>0)
                }

                is Resource.Error -> {
                    _state.value = FarmerFragmentState.Error(it.message!!)
                }
            }
        }
    }

    private suspend fun updateFarmer(farmer: Farmer) {
        updadeUseCase.updateFarmer(farmer).collect {
            when (it) {
                is Resource.Loading -> {
                    _state.value = FarmerFragmentState.Loading
                }

                is Resource.Success -> {
                    _state.value = FarmerFragmentState.IsUpdate(it.data!!)
                }

                is Resource.Error -> {
                    _state.value = FarmerFragmentState.Error(it.message!!)
                }
            }
        }
    }

    fun onEvent(onEvent: FarmerFragmentOnEvent) {
        when (onEvent) {
            is FarmerFragmentOnEvent.insertFarmer -> {
                viewModelScope.launch { insertFarmer(onEvent.farmer) }
            }

            is FarmerFragmentOnEvent.updateFarmer -> {
                viewModelScope.launch { updateFarmer(onEvent.farmer) }
            }
        }
    }

}