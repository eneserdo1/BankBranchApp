package com.app.bankbranchapp.ui.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.bankbranchapp.databinding.ItemBranchBinding
import com.app.bankbranchapp.business.models.BankListResponseItem
import com.app.bankbranchapp.ui.list.interfaces.BranchSelectedListener

class BankBranchRecyclerAdapter(val branchSelectListener:BranchSelectedListener) : ListAdapter<BankListResponseItem,BankBranchRecyclerAdapter.BranchViewHolder>(BranchDiffCallback),Filterable {

    private lateinit var binding : ItemBranchBinding

    private var list : ArrayList<BankListResponseItem> = arrayListOf()

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


    fun setData(bankList:List<BankListResponseItem>){
        this.list = bankList as ArrayList<BankListResponseItem>
        submitList(bankList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BranchViewHolder {
        binding = ItemBranchBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return BranchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BranchViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getFilter(): Filter {
        return customFilter
    }

    private val customFilter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filteredList = mutableListOf<BankListResponseItem>()
            if (constraint == null || constraint.isEmpty()) {
                filteredList.addAll(list)
            } else {
                for (item in list) {
                    if (item.dc_SEHIR.lowercase().startsWith(constraint.toString().lowercase())) {
                        filteredList.add(item)
                    }
                }
            }
            val results = FilterResults()
            results.values = filteredList
            return results
        }

        override fun publishResults(constraint: CharSequence?, filterResults: FilterResults?) {
            submitList(filterResults?.values as MutableList<BankListResponseItem>)
        }
    }
}