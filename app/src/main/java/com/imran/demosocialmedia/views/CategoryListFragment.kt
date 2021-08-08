package com.imran.demosocialmedia.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.imran.demosocialmedia.R
import com.imran.demosocialmedia.data.network.LiveDataResource
import com.imran.demosocialmedia.databinding.FragmentCategoryListBinding
import com.imran.demosocialmedia.viewmodels.CategoryViewModel
import com.imran.demosocialmedia.views.adapters.CategoryListAdapter

class CategoryListFragment : Fragment() {

    private var _binding: FragmentCategoryListBinding? = null

    private var mAdapter: CategoryListAdapter = CategoryListAdapter()

    private val categoryViewModel by viewModels<CategoryViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCategoryListBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()

        categoryViewModel.getCategoryList().observe(viewLifecycleOwner, { data->
            data?.let {resource ->

                when(resource.status) {
                    LiveDataResource.Status.SUCCESS -> {
                        _binding?.srlCategory?.isRefreshing = false
                        mAdapter.setData(resource.data?.data?.categories)
                    }
                    LiveDataResource.Status.ERROR -> {
                        _binding?.srlCategory?.isRefreshing = false
                        Toast.makeText(requireContext(), resource.data?.message, Toast.LENGTH_SHORT).show()
                    }
                    LiveDataResource.Status.LOADING -> {
                        _binding?.srlCategory?.isRefreshing = true
                    }
                }

            }
        })
    }

    private fun initViews() {
        _binding?.apply {
            layoutCustomToolbar.tvToolbarTitle.text = getString(R.string.search_category)
            layoutCustomToolbar.btnBack.setOnClickListener {
                activity?.onBackPressed()
            }
            layoutCustomToolbar.tvSave.isVisible = true
            layoutCustomToolbar.tvSave.setOnClickListener {
                saveList()
                activity?.onBackPressed()
            }
            rvCategoryList.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            rvCategoryList.adapter = mAdapter
        }
    }

    private fun saveList() {

    }
}