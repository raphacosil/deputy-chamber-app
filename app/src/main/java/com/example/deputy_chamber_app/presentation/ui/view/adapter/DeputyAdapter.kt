package com.example.deputy_chamber_app.presentation.ui.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.deputy_chamber_app.R
import com.example.deputy_chamber_app.VIEW_TYPE_FOOTER
import com.example.deputy_chamber_app.VIEW_TYPE_ITEM
import com.example.deputy_chamber_app.databinding.DeputyItemBinding
import com.example.deputy_chamber_app.databinding.PaginationItemBinding
import com.example.deputy_chamber_app.domain.entity.DeputyItem
import com.example.deputy_chamber_app.presentation.ui.view.click_listener.OnAdvancePaginationClickListener
import com.example.deputy_chamber_app.presentation.ui.view.click_listener.OnDeputyItemClickListener
import com.example.deputy_chamber_app.presentation.ui.view.click_listener.OnReturnPaginationClickListener

class DeputyAdapter(
    private val deputyItemList: List<DeputyItem>,
    private val onDeputyItemClickListener: OnDeputyItemClickListener,
    private val onReturnPaginationClickListener: OnReturnPaginationClickListener,
    private val onAdvancePaginationClickListener: OnAdvancePaginationClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount() = deputyItemList.size + 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_ITEM) {
                val binding = DeputyItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                DeputyItemViewHolder(binding)
            } else {
                val binding = PaginationItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                PaginationItemHolder(binding)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == deputyItemList.size) VIEW_TYPE_FOOTER else VIEW_TYPE_ITEM
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is DeputyItemViewHolder && position < deputyItemList.size){
            holder.bind(deputyItemList[position])
        }

        holder.itemView.setOnClickListener {
            onDeputyItemClickListener.onDeputyItemClick(deputyItemList[position].id)
        }
    }

    inner class DeputyItemViewHolder(
        private val binding: DeputyItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(deputyItem: DeputyItem){
            binding.apply {
                textViewName.text = deputyItem.name
                textViewEmail.text = deputyItem.email
                textViewEmail.paintFlags = textViewEmail.paintFlags or android.graphics.Paint.UNDERLINE_TEXT_FLAG
                textViewParty.text = deputyItem.party
                textViewUf.text = mapUf[deputyItem.uf]

                Glide.with(binding.root)
                    .load(deputyItem.imageUrl)
                    .placeholder(R.drawable.deputy_placeholder)
                    .error(R.drawable.deputy_placeholder)
                    .into(imageView)
            }
        }
    }

    inner class PaginationItemHolder(
        binding: PaginationItemBinding
    ): RecyclerView.ViewHolder(binding.root){
        init {
            binding.btnAdvance.setOnClickListener {
                onAdvancePaginationClickListener.onAdvancePaginationClick()
            }
            binding.btnReturn.setOnClickListener {
                onReturnPaginationClickListener.onReturnPaginationClick()
            }
        }
    }

    private val mapUf = mapOf(
        "AC" to "Acre",
        "AL" to "Alagoas",
        "AP" to "Amapá",
        "AM" to "Amazonas",
        "BA" to "Bahia",
        "CE" to "Ceará",
        "DF" to "Distrito Federal",
        "ES" to "Espírito Santo",
        "GO" to "Goiás",
        "MA" to "Maranhão",
        "MT" to "Mato Grosso",
        "MS" to "Mato Grosso do Sul",
        "MG" to "Minas Gerais",
        "PA" to "Pará",
        "PB" to "Paraíba",
        "PR" to "Paraná",
        "PE" to "Pernambuco",
        "PI" to "Piauí",
        "RJ" to "Rio de Janeiro",
        "RN" to "Rio Grande do Norte",
        "RS" to "Rio Grande do Sul",
        "RO" to "Rondônia",
        "RR" to "Roraima",
        "SC" to "Santa Catarina",
        "SP" to "São Paulo",
        "SE" to "Sergipe",
        "TO" to "Tocantins"
    )
}
