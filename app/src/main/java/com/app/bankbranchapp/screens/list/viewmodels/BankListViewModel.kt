package com.app.bankbranchapp.screens.list.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.bankbranchapp.business.repositories.Repository
import com.app.bankbranchapp.common.models.Resource
import com.app.bankbranchapp.presentation.models.BankListResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BankListViewModel @Inject constructor(private val repository: Repository) : ViewModel(){

    private var _bankBranchList = MutableLiveData<Resource<BankListResponse>>()
    var bankBranchList: MutableLiveData<Resource<BankListResponse>> = _bankBranchList

    fun fetchAllBankBranchList(){
        viewModelScope.launch {
            repository.getAllBankBranchList().onStart {
                bankBranchList.postValue(Resource.loading())
            }.collect{
                bankBranchList.postValue(it)
            }
        }
    }
}