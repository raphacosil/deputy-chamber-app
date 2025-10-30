package com.example.deputy_chamber_app.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.deputy_chamber_app.data.datasource.DeputyCostPagingSource
import com.example.deputy_chamber_app.data.datasource.DeputyPagingSource
import com.example.deputy_chamber_app.data.dto.CostItemDto
import com.example.deputy_chamber_app.data.dto.DeputyDetailResponse
import com.example.deputy_chamber_app.data.dto.DeputyItemDto
import com.example.deputy_chamber_app.data.service.DeputyService
import com.example.deputy_chamber_app.domain.entity.CostItem
import com.example.deputy_chamber_app.domain.entity.DeputyDetail
import com.example.deputy_chamber_app.domain.entity.DeputyItem
import com.example.deputy_chamber_app.domain.repository.DeputyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DeputyRepositoryImpl(
    private val service: DeputyService,
    private val pagingSource: DeputyPagingSource
): DeputyRepository {
    override suspend fun getDeputies(pageSize: Int): Flow<PagingData<DeputyItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = pageSize,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { pagingSource }
        ).flow.map { response ->
            response.map { mapToDeputyItem(it) }

        }
    }

    override suspend fun getDeputyDetail(id: Int): DeputyDetail? {
        val response = service.getDeputyDetail(id)
        if (response.isSuccessful) {
            return mapToDeputyDetail(
                response.body()
            )
        }
        return null
    }

    override suspend fun getDeputyCosts(deputyId: Int, pageSize: Int?): Flow<PagingData<CostItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = pageSize?:15,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { DeputyCostPagingSource(service, deputyId) }
        ).flow.map { response ->
            response.map { mapToCostItem(it) }

        }
    }
}

fun mapToDeputyItem(deputyItemDto: DeputyItemDto): DeputyItem {
    return DeputyItem(
        id = deputyItemDto.id,
        name = deputyItemDto.name,
        email = deputyItemDto.email,
        party = deputyItemDto.party,
        uf = deputyItemDto.uf,
        imageUrl = deputyItemDto.urlPhoto
    )
}

fun mapToDeputyDetail(detailResponse: DeputyDetailResponse?): DeputyDetail? {
    if (detailResponse != null) {
        return DeputyDetail(
            id = detailResponse.data.id,
            civilName = detailResponse.data.civilName,
            party = detailResponse.data.lastStatus.party,
            uf = detailResponse.data.lastStatus.uf,
            imageUrl = detailResponse.data.lastStatus.urlPhoto,
            situation = detailResponse.data.lastStatus.situation,
            birthDate = detailResponse.data.dataNascimento,
            birthState = detailResponse.data.birthState,
            birthMunicipality = detailResponse.data.birthMunicipality,
            schooling = detailResponse.data.schooling,
            cabinetName = detailResponse.data.lastStatus.cabinet?.name,
            building = detailResponse.data.lastStatus.cabinet?.building,
            room = detailResponse.data.lastStatus.cabinet?.room,
            floor = detailResponse.data.lastStatus.cabinet?.floor,
            phone = detailResponse.data.lastStatus.cabinet?.phone,
            email = detailResponse.data.lastStatus.cabinet?.email,
            socialMedia = detailResponse.data.socialMedia
        )
    }
    return null
}

fun mapToCostItem(costItemDto: CostItemDto): CostItem{
    return CostItem(
        id = costItemDto.documentNumber,
        type = costItemDto.type,
        supplier = costItemDto.supplier,
        supplierCpfCnpj = costItemDto.supplierCpfCnpj,
        installment = costItemDto.installment,
        value = costItemDto.value,
        documentType = costItemDto.documentType,
        documentLink = costItemDto.documentLink
    )
}
