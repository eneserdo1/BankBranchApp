package com.app.bankbranchapp.screens.list.interfaces

import com.app.bankbranchapp.presentation.models.BankListResponseItem

interface BranchSelectedListener {

    fun selectedBranch(item:BankListResponseItem)

}