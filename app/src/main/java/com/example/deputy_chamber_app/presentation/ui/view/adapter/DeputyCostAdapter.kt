package com.example.deputy_chamber_app.presentation.ui.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.deputy_chamber_app.VIEW_TYPE_FOOTER
import com.example.deputy_chamber_app.VIEW_TYPE_ITEM
import com.example.deputy_chamber_app.databinding.CostItemBinding
import com.example.deputy_chamber_app.databinding.PaginationItemBinding
import com.example.deputy_chamber_app.domain.entity.CostItem
import com.example.deputy_chamber_app.presentation.ui.view.click_listener.OnAdvancePaginationClickListener
import com.example.deputy_chamber_app.presentation.ui.view.click_listener.OnLinkClickListener
import com.example.deputy_chamber_app.presentation.ui.view.click_listener.OnReturnPaginationClickListener

class DeputyCostAdapter(
    private val deputyCostList: List<CostItem>,
    private val onReturnPaginationClickListener: OnReturnPaginationClickListener,
    private val onAdvancePaginationClickListener: OnAdvancePaginationClickListener,
    private val onLinkClickListener: OnLinkClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount() = deputyCostList.size + 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_ITEM) {
            val binding = CostItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            DeputyCostItemViewHolder(binding)
        } else {
            val binding =
                PaginationItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            PaginationItemHolder(binding)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == deputyCostList.size) VIEW_TYPE_FOOTER else VIEW_TYPE_ITEM
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is DeputyCostItemViewHolder && position < deputyCostList.size) {
            holder.bind(deputyCostList[position])
        }
    }

    inner class DeputyCostItemViewHolder(
        private val binding: CostItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(costItem: CostItem) {
            binding.apply {
                tvType.text = costItem.type
                tvValue.text = "R$ ${costItem.value}"
                tvInstallment.text = if(costItem.installment!=0) "${costItem.installment}º parcela"
                else "Não parcelado"
                tvFileLink.text = costItem.documentType
                tvFileLink.setOnClickListener {
                    onLinkClickListener.onLinkClick(costItem.documentLink)
                }
                tvFileLink.paintFlags = tvFileLink.paintFlags or android.graphics.Paint.UNDERLINE_TEXT_FLAG
                tvSupplierName.text = costItem.supplier
            }
        }
    }

    inner class PaginationItemHolder(
        binding: PaginationItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.btnAdvance.setOnClickListener {
                onAdvancePaginationClickListener.onAdvancePaginationClick()
            }
            binding.btnReturn.setOnClickListener {
                onReturnPaginationClickListener.onReturnPaginationClick()
            }
        }
    }
}