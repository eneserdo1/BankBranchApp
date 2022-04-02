package com.app.bankbranchapp.screens.detail.viewmodels

import androidx.lifecycle.ViewModel
import com.app.bankbranchapp.business.repositories.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BankDetailViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

}