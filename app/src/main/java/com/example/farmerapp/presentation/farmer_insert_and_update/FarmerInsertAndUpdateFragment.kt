package com.example.farmerapp.presentation.farmer_insert_and_update

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.farmerapp.R
import com.example.farmerapp.databinding.FragmentFarmerInsertAndUpdateBinding
import com.example.farmerapp.domain.model.Farmer
import com.example.farmerapp.presentation.dialog.CustomDialog
import com.example.farmerapp.until.Constant
import com.example.farmerapp.until.FarmerStatus
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class FarmerInsertAndUpdateFragment : Fragment() {

    private lateinit var binding: FragmentFarmerInsertAndUpdateBinding
    private val viewModel: FarmerInsertAndUpdateViewModel by viewModels()
    private val editLayoutList = ArrayList<TextInputLayout>()

    @Inject
    lateinit var customDialog: CustomDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFarmerInsertAndUpdateBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            observableState()
        }
        fillFarmerStatusSpinner()
        fillEditLayoutList()
        binding.saveButton.setOnClickListener {
            if (checkEditTextNullAndSetErrorStatus()) {
                val name = binding.nameText.text.toString()
                val surname = binding.surnameEditText.text.toString()
                val year = binding.yearsEditText.text.toString().toInt()
                val farmerStatusIndex = binding.farmerStatusSpinner.selectedItemPosition
                val farmer = Farmer(name, surname, year, FarmerStatus.values()[farmerStatusIndex])
                viewModel.onEvent(FarmerFragmentOnEvent.insertFarmer(farmer))
            }
        }
    }

    private fun fillEditLayoutList() {
        editLayoutList.add(binding.nameTextLayout)
        editLayoutList.add(binding.surnameTextLayout)
        editLayoutList.add(binding.yearsTextLayout)
    }

    private fun checkEditTextNullAndSetErrorStatus(): Boolean {
        var check = true
        for (editLayout in editLayoutList) {
            if (editLayout.editText!!.text.isNullOrEmpty()) {
                editLayout.error = getString(R.string.required)
                check = false
            } else {
                editLayout.error = null
            }
        }
        return check
    }

    private fun fillFarmerStatusSpinner() {
        val adapter = ArrayAdapter(
            requireContext(),
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            FarmerStatus.values()
        )
        binding.farmerStatusSpinner.adapter = adapter
    }

    private suspend fun observableState() {
        viewModel.state.collect {
            when (it) {
                is FarmerFragmentState.Idle -> {

                }

                is FarmerFragmentState.Loading -> {

                }

                is FarmerFragmentState.Error -> {
                    customDialog.errorDialogShow(
                        getString(R.string.can_not_data),
                        onConfirmClick = {

                        }
                    ) {

                    }
                }

                is FarmerFragmentState.IsInsert -> {
                    customDialog.successDialogShow(
                        getString(R.string.save_data),
                        Constant.SUCCESS_TIMER
                    ) {
                        Navigation.findNavController(requireView()).popBackStack()
                    }
                }

                is FarmerFragmentState.IsUpdate -> {

                }
            }
        }
    }
}