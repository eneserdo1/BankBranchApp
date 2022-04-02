package com.app.bankbranchapp.screens.list.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.app.bankbranchapp.databinding.FragmentBankListBinding
import com.app.bankbranchapp.presentation.fragment.BaseFragment
import com.app.bankbranchapp.screens.list.adapter.BankBranchRecyclerAdapter
import com.app.bankbranchapp.screens.list.viewmodels.BankListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BankListFragment : BaseFragment<FragmentBankListBinding>(FragmentBankListBinding::inflate) {

    private val viewModel : BankListViewModel by viewModels()

    private val bankBranchAdapter : BankBranchRecyclerAdapter by lazy {
        BankBranchRecyclerAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setObservers()
    }

    private fun setObservers() {
        viewModel.bankBranchList.observe(viewLifecycleOwner){response->
            response?.let {
                bankBranchAdapter.submitList(it.data)
            }
        }
    }

}