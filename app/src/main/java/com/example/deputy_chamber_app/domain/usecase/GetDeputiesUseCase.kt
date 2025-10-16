package com.example.deputy_chamber_app.domain.usecase

import com.example.deputy_chamber_app.domain.entity.DeputiesPage
import com.example.deputy_chamber_app.domain.repository.DeputyRepository

class GetDeputiesUseCase(
    private val deputyRepository: DeputyRepository
) {
    suspend operator fun invoke(pageSize: Int): DeputiesPage {
        return deputyRepository.getDeputies(pageSize)
    }
}