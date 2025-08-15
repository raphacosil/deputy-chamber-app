package com.example.deputy_chamber_app.domain.usecase

import com.example.deputy_chamber_app.domain.entity.CostItem
import com.example.deputy_chamber_app.domain.repository.DeputyRepository

class GetDeputyCostsUseCase(
    private val deputyRepository: DeputyRepository
) {
    suspend operator fun invoke(id: Int, page: Int?): List<CostItem>{
        return deputyRepository.getDeputyCosts(id, page)
    }
}
