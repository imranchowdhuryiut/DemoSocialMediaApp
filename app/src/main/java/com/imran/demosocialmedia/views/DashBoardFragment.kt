package com.imran.demosocialmedia.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.imran.demosocialmedia.App
import com.imran.demosocialmedia.R
import com.imran.demosocialmedia.databinding.FragmentDashBoardBinding
import com.imran.demosocialmedia.views.adapters.SelectedListAdapter

class DashBoardFragment : Fragment() {

    private var _binding: FragmentDashBoardBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDashBoardBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    private val mAdpter = SelectedListAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        mAdpter.setData(App.appPref.getSavedCategories()?.toMutableList())
    }

    private fun initViews() {
        _binding?.apply {
            layoutCustomToolbar.tvToolbarTitle.text = getString(R.string.dash_board)
            layoutCustomToolbar.btnBack.setOnClickListener {
                activity?.onBackPressed()
            }
            layoutCustomToolbar.tvSave.isVisible = false
            rvCategoryList.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            rvCategoryList.adapter = mAdpter
            btnCategory.setOnClickListener {
                findNavController().navigate(DashBoardFragmentDirections.actionDashBoardFragmentToCategoryListFragment())
            }
        }
    }

}