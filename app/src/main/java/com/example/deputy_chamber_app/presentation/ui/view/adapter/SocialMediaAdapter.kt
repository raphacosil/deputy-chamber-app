package com.example.deputy_chamber_app.presentation.ui.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.deputy_chamber_app.R
import com.example.deputy_chamber_app.databinding.DeputyItemBinding
import com.example.deputy_chamber_app.databinding.TvSocialMediaItemBinding
import com.example.deputy_chamber_app.presentation.ui.view.click_listener.OnSocialMediaItemClickListener

class SocialMediaAdapter(
    private val socialMediaItemList: List<String>,
    private val onSocialMediaItemClickListener: OnSocialMediaItemClickListener
) : RecyclerView.Adapter<SocialMediaAdapter.Holder>() {

    inner class Holder(val binding: TvSocialMediaItemBinding) : RecyclerView.ViewHolder(binding.root)
    override fun getItemCount() = socialMediaItemList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            TvSocialMediaItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.binding.apply {
            val socialMedia = socialMediaItemList[position]
            textViewSocialMediaLink.text = socialMedia
            textViewSocialMediaLink.paintFlags =
                textViewSocialMediaLink.paintFlags or android.graphics.Paint.UNDERLINE_TEXT_FLAG
        }

        holder.itemView.setOnClickListener {
            onSocialMediaItemClickListener.onSocialMediaItemClick(socialMediaItemList[position])
        }
    }
}