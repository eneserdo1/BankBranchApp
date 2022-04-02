package com.app.bankbranchapp.screens.list.fragments

import android.os.Bundle
import android.view.View
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.app.bankbranchapp.R
import com.app.bankbranchapp.common.constants.AnalyticsConstants.Companion.BANK_LIST
import com.app.bankbranchapp.common.constants.AnalyticsConstants.Companion.BANK_LIST_SUCCESS
import com.app.bankbranchapp.common.models.Status
import com.app.bankbranchapp.common.utils.isNetworkAvailable
import com.app.bankbranchapp.databinding.FragmentBankListBinding
import com.app.bankbranchapp.presentation.fragment.BaseFragment
import com.app.bankbranchapp.presentation.models.BankListResponseItem
import com.app.bankbranchapp.screens.list.adapter.BankBranchRecyclerAdapter
import com.app.bankbranchapp.screens.list.interfaces.BranchSelectedListener
import com.app.bankbranchapp.screens.list.viewmodels.BankListViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.analytics.FirebaseAnalytics
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BankListFragment : BaseFragment<FragmentBankListBinding>(FragmentBankListBinding::inflate) {

    private val viewModel : BankListViewModel by viewModels()

    private lateinit var bankBranchAdapter : BankBranchRecyclerAdapter

    private lateinit var firebaseAnalytics: FirebaseAnalytics

    companion object{
        const val SELECTED_BANK_BRANCH = "SELECTED_BANK_BRANCH"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setObservers()
        setAdapters()
        setFirebaseAnalytics()
        setCheckConnectionControl()

    }

    private fun setCheckConnectionControl() {
        val isAvailable = isNetworkAvailable(requireContext())
        if (!isAvailable){
            Snackbar.make(requireView(),getString(R.string.connection_error_message),Snackbar.LENGTH_LONG).show()
        }
    }

    private fun setFirebaseAnalytics() {
        val bundle = Bundle()
        bundle.putBoolean(BANK_LIST_SUCCESS,true)
        firebaseAnalytics = FirebaseAnalytics.getInstance(requireContext())
        firebaseAnalytics.logEvent(BANK_LIST,bundle)
    }

    private fun setSearchListener() {
        binding.searchEdittext.doOnTextChanged { text, start, before, count ->
            bankBranchAdapter.filter.filter(text)
        }
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
        lifecycleScope.launch {
            viewModel.fetchBankBranchList().observe(viewLifecycleOwner){response->
                when(response.status){
                    Status.ERROR->{
                        Snackbar.make(requireView(),getString(R.string.service_error_message),Snackbar.LENGTH_LONG).show()
                        dialog.dismiss()
                    }
                    Status.LOADING->{
                        dialog.show()
                    }
                    Status.SUCCESS->{
                        dialog.dismiss()
                        response.data?.let {
                            bankBranchAdapter.setData(it)
                            viewModel.setFilterState(true)
                        }
                    }
                }
            }
        }


        viewModel.filterState.observe(viewLifecycleOwner){filterState->
            if (filterState){
                setSearchListener()
            }
        }
    }
}