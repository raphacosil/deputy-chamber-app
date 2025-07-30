package com.example.deputy_chamber_app.presentation.ui.activity.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.deputy_chamber_app.databinding.FragmentDeputyListBinding
import com.example.deputy_chamber_app.presentation.ui.activity.DeputyDetailActivity
import com.example.deputy_chamber_app.domain.entity.DeputyItem
import com.example.deputy_chamber_app.presentation.ui.view.adapter.DeputyAdapter
import com.example.deputy_chamber_app.presentation.ui.view.click_listener.OnDeputyItemClickListener
import com.example.deputy_chamber_app.presentation.ui.view.style.SpaceItemDecoration
import com.example.deputy_chamber_app.presentation.viewmodel.DeputyListViewModel
import com.example.deputy_chamber_app.presentation.viewmodel.action.DeputyListAction
import org.koin.androidx.viewmodel.ext.android.viewModel

class DeputyListFragment : Fragment(), OnDeputyItemClickListener {
    private lateinit var binding: FragmentDeputyListBinding
    private val viewModel: DeputyListViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDeputyListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loading(true)
        try {
            viewModel.handleAction(DeputyListAction.LoadData(null))
            setupObserver()
        } catch (e: Exception) {
            Log.d("DeputyListFragment", "onViewCreated: $e")
            Toast.makeText(requireContext(), "Error fetching deputies", Toast.LENGTH_SHORT).show()
            loading(false)
        }
    }

    private fun setupObserver() {
        viewModel.deputyListState .observe(viewLifecycleOwner) {
            loading(it.isLoading)
            setupRecycler(it.data)
            it.errorMessage?.let { error ->
                Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupRecycler(feedList: List<DeputyItem>) = binding.recyclerView.apply {
        val deputyItemListAdapter = DeputyAdapter(
            feedList,
            this@DeputyListFragment
        )
        adapter = deputyItemListAdapter
        layoutManager = LinearLayoutManager(context)
        addItemDecoration(SpaceItemDecoration(32))
    }

    override fun onDeputyItemClick(deputyId: Int) {
        val intent = Intent(requireContext(), DeputyDetailActivity::class.java)
        intent.putExtra("deputyId", deputyId)
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