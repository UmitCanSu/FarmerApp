package com.example.farmerapp.presentation.save_company_fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.farmerapp.R
import com.example.farmerapp.databinding.FragmentSaveCompanyBinding
import com.example.farmerapp.domain.model.Company
import com.example.farmerapp.presentation.dialog.CustomDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SaveCompanyFragment : Fragment() {
    private val viewModel: SaveCompanyViewModel by viewModels()
    private lateinit var binding: FragmentSaveCompanyBinding

    @Inject
    lateinit var customDialog: CustomDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSaveCompanyBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
                    customDialog.errorDialogShow(
                        getString(R.string.can_not_data),
                        onConfirmClick = {

                        }
                    ) {

                    }
                }

                is SaveCompanyState.SavedCompany -> {
                    binding.informationText.text = "Can save data"
                }
            }
        }
    }

}