package com.example.farmerapp.presentation.save_company_fragment


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.farmerapp.R
import com.example.farmerapp.databinding.FragmentSaveCompanyBinding
import com.example.farmerapp.domain.model.Company
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.observeOn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SaveCompanyFragment : Fragment() {
    private lateinit var viewModel: SaveCompanyViewModel
    private lateinit var binding: FragmentSaveCompanyBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSaveCompanyBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[SaveCompanyViewModel::class.java]

        val company = Company("Deneme", "Artvin/Savsat/Armutlu mah.", "05340000000")
        viewModel.onEvent(SaveCompanyOnEvent.SaveCompany(company))
        lifecycleScope.launch { observableState() }

    }


    private suspend fun observableState() {
        viewModel.state.collect { state ->
            when (state) {
                is SaveCompanyState.Idle -> {
                    binding.informationText.text = "Idle"
                }

                is SaveCompanyState.Loading -> {
                    binding.informationText.text = "Loading"
                }

                is SaveCompanyState.Error -> {
                    binding.informationText.text = state.errorMessage
                }

                is SaveCompanyState.SavedCompany -> {
                   binding.informationText.text="Can save data"
                    val action = SaveCompanyFragmentDirections.actionSaveCompanyFragmentToSaleFragment()
                    Navigation.findNavController(requireView()).navigate(action)
                }
            }
        }
    }

}