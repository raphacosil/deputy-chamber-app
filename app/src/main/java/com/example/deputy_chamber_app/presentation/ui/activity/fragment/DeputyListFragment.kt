package com.example.deputy_chamber_app.presentation.ui.activity.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.deputy_chamber_app.databinding.FragmentDeputyListBinding
import com.example.deputy_chamber_app.presentation.ui.entity.DeputyItem
import com.example.deputy_chamber_app.presentation.ui.view.adapter.DeputyAdapter
import com.example.deputy_chamber_app.presentation.ui.view.click_listener.OnDeputyItemClickListener
import com.example.deputy_chamber_app.presentation.ui.view.style.SpaceItemDecoration

class DeputyListFragment : Fragment(), OnDeputyItemClickListener {
    private lateinit var binding: FragmentDeputyListBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDeputyListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val deputies = listOf(
            DeputyItem(
                id = 1,
                name = "João Silva",
                email = "joao.silva@camara.leg.br",
                party = "PT",
                uf = "SP",
                imageUrl = "https://placehold.co/300x400/png"
            ),
            DeputyItem(
                id = 2,
                name = "Maria Oliveira",
                email = "maria.oliveira@camara.leg.br",
                party = "PSDB",
                uf = "RJ",
                imageUrl = "https://placehold.co/300x400/png"
            ),
            DeputyItem(
                id = 3,
                name = "Carlos Souza",
                email = "carlos.souza@camara.leg.br",
                party = "MDB",
                uf = "MG",
                imageUrl = "https://placehold.co/300x400/png"
            ),
            DeputyItem(
                id = 4,
                name = "Ana Lima",
                email = "ana.lima@camara.leg.br",
                party = "PDT",
                uf = "RS",
                imageUrl = "https://placehold.co/300x400/png"
            ),
            DeputyItem(
                id = 5,
                name = "Pedro Costa",
                email = "pedro.costa@camara.leg.br",
                party = "DEM",
                uf = "BA",
                imageUrl = "https://placehold.co/300x400/png"
            )
        )
        setupRecycler(deputies)
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
        Log.d("TAG", "onDeputyItemClick: $deputyId")
    }
}