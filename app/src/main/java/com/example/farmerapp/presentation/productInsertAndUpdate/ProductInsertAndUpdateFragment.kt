package com.example.farmerapp.presentation.productInsertAndUpdate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.farmerapp.R
import com.example.farmerapp.databinding.FragmentProductInsertAndUpdateBinding
import com.example.farmerapp.domain.model.Company
import com.example.farmerapp.domain.model.Product
import com.example.farmerapp.presentation.dialog.CustomDialog
import com.example.farmerapp.until.UnitType
import com.example.farmerapp.until.UserSingleton
import com.example.farmerapp.until.extetensions.Extensions
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductInsertAndUpdateFragment : Fragment() {
    private lateinit var viewModel: ProductInsertAndUpdateViewModel
    private lateinit var binding: FragmentProductInsertAndUpdateBinding
    private val editTextList = ArrayList<com.google.android.material.textfield.TextInputLayout>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this)[ProductInsertAndUpdateViewModel::class.java]
        binding = FragmentProductInsertAndUpdateBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch { observableState() }
        fillEditTextLayoutList()
        val company = Company("", "", "")
        company.id = 1

        binding.saveButton.setOnClickListener {
            if (Extensions.checkEditTextNullAndSetErrorStatus(editTextList)) {
                val productName = binding.nameTextLayout.editText!!.text.toString()
                val productPrice = binding.priceTextLayout.editText!!.text.toString().toInt()
                val unitType = when (binding.unitType.checkedRadioButtonId) {
                    binding.ad.id -> UnitType.AD
                    binding.gr.id -> UnitType.GR
                    binding.kg.id -> UnitType.KG
                    else -> {}
                }
                val product = Product(
                    productName,
                    unitType.toString(),
                    productPrice,
                    UserSingleton.getInstance().company!!
                )
                viewModel.onEvent(ProductInsertAndUpdateOnEvent.insertProduct(product))
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
                    CustomDialog(requireActivity()).errorDialogShow(
                        getString(R.string.can_not_data),
                        onConfirmClick = {

                        }, onCancelClick = {

                        }
                    )
                }

                is ProductInsertAndUpdateState.Success -> {
                    if (it.isSuccessfully) {
                        CustomDialog(requireContext()).successDialogShow(
                            getString(
                                R.string.save_data,
                            ),
                            5
                        ) {
                            val action =
                                ProductInsertAndUpdateFragmentDirections.actionProductInsertAndUpdateFragmentToProductListFragment()
                            Navigation.findNavController(requireView()).navigate(action)
                        }
                    }
                }

            }
        }
    }
}