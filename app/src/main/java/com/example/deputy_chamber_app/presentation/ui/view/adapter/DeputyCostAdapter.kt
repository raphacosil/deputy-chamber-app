package com.example.deputy_chamber_app.presentation.ui.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.deputy_chamber_app.databinding.CostItemBinding
import com.example.deputy_chamber_app.domain.entity.CostItem
import com.example.deputy_chamber_app.presentation.ui.view.click_listener.OnLinkClickListener

class DeputyCostAdapter(
    private val onLinkClickListener: OnLinkClickListener
) : PagingDataAdapter<CostItem, DeputyCostAdapter.Holder>(DIFF_CALLBACK) {

    inner class Holder(val binding: CostItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeputyCostAdapter.Holder {
        return Holder(
            CostItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: DeputyCostAdapter.Holder, position: Int) {
        val deputyItem = getItem(position) ?: return
            holder.binding.apply {
                tvType.text = deputyItem.type
                tvValue.text = "R$ ${deputyItem.value}"
                tvInstallment.text =
                    if (deputyItem.installment != 0) "${deputyItem.installment}º parcela"
                    else "Não parcelado"
                tvFileLink.text = deputyItem.documentType
                tvFileLink.paintFlags =
                    tvFileLink.paintFlags or android.graphics.Paint.UNDERLINE_TEXT_FLAG
                tvFileLink.setOnClickListener {
                    onLinkClickListener.onLinkClick(deputyItem.documentLink)
                }
                tvSupplierName.text = deputyItem.supplier
            }

    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CostItem>() {
            override fun areItemsTheSame(oldItem: CostItem, newItem: CostItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: CostItem, newItem: CostItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}