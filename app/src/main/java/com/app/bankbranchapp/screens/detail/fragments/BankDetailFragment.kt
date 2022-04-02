package com.app.bankbranchapp.screens.detail.fragments

import android.os.Bundle
import android.view.View
import com.app.bankbranchapp.databinding.FragmentBankDetailBinding
import com.app.bankbranchapp.presentation.fragment.BaseFragment
import com.app.bankbranchapp.presentation.models.BankListResponseItem
import com.app.bankbranchapp.screens.list.fragments.BankListFragment.Companion.SELECTED_BANK_BRANCH
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BankDetailFragment : BaseFragment<FragmentBankDetailBinding>(FragmentBankDetailBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bankDetail = arguments?.getParcelable<BankListResponseItem>(SELECTED_BANK_BRANCH)
        println("Detail - $bankDetail")
    }
}


