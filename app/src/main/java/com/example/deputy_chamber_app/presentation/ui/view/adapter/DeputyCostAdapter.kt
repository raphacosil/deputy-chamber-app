package com.example.deputy_chamber_app.presentation.ui.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.deputy_chamber_app.databinding.CostItemBinding
import com.example.deputy_chamber_app.domain.entity.CostItem
import com.example.deputy_chamber_app.presentation.ui.view.click_listener.OnLinkClickListener

class DeputyCostAdapter(
    private val deputyCostList: List<CostItem>,
    private val onLinkClickListener: OnLinkClickListener
) : RecyclerView.Adapter<DeputyCostAdapter.DeputyCostItemViewHolder>() {

    override fun getItemCount(): Int = deputyCostList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeputyCostItemViewHolder {
        val binding = CostItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DeputyCostItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DeputyCostItemViewHolder, position: Int) {
        holder.bind(deputyCostList[position])
    }

    inner class DeputyCostItemViewHolder(
        private val binding: CostItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(costItem: CostItem) {
            binding.apply {
                tvType.text = costItem.type
                tvValue.text = "R$ ${costItem.value}"
                tvInstallment.text =
                    if (costItem.installment != 0) "${costItem.installment}º parcela"
                    else "Não parcelado"
                tvFileLink.text = costItem.documentType
                tvFileLink.paintFlags =
                    tvFileLink.paintFlags or android.graphics.Paint.UNDERLINE_TEXT_FLAG
                tvFileLink.setOnClickListener {
                    onLinkClickListener.onLinkClick(costItem.documentLink)
                }
                tvSupplierName.text = costItem.supplier
            }
        }
    }
}