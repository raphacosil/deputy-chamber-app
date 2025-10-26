package com.example.deputy_chamber_app.domain.usecase

import com.example.deputy_chamber_app.domain.entity.DeputyDetail
import com.example.deputy_chamber_app.domain.repository.DeputyRepository

class GetDeputyDetailUseCase(
    private val deputyRepository: DeputyRepository
) {
    suspend operator fun invoke(id: Int): DeputyDetail? =
        deputyRepository.getDeputyDetail(id)
}