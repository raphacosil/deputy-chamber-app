package com.example.deputy_chamber_app.presentation.ui.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.deputy_chamber_app.databinding.ActivityDeputyCostsBinding
import com.example.deputy_chamber_app.presentation.ui.view.adapter.DeputyCostAdapter
import com.example.deputy_chamber_app.presentation.ui.view.click_listener.OnLinkClickListener
import com.example.deputy_chamber_app.presentation.ui.view.style.SpaceItemDecoration
import com.example.deputy_chamber_app.presentation.viewmodel.DeputyCostViewModel
import com.example.deputy_chamber_app.presentation.viewmodel.action.DeputyCostAction
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class DeputyCostsActivity : AppCompatActivity(), OnLinkClickListener {
    private lateinit var binding: ActivityDeputyCostsBinding
    private val viewModel: DeputyCostViewModel by viewModel()

    private lateinit var adapter: DeputyCostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeputyCostsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnExit.setOnClickListener {
            finish()
        }
        val deputyId = intent.getIntExtra("deputyId", 0)
        setupRecycler()

        try {
            loading(true)
            viewModel.handleAction(DeputyCostAction.LoadData(deputyId))
            setupObserver()
        } catch (e: Exception) {
            Log.d("DeputyListFragment", "onViewCreated: $e")
            loading(false)
        }
    }

    private fun setupObserver() {
        viewModel.deputyCostState.observe(this) {
            loading(it.isLoading)

            it.data?.let { flow ->
                this.lifecycleScope.launch {
                    flow.collectLatest { pagingData ->
                        adapter.submitData(pagingData)
                    }
                }
            }

            binding.recyclerView.visibility = if(it.isLoading) View.GONE else View.VISIBLE

            it.errorMessage?.let { error ->
                Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupRecycler() = binding.recyclerView.apply {
        adapter = DeputyCostAdapter(this@DeputyCostsActivity)
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.addItemDecoration(SpaceItemDecoration(32))
        binding.recyclerView.adapter = adapter
    }

    private fun loading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    override fun onLinkClick(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }
}