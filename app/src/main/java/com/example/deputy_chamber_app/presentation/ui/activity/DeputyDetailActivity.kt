package com.example.deputy_chamber_app.presentation.ui.activity

import android.content.Intent
import android.content.res.ColorStateList
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.deputy_chamber_app.R
import com.example.deputy_chamber_app.databinding.ActivityDeputyDetailBinding
import com.example.deputy_chamber_app.domain.entity.DeputyDetail
import com.example.deputy_chamber_app.presentation.ui.view.adapter.SocialMediaAdapter
import com.example.deputy_chamber_app.presentation.ui.view.click_listener.OnSocialMediaItemClickListener
import com.example.deputy_chamber_app.presentation.viewmodel.DeputyDetailViewModel
import com.example.deputy_chamber_app.presentation.viewmodel.action.DeputyDetailAction
import org.koin.androidx.viewmodel.ext.android.viewModel

class DeputyDetailActivity : AppCompatActivity(), OnSocialMediaItemClickListener {
    private lateinit var binding: ActivityDeputyDetailBinding
    private val viewModel: DeputyDetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeputyDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnExit.setOnClickListener {
            finish()
        }
        val deputyId = intent.getIntExtra("deputyId", 0)

        try {
            loading(true)
            viewModel.handleAction(DeputyDetailAction.LoadData(deputyId))
            setupObserver()
        } catch (e: Exception) {
            Log.d("DeputyListFragment", "onViewCreated: $e")
            loading(false)
        }
    }

    private fun setupObserver() {
        viewModel.deputyDetailState.observe(this) {
            loading(it.isLoading)
            setupContent(it.data)
            it.errorMessage?.let { error ->
                Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    private fun setupContent(deputyInfo: DeputyDetail?) {
        if (deputyInfo != null) {
            Glide.with(this)
                .load(deputyInfo.imageUrl)
                .placeholder(R.drawable.deputy_placeholder)
                .error(R.drawable.deputy_placeholder)
                .into(binding.ivDeputyPhoto)

            binding.tvName.text = capitalize(deputyInfo.civilName)
            binding.tvParty.text = deputyInfo.party
            binding.tvUf.text = deputyInfo.uf
            setupSituation(deputyInfo.situation)

            binding.tvBirthDate.text = getString(R.string.born_in).format(deputyInfo.birthDate)

            binding.tvCityState.text = getString(R.string.natural).format(
                deputyInfo.birthMunicipality,
                deputyInfo.birthState
            )

            deputyInfo.schooling.let {
                if (it == "") binding.tvSchooling.visibility =
                    View.GONE else binding.tvSchooling.text =  getString(R.string.schooling).format(it)
            }

            binding.tvCabinetInfo.text = getString(R.string.cabinet).format(
                deputyInfo.building,
                deputyInfo.floor,
                deputyInfo.room
            )
            deputyInfo.phone.let {
                if (it == "") binding.tvPhone.visibility = View.GONE else binding.tvPhone.text = it
            }
            binding.tvPhone.paintFlags =
                binding.tvPhone.paintFlags or android.graphics.Paint.UNDERLINE_TEXT_FLAG

            binding.tvPhone.setOnClickListener {
                val intent = Intent(Intent.ACTION_DIAL)
                intent.data = Uri.parse("tel:${deputyInfo.phone}")
                startActivity(intent)
            }

            deputyInfo.email.let {
                if (it == "") binding.tvEmail.visibility = View.GONE else binding.tvEmail.text = it
            }
            binding.tvEmail.paintFlags =
                binding.tvEmail.paintFlags or android.graphics.Paint.UNDERLINE_TEXT_FLAG

            binding.tvEmail.setOnClickListener {
                val intent = Intent(Intent.ACTION_SENDTO)
                intent.data = Uri.parse("mailto:${deputyInfo.email}")
                startActivity(intent)
            }

            setupLinkRecycler(deputyInfo.socialMedia)

            loading(false)
        }
    }

    private fun setupSituation(situation: String) {
        binding.tvSituation.text = situation
        when (situation) {
            "Ativo" -> {
                binding.vIcon.background = ContextCompat.getDrawable(this, R.drawable.status_style)
                binding.vIcon.backgroundTintList = ColorStateList.valueOf(
                    ContextCompat.getColor(this, R.color.blue)
                )
                binding.tvSituation.setTextColor(ContextCompat.getColor(this, R.color.blue))
            }
            "Inativo" -> {
                binding.vIcon.background = ContextCompat.getDrawable(this, R.drawable.status_style)
                binding.vIcon.backgroundTintList = ColorStateList.valueOf(
                    ContextCompat.getColor(this, R.color.red)
                )
                binding.tvSituation.setTextColor(ContextCompat.getColor(this, R.color.red))
            }
            "Afastado" -> {
                binding.vIcon.background = ContextCompat.getDrawable(this, R.drawable.status_style)
                binding.vIcon.backgroundTintList = ColorStateList.valueOf(
                    ContextCompat.getColor(this, R.color.yellow)
                )
                binding.tvSituation.setTextColor(ContextCompat.getColor(this, R.color.yellow))
            }
            "Convocado" -> {
                binding.vIcon.background = ContextCompat.getDrawable(this, R.drawable.status_style)
                binding.vIcon.backgroundTintList = ColorStateList.valueOf(
                    ContextCompat.getColor(this, R.color.green)
                )
                binding.tvSituation.setTextColor(ContextCompat.getColor(this, R.color.green))
            }
            "Exercício" -> {
                binding.vIcon.background = ContextCompat.getDrawable(this, R.drawable.status_style)
                binding.vIcon.backgroundTintList = ColorStateList.valueOf(
                    ContextCompat.getColor(this, R.color.green)
                )
                binding.tvSituation.setTextColor(ContextCompat.getColor(this, R.color.green))
            }
            "Fim de Mandato" -> {
                binding.vIcon.background = ContextCompat.getDrawable(this, R.drawable.status_style)
                binding.vIcon.backgroundTintList = ColorStateList.valueOf(
                    ContextCompat.getColor(this, R.color.red)
                )
                binding.tvSituation.setTextColor(ContextCompat.getColor(this, R.color.red))
            }
            "Licença" -> {
                binding.vIcon.background = ContextCompat.getDrawable(this, R.drawable.status_style)
                binding.vIcon.backgroundTintList = ColorStateList.valueOf(
                    ContextCompat.getColor(this, R.color.yellow)
                )
                binding.tvSituation.setTextColor(ContextCompat.getColor(this, R.color.yellow))
            }
            "Suplência" -> {
                binding.vIcon.background = ContextCompat.getDrawable(this, R.drawable.status_style)
                binding.vIcon.backgroundTintList = ColorStateList.valueOf(
                    ContextCompat.getColor(this, R.color.yellow)
                )
                binding.tvSituation.setTextColor(ContextCompat.getColor(this, R.color.yellow))
            }
            "Suspenso" -> {
                binding.vIcon.background = ContextCompat.getDrawable(this, R.drawable.status_style)
                binding.vIcon.backgroundTintList = ColorStateList.valueOf(
                    ContextCompat.getColor(this, R.color.red)
                )
                binding.tvSituation.setTextColor(ContextCompat.getColor(this, R.color.red))
            }
            "Vacência" -> {
                binding.vIcon.background = ContextCompat.getDrawable(this, R.drawable.status_style)
                binding.vIcon.backgroundTintList = ColorStateList.valueOf(
                    ContextCompat.getColor(this, R.color.green)
                )
                binding.tvSituation.setTextColor(ContextCompat.getColor(this, R.color.green))
            }
        }
    }

    private fun setupLinkRecycler(links: List<String>?) {
        if (!links.isNullOrEmpty()) {
            binding.rvSocialMedia.apply {
                val deputyItemListAdapter = SocialMediaAdapter(
                    links,
                    this@DeputyDetailActivity
                )
                adapter = deputyItemListAdapter
                layoutManager = LinearLayoutManager(context)
            }
        } else {
            binding.deputyRedesLinear.visibility = View.GONE
        }
    }

    private fun capitalize(s: String?): String {
        if (s.isNullOrBlank()) return ""
        return s.split(" ").joinToString(" ") {
            it.lowercase().replaceFirstChar { c -> c.uppercaseChar() }
        }
    }

    override fun onSocialMediaItemClick(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }
    private fun loading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}