package com.example.farmerapp.presentation.amount_paid_list_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.farmerapp.databinding.FragmentAmountPaidListBinding
import com.example.farmerapp.presentation.dialog.CustomDialog
import kotlinx.coroutines.launch
import javax.inject.Inject

class AmountPaidListFragment : Fragment() {

    private val viewModel: AmountPaidListViewModel by viewModels()
    private lateinit var binding: FragmentAmountPaidListBinding
    @Inject
    lateinit var customDialog: CustomDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAmountPaidListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch { observableState() }
    }

    private suspend fun observableState() {

    }

}