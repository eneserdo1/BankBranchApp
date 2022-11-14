package com.app.bankbranchapp.ui.list.interfaces

import com.app.bankbranchapp.business.models.BankListResponseItem

interface BranchSelectedListener {

    fun selectedBranch(item:BankListResponseItem)

}