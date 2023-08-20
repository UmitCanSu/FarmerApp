package com.example.farmerapp.presentation.customer_fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.farmerapp.R
import com.example.farmerapp.databinding.FragmentCustomerBinding
import com.example.farmerapp.domain.model.Customer
import com.example.farmerapp.presentation.dialog.CustomDialog
import com.example.farmerapp.until.extetensions.Extensions
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.lang.Exception

@AndroidEntryPoint
class CustomerFragment : Fragment() {

    private lateinit var binding: FragmentCustomerBinding
    private lateinit var viewModel: CustomerViewModel
    private val editTextList = ArrayList<com.google.android.material.textfield.TextInputLayout>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(requireActivity())[CustomerViewModel::class.java]
        binding = FragmentCustomerBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch { observableState() }
        binding.findCustomer.setOnClickListener {
            val phoneNumber = binding.phoneText.text.toString()
            viewModel.onEvent(CustomerFragmentOnEvent.FindCustomerWithPhoneNumber(phoneNumber = phoneNumber))
        }
        fillEditTextList()
        binding.saveCustomer.setOnClickListener {
            if(Extensions.checkEditTextNullAndSetErrorStatus(editTextList)){
                val customer = Customer(
                    binding.nameText.text.toString(),
                    binding.surnameText.text.toString(),
                    binding.phoneText.text.toString(),
                    binding.addressText.text.toString(),
                )
                viewModel.onEvent(CustomerFragmentOnEvent.SavedCustomer(customer))
            }
        }

    }
    private fun fillEditTextList(){
        editTextList.add(binding.nameTextLayout)
        editTextList.add(binding.surnameTextLayout)
        editTextList.add(binding.addressTextLayout)
        editTextList.add(binding.phoneTextLayout)
    }

    private fun setCustomerVoView(customer: Customer) {
        binding.nameText.setText(customer.name)
        binding.surnameText.setText(customer.surName)
        binding.addressText.setText(customer.address)
        binding.phoneText.setText(customer.phone)
    }

    private suspend fun observableState() {
        viewModel.state.collect {
            when (it) {
                is CustomerFragmentState.Idle -> {

                }

                is CustomerFragmentState.Loading -> {

                }

                is CustomerFragmentState.Error -> {
                    CustomDialog(requireActivity()).errorDialogShow(
                        getString(R.string.can_not_data),
                        onConfirmClick = {

                        }, onCancelClick = {

                        }
                    )
                }

                is CustomerFragmentState.FindCustomer -> {
                    setCustomerVoView(it.customer)

                }

                is CustomerFragmentState.SaveCustomer -> {
                    Log.e("S->", "Saved Customer: " + it.isSaved)
                }
            }
        }
    }


}