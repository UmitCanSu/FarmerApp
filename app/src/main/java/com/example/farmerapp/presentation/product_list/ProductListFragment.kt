package com.example.farmerapp.presentation.product_list

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.farmerapp.R
import com.example.farmerapp.databinding.AdapterSalesProductListBinding
import com.example.farmerapp.databinding.FragmentProductListBinding
import com.example.farmerapp.presentation.product_list.adapter.ProductListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductListFragment : Fragment() {
    private lateinit var viewModel: ProductListViewModel
    private lateinit var binding: FragmentProductListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this)[ProductListViewModel::class.java]
        binding = FragmentProductListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch { observableState() }
    }

    private suspend fun observableState() {
        viewModel.state.collect {
            when (it) {
                is ProductListState.Idle -> {

                }

                is ProductListState.Loading -> {

                }

                is ProductListState.Error -> {

                }

                is ProductListState.ProductList -> {
                    val adapter = ProductListAdapter(it.productList)
                    binding.recyclerView.adapter = adapter
                    val staggeredGridLayoutManager =
                        StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
                    binding.recyclerView.adapter = adapter
                    binding.recyclerView.layoutManager = staggeredGridLayoutManager

                }
            }
        }
    }

}