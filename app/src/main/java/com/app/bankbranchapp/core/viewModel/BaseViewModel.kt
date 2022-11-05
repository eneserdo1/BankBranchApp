package com.app.bankbranchapp.core.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    var error = MutableLiveData<String>()
        protected set

    var loading = MutableLiveData<Boolean>()
        protected set
}