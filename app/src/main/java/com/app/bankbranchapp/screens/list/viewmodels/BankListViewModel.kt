package com.app.bankbranchapp.screens.list.viewmodels

import androidx.lifecycle.LiveData
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

    private var _filterState = MutableLiveData<Boolean>()
    var filterState = _filterState

    init {
        fetchAllBankBranchList()
    }

    private fun fetchAllBankBranchList(){
        viewModelScope.launch {
            repository.getAllBankBranchList().onStart {
                _bankBranchList.postValue(Resource.loading())
            }.collect{
                _bankBranchList.postValue(it)
            }
        }
    }

    fun fetchBankBranchList() : LiveData<Resource<BankListResponse>>{
        return _bankBranchList
    }


    fun setFilterState(state:Boolean = false) = filterState.postValue(state)

}