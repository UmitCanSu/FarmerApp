package com.example.farmerapp.presentation.productInsertAndUpdate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.farmerapp.R
import com.example.farmerapp.databinding.FragmentProductInsertAndUpdateBinding
import com.example.farmerapp.domain.model.Product
import com.example.farmerapp.presentation.dialog.CustomDialog
import com.example.farmerapp.until.Sesion
import com.example.farmerapp.until.enums.UnitType
import com.example.farmerapp.until.extetensions.Extensions
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ProductInsertAndUpdateFragment : Fragment() {
    private val viewModel: ProductInsertAndUpdateViewModel by viewModels()
    private lateinit var binding: FragmentProductInsertAndUpdateBinding
    private val editTextList = ArrayList<TextInputLayout>()

    @Inject
    lateinit var customDialog: CustomDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductInsertAndUpdateBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch { observableState() }
        fillEditTextLayoutList()
        binding.saveButton.setOnClickListener {
            if (Extensions.checkEditTextNullAndSetErrorStatus(editTextList)) {
                val product = fillProduct()
                viewModel.onEvent(ProductInsertAndUpdateOnEvent.insertProduct(product))
            }
        }
    }

    private fun fillProduct(): Product {
        val productName = binding.nameTextLayout.editText!!.text.toString()
        val productPrice = binding.priceTextLayout.editText!!.text.toString().toFloat()
        val selectedUnitType = selectedUnitType()
        return Product(
            productName,
            selectedUnitType.toString(),
            productPrice,
            Sesion.getInstance().company!!
        )
    }

    private fun selectedUnitType(): UnitType {
        return when (binding.unitType.checkedRadioButtonId) {
            binding.ad.id -> UnitType.AD
            binding.gr.id -> UnitType.GR
            binding.kg.id -> UnitType.KG
            else -> {
                UnitType.AD
            }
        }
    }

    private fun fillEditTextLayoutList() {
        editTextList.add(binding.nameTextLayout)
        editTextList.add(binding.priceTextLayout)
    }

    private suspend fun observableState() {
        viewModel.state.collect {
            when (it) {
                is ProductInsertAndUpdateState.Idle -> {

                }

                is ProductInsertAndUpdateState.Loading -> {

                }

                is ProductInsertAndUpdateState.Error -> {
                    customDialog.errorDialogShow(
                        getString(R.string.can_not_data),
                        onConfirmClick = {

                        }
                    ) {

                    }
                }

                is ProductInsertAndUpdateState.Success -> {
                    /*
                    if (it.isSuccessfully) {
                        customDialog.successDialogShow(
                            getString(
                                R.string.save_data,
                            ),
                            Constant.SUCCESS_TIMER
                        ) {
                            val action =
                                ProductInsertAndUpdateFragmentDirections.actionProductInsertAndUpdateFragmentToProductListFragment()
                            Navigation.findNavController(requireView()).navigate(action)
                        }
                    }
                    */
                }

            }
        }
    }
}