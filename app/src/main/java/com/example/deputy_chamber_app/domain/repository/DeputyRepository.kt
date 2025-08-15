package com.example.deputy_chamber_app.domain.repository

import com.example.deputy_chamber_app.domain.entity.CostItem
import com.example.deputy_chamber_app.domain.entity.DeputyDetail
import com.example.deputy_chamber_app.domain.entity.DeputyItem

interface DeputyRepository {
    suspend fun getDeputies(page: Int?): List<DeputyItem>

    suspend fun getDeputyDetail(id: Int): DeputyDetail?

    suspend fun getDeputyCosts(id: Int, page: Int?): List<CostItem>
}