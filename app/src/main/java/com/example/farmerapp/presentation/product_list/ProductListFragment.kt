package com.example.farmerapp.presentation.product_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.farmerapp.R
import com.example.farmerapp.databinding.FragmentProductListBinding
import com.example.farmerapp.presentation.dialog.CustomDialog
import com.example.farmerapp.presentation.product_list.adapter.ProductListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ProductListFragment : Fragment() {
    private val viewModel: ProductListViewModel by viewModels()
    private lateinit var binding: FragmentProductListBinding
    @Inject
    lateinit var customDialog: CustomDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
                    customDialog.errorDialogShow(getString(R.string.error), {}) {}
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