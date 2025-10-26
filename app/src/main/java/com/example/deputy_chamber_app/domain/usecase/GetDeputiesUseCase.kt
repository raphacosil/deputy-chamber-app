package com.example.deputy_chamber_app.domain.usecase

import androidx.paging.PagingData
import com.example.deputy_chamber_app.domain.entity.DeputyItem
import com.example.deputy_chamber_app.domain.repository.DeputyRepository
import kotlinx.coroutines.flow.Flow

class GetDeputiesUseCase(
    private val deputyRepository: DeputyRepository
) {
    suspend operator fun invoke(pageSize: Int): Flow<PagingData<DeputyItem>> =
        deputyRepository.getDeputies(pageSize)
}
