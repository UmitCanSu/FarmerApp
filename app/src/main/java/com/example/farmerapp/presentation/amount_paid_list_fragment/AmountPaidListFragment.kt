package com.example.farmerapp.presentation.amount_paid_list_fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.farmerapp.databinding.FragmentAmountPaidListBinding
import kotlinx.coroutines.launch

class AmountPaidListFragment : Fragment() {

    private lateinit var viewModel: AmountPaidListViewModel
    private lateinit var binding: FragmentAmountPaidListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this)[AmountPaidListViewModel::class.java]
        binding = FragmentAmountPaidListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch { observableState() }
    }
    private suspend fun observableState(){

    }

}