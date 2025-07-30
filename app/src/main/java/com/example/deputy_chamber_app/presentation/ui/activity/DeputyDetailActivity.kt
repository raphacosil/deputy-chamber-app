package com.example.deputy_chamber_app.presentation.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.deputy_chamber_app.R
import com.example.deputy_chamber_app.databinding.ActivityDeputyDetailBinding
import com.example.deputy_chamber_app.domain.entity.DeputyDetail
import com.example.deputy_chamber_app.presentation.viewmodel.DeputyDetailViewModel
import com.example.deputy_chamber_app.presentation.viewmodel.action.DeputyDetailAction
import org.koin.androidx.viewmodel.ext.android.viewModel

class DeputyDetailActivity : AppCompatActivity() {
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
            Toast.makeText(this, "Error fetching deputies", Toast.LENGTH_SHORT).show()
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
            binding.tvName.text = deputyInfo.name
            binding.tvParty.text = deputyInfo.party
            binding.tvUf.text = deputyInfo.uf

            binding.tvBirthDate.text = getString(R.string.born_in).format(deputyInfo.birthDate)
            binding.tvCityState.text = getString(R.string.natural).format(
                deputyInfo.birthMunicipality,
                deputyInfo.birthState
            )
            binding.tvSchooling.text = getString(R.string.schooling).format(deputyInfo.schooling)

            binding.tvCabinetInfo.text = getString(R.string.cabinet).format(
                deputyInfo.building,
                deputyInfo.floor,
                deputyInfo.room
            )
            binding.tvPhone.text = deputyInfo.phone
            binding.tvEmail.text = deputyInfo.email
        }
    }

    private fun loading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}