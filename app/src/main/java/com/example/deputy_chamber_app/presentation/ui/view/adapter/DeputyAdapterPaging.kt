package com.example.deputy_chamber_app.presentation.ui.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.deputy_chamber_app.R
import com.example.deputy_chamber_app.databinding.DeputyItemBinding
import com.example.deputy_chamber_app.domain.entity.DeputyItem
import com.example.deputy_chamber_app.presentation.ui.view.click_listener.OnDeputyItemClickListener

class DeputyAdapterPaging(
    private val onDeputyItemClickListener: OnDeputyItemClickListener,
) : PagingDataAdapter<DeputyItem, DeputyAdapterPaging.Holder>(DIFF_CALLBACK) {

    inner class Holder(val binding: DeputyItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            DeputyItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val deputyItem = getItem(position) ?: return

        holder.binding.apply {
            textViewName.text = deputyItem.name
            textViewEmail.text = deputyItem.email
            textViewEmail.paintFlags =
                textViewEmail.paintFlags or android.graphics.Paint.UNDERLINE_TEXT_FLAG
            textViewParty.text = deputyItem.party
            textViewUf.text = mapUf[deputyItem.uf]

            Glide.with(root)
                .load(deputyItem.imageUrl)
                .placeholder(R.drawable.deputy_placeholder)
                .error(R.drawable.deputy_placeholder)
                .into(imageView)
        }
        holder.itemView.setOnClickListener {
            onDeputyItemClickListener.onDeputyItemClick(deputyItem.id)
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DeputyItem>() {
            override fun areItemsTheSame(oldItem: DeputyItem, newItem: DeputyItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: DeputyItem, newItem: DeputyItem): Boolean {
                return oldItem == newItem
            }
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