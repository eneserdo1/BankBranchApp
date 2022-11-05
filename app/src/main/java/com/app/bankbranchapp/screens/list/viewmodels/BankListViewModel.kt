package com.app.bankbranchapp.screens.list.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.bankbranchapp.business.models.BankListResponse
import com.app.bankbranchapp.business.repositories.RepositoryImpl
import com.app.bankbranchapp.common.models.Status
import com.app.bankbranchapp.core.viewModel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BankListViewModel @Inject constructor(private val repository: RepositoryImpl) :
    BaseViewModel() {

    private var _bankBranchList = MutableLiveData<BankListResponse>()
    val bankBranchList: LiveData<BankListResponse> get() = _bankBranchList

    private var _filterState = MutableLiveData<Boolean>()
    var filterState = _filterState

    init {
        fetchAllBankBranchList()
    }

    private fun fetchAllBankBranchList() {
        viewModelScope.launch {
            repository.getAllBankBranchList().collect {
                when (it.status) {
                    Status.ERROR -> {
                        error.value = it.error?.statusMessage.toString()
                    }
                    Status.LOADING -> {
                        loading.value = true
                    }
                    Status.SUCCESS -> {
                        loading.value = false
                        it.data?.let { result ->
                            _bankBranchList.postValue(result)
                        }
                    }
                }
            }
        }
    }

    fun setFilterState(state: Boolean = false) = filterState.postValue(state)

}