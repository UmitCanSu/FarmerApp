package com.example.farmerapp.presentation.sale_list_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.farmerapp.databinding.FragmentSaleListBinding
import com.example.farmerapp.presentation.sale_list_fragment.adapter.SaleListAdapter
import com.example.farmerapp.until.UserSingleton
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SaleListFragment : Fragment() {
    private lateinit var viewModel: SaleListViewModel
    private lateinit var binding: FragmentSaleListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this)[SaleListViewModel::class.java]
        binding = FragmentSaleListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var companyId = UserSingleton.getInstance().company!!.id
        viewModel.onEvent(SaleListOnEvent.SaleList(companyId))
        binding.isPaidCheckbox.setOnCheckedChangeListener { _, isPaid ->
            viewModel.onEvent(SaleListOnEvent.IsPaid(isPaid))
        }
        lifecycleScope.launch { observableState() }
    }

    private suspend fun observableState() {
        viewModel.state.collect {
            when (it) {
                is SaleListState.Idle -> {

                }

                is SaleListState.Loading -> {

                }

                is SaleListState.Error -> {

                }

                is SaleListState.SaleList -> {
                    val adapter = SaleListAdapter(it.salesProductList)
                    binding.recyclerView.adapter = adapter
                    binding.recyclerView.layoutManager =
                        StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
                }
            }
        }
    }
}