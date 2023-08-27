package com.example.farmerapp.presentation.sing_in_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.farmerapp.databinding.FragmentSingInBinding
import com.example.farmerapp.presentation.dialog.CustomDialog
import com.example.farmerapp.until.extetensions.Extensions
import com.google.android.material.textfield.TextInputLayout
import javax.inject.Inject

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
        binding = FragmentSingInBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fillEditTextLayout()
        binding.singInButton.setOnClickListener {
            if (Extensions.checkEditTextNullAndSetErrorStatus(editTextLayout)) {

            }
        }
        binding.loginButton.setOnClickListener {
            Navigation.findNavController(requireView()).popBackStack()
        }
    }

    private fun fillEditTextLayout() {
        editTextLayout.add(binding.nameTextLayout)
        editTextLayout.add(binding.surnameTextLayout)
        editTextLayout.add(binding.mailTextLayout)
        editTextLayout.add(binding.phoneTextLayout)
    }
}