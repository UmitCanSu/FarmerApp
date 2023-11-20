package com.example.farmerapp.presentation.sing_in_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.farmerapp.databinding.FragmentSingInBinding
import com.example.farmerapp.domain.model.Company
import com.example.farmerapp.domain.model.Farmer
import com.example.farmerapp.domain.model.Login
import com.example.farmerapp.presentation.dialog.CustomDialog
import com.example.farmerapp.until.FarmerStatus
import com.example.farmerapp.until.extetensions.Extensions
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SingInFragment : Fragment() {
    private val viewModel: SingInViewModel by viewModels()
    private lateinit var binding: FragmentSingInBinding
    private val editTextLayout = ArrayList<TextInputLayout>()

    @Inject
    lateinit var customDialog: CustomDialog
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel
        binding = FragmentSingInBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fillEditTextLayout()
        binding.singInButton.setOnClickListener {
            if (Extensions.checkEditTextNullAndSetErrorStatus(editTextLayout)) {
                val login = fillLogin()
                val farmer = fillFarmer()
                val company = fillCompany()
                viewModel.onEvent(SingInFragmentOnEvent.Save(farmer, login, company))

            }
        }
        binding.loginButton.setOnClickListener {
            Navigation.findNavController(requireView()).popBackStack()
        }
        lifecycleScope.launch { onservableState() }
    }

    private fun fillCompany(): Company {
        return with(binding) {
            Company(
                nickNameLayout.editText!!.text.toString(),
                "",
                phoneTextLayout.editText!!.text.toString(),
            )
        }
    }


    private fun fillLogin(): Login {
        return with(binding) {
            Login(
                nickNameLayout.editText!!.text.toString(),
                passwordLayout.editText!!.text.toString()
            )
        }
    }

    private fun fillFarmer(): Farmer {
        return with(binding) {
            Farmer(
                company = null,
                nameTextLayout.editText!!.text.toString(),
                surnameTextLayout.editText!!.text.toString(),
                18,
                FarmerStatus.Farmer,
            )
        }
    }

    private fun fillEditTextLayout() {
        editTextLayout.add(binding.nameTextLayout)
        editTextLayout.add(binding.surnameTextLayout)
        editTextLayout.add(binding.mailTextLayout)
        editTextLayout.add(binding.phoneTextLayout)
        editTextLayout.add(binding.passwordLayout)
    }

    private suspend fun onservableState() {
        viewModel.state.collect { state ->
            when (state) {
                is SingInFragmentState.Idle -> {

                }

                is SingInFragmentState.Loading -> {

                }

                is SingInFragmentState.IsInternet -> {
                    if (state.isInternet)
                        binding.singInButton.visibility = View.VISIBLE
                    else
                        binding.singInButton.visibility = View.GONE
                }

                is SingInFragmentState.Success -> {
                    val action = SingInFragmentDirections.actionSingInFragmentToMainFragment()
                    Navigation.findNavController(requireView()).navigate(action)
                }

                is SingInFragmentState.Error -> {

                }
            }
        }
    }
}