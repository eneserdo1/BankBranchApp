package com.app.bankbranchapp.screens.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.bankbranchapp.databinding.ItemBranchBinding
import com.app.bankbranchapp.presentation.models.BankListResponseItem
import com.app.bankbranchapp.screens.list.interfaces.BranchSelectedListener

class BankBranchRecyclerAdapter(val branchSelectListener:BranchSelectedListener) : ListAdapter<BankListResponseItem,BankBranchRecyclerAdapter.BranchViewHolder>(BranchDiffCallback) {

    private lateinit var binding : ItemBranchBinding

    object BranchDiffCallback : DiffUtil.ItemCallback<BankListResponseItem>(){
        override fun areItemsTheSame(
            oldItem: BankListResponseItem,
            newItem: BankListResponseItem
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: BankListResponseItem,
            newItem: BankListResponseItem
        ): Boolean {
            return oldItem.dc_BANK_KODU == newItem.dc_BANK_KODU
        }
    }

    inner class BranchViewHolder(private val binding: ItemBranchBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data:BankListResponseItem) = with(itemView){
            binding.apply {
                bankBranchName.text = data.dc_BANKA_SUBE
                bankBranchType.text = data.dc_BANKA_TIPI
            }
            itemView.setOnClickListener {
                branchSelectListener.selectedBranch(data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BranchViewHolder {
        binding = ItemBranchBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return BranchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BranchViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}