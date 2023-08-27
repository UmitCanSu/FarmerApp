package com.example.farmerapp.presentation.sale_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.farmerapp.R
import com.example.farmerapp.databinding.FragmentSaleBinding
import com.example.farmerapp.presentation.dialog.CustomDialog
import com.example.farmerapp.until.Constant
import com.example.farmerapp.until.extetensions.Extensions
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SaleFragment : Fragment() {
    private lateinit var binding: FragmentSaleBinding
    private val viewModel: SaleViewModel by viewModels()
    private val editTextLayout = ArrayList<TextInputLayout>()

    @Inject
    lateinit var customDialog: CustomDialog
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSaleBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch { observeSaleFragmentState() }
        fillEditTextLayout()
        binding.choseProduct.onItemSelectedListener = listener()

        binding.numberText.addTextChangedListener {
            it?.ifEmpty { 0 }.let { number ->
                val salesProductCount: Int = number.toString().toInt()
                viewModel.onEvent(SaleFragmentOnEvent.CalculatePrice(salesProductCount))
                binding.numberTextLayout.error = null
            }
        }

        binding.saleButton.setOnClickListener {
            if (Extensions.checkEditTextNullAndSetErrorStatus(editTextLayout)) {
                viewModel.onEvent(SaleFragmentOnEvent.SelectCustomer(binding.choseCustomer.selectedItemPosition))
                viewModel.onEvent(SaleFragmentOnEvent.Save)
            }
        }
       customDialog = CustomDialog(requireContext())
    }

    private fun listener(): OnItemSelectedListener {
        return object : OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                viewModel.onEvent(SaleFragmentOnEvent.SelectProduct(p2))
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }
    }

    private fun fillEditTextLayout() {
        editTextLayout.add(binding.numberTextLayout)
    }

    private suspend fun observeSaleFragmentState() {

        viewModel.state.collect { state ->
            when (state) {
                is SaleFragmentState.Idle -> {}
                is SaleFragmentState.Loading -> {}
                is SaleFragmentState.Error -> {}
                is SaleFragmentState.Calculate -> {
                    binding.totalPrice.text = state.amountPrice
                }

                is SaleFragmentState.ProdcutList -> {
                    val adapter = ArrayAdapter(
                        requireContext(),
                        androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                        state.productList.map { it.name })
                    binding.choseProduct.adapter = adapter
                }

                is SaleFragmentState.CustomerList -> {
                    if (state.customerList.isEmpty()) {
                        customDialog.warningDialogShow(getString(R.string.customer_list_null)) {
                            val action =
                                SaleFragmentDirections.actionSaleFragmentToCustomerFragment()
                            Navigation.findNavController(requireView()).navigate(action)

                        }
                    } else {
                        val adapter = ArrayAdapter(
                            requireContext(),
                            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                            state.customerList.map { it.name + " " + it.surName })
                        binding.choseCustomer.adapter = adapter
                    }
                }

                is SaleFragmentState.SelectedProduct -> {
                    val product = state.selectedProduct
                    binding.productName.text = product.name
                    binding.productPrice.text = product.price.toString()
                }

                is SaleFragmentState.IsSavesSalesProduct -> {
                    if (state.salesId > 0) {
                        customDialog.successDialogShow(
                            getString(R.string.save_data),
                            Constant.SUCCESS_TIMER
                        ) {
                            val action =
                                SaleFragmentDirections.actionSaleFragmentToAddAmountPaidFragment(
                                    state.salesId
                                )
                            Navigation.findNavController(requireView()).navigate(action)

                        }
                    } else {
                        customDialog.errorDialogShow(
                            getString(R.string.can_not_data),
                            onConfirmClick = {

                            }
                        ) {

                        }
                    }
                }
            }
        }
    }
}