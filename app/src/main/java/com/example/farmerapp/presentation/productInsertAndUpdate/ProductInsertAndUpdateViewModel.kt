package com.example.farmerapp.presentation.productInsertAndUpdate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.farmerapp.domain.model.Product
import com.example.farmerapp.domain.use_case.IsInternetUseCase
import com.example.farmerapp.domain.use_case.product.AddProductToApiUseCase
import com.example.farmerapp.domain.use_case.product.InsertProductUseCase
import com.example.farmerapp.domain.use_case.product.UpdateProductUseCase
import com.example.farmerapp.until.Resource
import com.example.farmerapp.until.Sesion
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductInsertAndUpdateViewModel
@Inject constructor(
    private val getInternetUseCase: IsInternetUseCase,
    private val insertProductUseCase: InsertProductUseCase,
    private val updateProductUseCase: UpdateProductUseCase,
    private val addProductToApiUseCase: AddProductToApiUseCase
) : ViewModel() {
    private val _state =
        MutableStateFlow<ProductInsertAndUpdateState>(ProductInsertAndUpdateState.Idle)
    val state: StateFlow<ProductInsertAndUpdateState> = _state

    private suspend fun checkInternet(product: Product) {
        if (Sesion.getInstance().isInternet) {
            addProductToApi(product)
        } else {
            insertProductToLocal(product)
        }
    }

    private suspend fun insertProductToLocal(product: Product) {
        insertProductUseCase.insertProdcut(product).collect {
            when (it) {
                is Resource.Loading -> {
                    _state.value = ProductInsertAndUpdateState.Loading
                }

                is Resource.Error -> {
                    _state.value = ProductInsertAndUpdateState.Error(it.message!!)

                }

                is Resource.Success -> {
                     _state.value = ProductInsertAndUpdateState.Success(it.data!!)
                }
            }
        }
    }

    private suspend fun updateProduct(product: Product) {
        updateProductUseCase.updateRroduct(product).collect {
            when (it) {
                is Resource.Loading -> {
                    _state.value = ProductInsertAndUpdateState.Loading
                }

                is Resource.Error -> {
                    _state.value = ProductInsertAndUpdateState.Error(it.message!!)
                }

                is Resource.Success -> {
                    _state.value = ProductInsertAndUpdateState.Success(it.data!!)
                }
            }
        }
    }

    private suspend fun addProductToApi(product: Product) {
        addProductToApiUseCase.addProduct(product).collect {
            when (it) {
                is Resource.Loading -> {
                    _state.value = ProductInsertAndUpdateState.Loading
                }

                is Resource.Error -> {
                    _state.value = ProductInsertAndUpdateState.Error(it.message!!)
                }

                is Resource.Success -> {
                    // _state.value = ProductInsertAndUpdateState.Success(it.data!!)
                    insertProductToLocal(it.data!!)
                }
            }
        }
    }


    fun onEvent(onEvent: ProductInsertAndUpdateOnEvent) {
        when (onEvent) {
            is ProductInsertAndUpdateOnEvent.insertProduct -> {
                viewModelScope.launch {
                    checkInternet(onEvent.product)
                }
            }

            is ProductInsertAndUpdateOnEvent.updateProduct -> {
                viewModelScope.launch { updateProduct(onEvent.product) }
            }
        }
    }
}