package com.app.bankbranchapp.core.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import android.app.AlertDialog
import com.app.bankbranchapp.R


abstract class BaseFragment<VB : ViewBinding>(private val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> VB) : Fragment() {

    private var _binding: VB? = null
    val binding get() = _binding!!

    lateinit var dialog : AlertDialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater.invoke(inflater, container, false)

        setLoader()
        return binding.root
    }

    private fun setLoader() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        builder.setCancelable(false)
        builder.setView(R.layout.layout_loading_dialog)
        dialog = builder.create()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}