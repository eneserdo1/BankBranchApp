package com.app.bankbranchapp.screens.detail.fragments

import android.os.Bundle
import android.view.View
import com.app.bankbranchapp.databinding.FragmentBankDetailBinding
import com.app.bankbranchapp.presentation.fragment.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BankDetailFragment : BaseFragment<FragmentBankDetailBinding>(FragmentBankDetailBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}


