package com.example.deputy_chamber_app.domain.usecase

import androidx.paging.PagingData
import com.example.deputy_chamber_app.domain.entity.CostItem
import com.example.deputy_chamber_app.domain.repository.DeputyRepository
import kotlinx.coroutines.flow.Flow

class GetDeputyCostsUseCase(
    private val deputyRepository: DeputyRepository
) {
    suspend operator fun invoke(deputyId: Int, pageSize: Int?): Flow<PagingData<CostItem>> =
        deputyRepository.getDeputyCosts(pageSize ?: 20, deputyId)
}
