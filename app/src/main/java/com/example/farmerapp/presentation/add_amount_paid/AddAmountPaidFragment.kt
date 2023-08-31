package com.example.farmerapp.presentation.add_amount_paid


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.R.layout.support_simple_spinner_dropdown_item
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.farmerapp.R
import com.example.farmerapp.databinding.FragmentAddAmountPaidBinding
import com.example.farmerapp.presentation.dialog.CustomDialog
import com.example.farmerapp.until.extetensions.Extensions
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class AddAmountPaidFragment : Fragment() {
    private val viewModel: AddAmountPaidViewModel by viewModels()
    private lateinit var binding: FragmentAddAmountPaidBinding
    private val editTextLayout = ArrayList<TextInputLayout>()
    private val args: AddAmountPaidFragmentArgs by navArgs()

    @Inject
    lateinit var customDialog: CustomDialog
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddAmountPaidBinding.inflate(layoutInflater)
        viewModel.onEvent(AddAmountPaidFragmentOnEvent.GetSalesProduct(args.salesProductID))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch { observableState() }
        fillEditTextLayout()
        binding.pay.setOnClickListener {
            if (Extensions.checkEditTextNullAndSetErrorStatus(editTextLayout)) {
                val selectedCustomerIndex = binding.customerSpinner.selectedItemPosition
                val price = binding.priceInputText.text.toString().toFloat()
                viewModel.onEvent(
                    AddAmountPaidFragmentOnEvent.SaveAmountPaid(
                        selectedCustomerIndex,
                        price
                    )
                )
            }
        }
    }

    private fun fillEditTextLayout() {
        editTextLayout.add(binding.priceInputLayout)
    }

    private suspend fun observableState() {
        viewModel.state.collect { state ->
            when (state) {
                is AddAmountPaidFragmentState.Idle -> {

                }

                is AddAmountPaidFragmentState.Loading -> {

                }

                is AddAmountPaidFragmentState.CustomerList -> {
                    val adapter = ArrayAdapter(
                        requireContext(),
                        support_simple_spinner_dropdown_item,
                        state.customerList.map { it.name + " " + it.surName })
                    adapter.setDropDownViewResource(support_simple_spinner_dropdown_item)
                    binding.customerSpinner.adapter = adapter
                }

                is AddAmountPaidFragmentState.IsSavedAmountPaid -> {

                    if (state.isSave) {
                        customDialog.successDialogShow(
                            getString(R.string.save_data),
                            5
                        ) {
                            Navigation.findNavController(requireView()).popBackStack()
                        }
                    } else {
                        customDialog.errorDialogShow(getString(R.string.can_not_data),
                            {}
                        ) {}
                    }
                }

                is AddAmountPaidFragmentState.RemainingDept -> {
                    binding.remainingDeptText.text = state.remainingDept.toString()
                }

                is AddAmountPaidFragmentState.Error -> {
                    customDialog.errorDialogShow(
                        state.errorMessage,
                        onConfirmClick = {

                        }
                    ) {

                    }
                }
            }
        }
    }

}