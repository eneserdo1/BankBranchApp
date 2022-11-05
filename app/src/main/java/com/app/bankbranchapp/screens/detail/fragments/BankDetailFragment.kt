package com.app.bankbranchapp.screens.detail.fragments

import android.os.Bundle
import android.view.View
import com.app.bankbranchapp.common.constants.AnalyticsConstants
import com.app.bankbranchapp.common.utils.viewOnMap
import com.app.bankbranchapp.databinding.FragmentBankDetailBinding
import com.app.bankbranchapp.core.fragment.BaseFragment
import com.app.bankbranchapp.business.models.BankListResponseItem
import com.app.bankbranchapp.screens.list.fragments.BankListFragment.Companion.SELECTED_BANK_BRANCH
import com.google.firebase.analytics.FirebaseAnalytics
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BankDetailFragment : BaseFragment<FragmentBankDetailBinding>(FragmentBankDetailBinding::inflate) {

    private var bankDetail : BankListResponseItem ?=null

    private lateinit var firebaseAnalytics: FirebaseAnalytics

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bankDetail = arguments?.getParcelable<BankListResponseItem>(SELECTED_BANK_BRANCH)
        setData(bankDetail)
        setButtonsListener()
        setFirebaseAnalytics()
    }

    private fun setButtonsListener() {
        binding.navigateButton.setOnClickListener {
            bankDetail?.let {
                val intent = viewOnMap(it.dc_ADRES)
                startActivity(intent)
            }
        }
    }

    private fun setData(bankDetail: BankListResponseItem?) {
        binding.apply {
            bankNameDescription.text = bankDetail?.dc_BANKA_SUBE
            bankTypeDescription.text = bankDetail?.dc_BANKA_TIPI
            bankCityDescription.text = bankDetail?.dc_SEHIR
            bankDistrictDescription.text = bankDetail?.dc_ILCE
            bankAddressDescription.text = bankDetail?.dc_ADRES
        }
    }

    private fun setFirebaseAnalytics() {
        val bundle = Bundle()
        bundle.putParcelable(AnalyticsConstants.BANK_DETAIL_SUCCESS,bankDetail)
        firebaseAnalytics = FirebaseAnalytics.getInstance(requireContext())
        firebaseAnalytics.logEvent(AnalyticsConstants.BANK_DETAIL,bundle)
    }
}


