package com.example.farmerapp.presentation.login_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.farmerapp.data.Login
import com.example.farmerapp.databinding.FragmentLoginBinding
import com.example.farmerapp.presentation.dialog.CustomDialog
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginFragmentViewModel by viewModels()
    private val editTextLayout = ArrayList<TextInputLayout>()

    @Inject
    lateinit var customDialog: CustomDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fillEditText()
        binding.singInButton.setOnClickListener {
            /*
               val action = LoginFragmentDirections.()
                 Navigation.findNavController(requireView()).navigate(action)
            */
        }
        val loginFarmer = Login("Farmer", "1")
        viewModel.onEvent(LoginFragmentOnEvent.OnLogin(loginFarmer))
        lifecycleScope.launch { observableState() }
    }

    private fun fillEditText() {
        editTextLayout.add(binding.nickNameLayout)
        editTextLayout.add(binding.passwordTextLayout)
    }

    private suspend fun observableState() {
        viewModel.state.collect {
            when (it) {
                is LoginFragmentState.Idle -> {

                }

                is LoginFragmentState.Loading -> {

                }

                is LoginFragmentState.Success -> {
                    val action = LoginFragmentDirections.actionLoginFragmentToMainFragment()
                    Navigation.findNavController(requireView()).navigate(action)
                 //   requireActivity().supportFragmentManager.popBackStack()
                }

                is LoginFragmentState.Error -> {

                }
            }
        }
    }


}