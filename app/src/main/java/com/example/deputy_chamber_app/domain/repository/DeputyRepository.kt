package com.example.deputy_chamber_app.domain.repository

import androidx.paging.PagingData
import com.example.deputy_chamber_app.domain.entity.CostItem
import com.example.deputy_chamber_app.domain.entity.DeputyDetail
import com.example.deputy_chamber_app.domain.entity.DeputyItem
import java.util.concurrent.Flow

interface DeputyRepository {
    suspend fun getDeputies(pageSize: Int): Flow<PagingData<DeputyItem>>

    suspend fun getDeputyDetail(id: Int): DeputyDetail?

    suspend fun getDeputyCosts(id: Int, page: Int?): List<CostItem>
}