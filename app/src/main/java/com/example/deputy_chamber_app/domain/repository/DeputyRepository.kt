package com.example.deputy_chamber_app.domain.repository

import com.example.deputy_chamber_app.domain.entity.CostItem
import com.example.deputy_chamber_app.domain.entity.DeputiesPage
import com.example.deputy_chamber_app.domain.entity.DeputyDetail

interface DeputyRepository {
    suspend fun getDeputies(page: Int?): DeputiesPage?

    suspend fun getDeputyDetail(id: Int): DeputyDetail?

    suspend fun getDeputyCosts(id: Int, page: Int?): List<CostItem>
}