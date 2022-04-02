package com.app.bankbranchapp.screens.list.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.app.bankbranchapp.R
import com.app.bankbranchapp.common.models.Status
import com.app.bankbranchapp.common.utils.hideProgressDialog
import com.app.bankbranchapp.common.utils.showProgressDialog
import com.app.bankbranchapp.databinding.FragmentBankListBinding
import com.app.bankbranchapp.presentation.fragment.BaseFragment
import com.app.bankbranchapp.presentation.models.BankListResponseItem
import com.app.bankbranchapp.screens.list.adapter.BankBranchRecyclerAdapter
import com.app.bankbranchapp.screens.list.interfaces.BranchSelectedListener
import com.app.bankbranchapp.screens.list.viewmodels.BankListViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BankListFragment : BaseFragment<FragmentBankListBinding>(FragmentBankListBinding::inflate) {

    private val viewModel : BankListViewModel by viewModels()

    private lateinit var bankBranchAdapter : BankBranchRecyclerAdapter

    companion object{
        const val SELECTED_BANK_BRANCH = "SELECTED_BANK_BRANCH"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setObservers()
        setAdapters()
    }

    private fun setAdapters() {
        bankBranchAdapter = BankBranchRecyclerAdapter(object : BranchSelectedListener{
            override fun selectedBranch(item: BankListResponseItem) {
                navigateDetail(item)
            }
        })
        binding.bankBranchRecyclerview.adapter = bankBranchAdapter
    }

    private fun navigateDetail(item: BankListResponseItem) {
        val bundle = Bundle()
        bundle.putParcelable(SELECTED_BANK_BRANCH,item)
        findNavController().navigate(R.id.bankDetailFragment,bundle)
    }

    private fun setObservers() {
        viewModel.fetchBankBranchList().observe(viewLifecycleOwner){response->
            when(response.status){
                Status.ERROR->{
                    Snackbar.make(requireView(),"Teknik hata oluştu, tekrar deneyiniz",Snackbar.LENGTH_LONG).show()
                }
                Status.LOADING->{
                    context?.showProgressDialog()
                }
                Status.SUCCESS->{
                    hideProgressDialog()
                    bankBranchAdapter.submitList(response.data)
                }
            }
        }
    }
}