package com.example.deputy_chamber_app.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.deputy_chamber_app.data.datasource.DeputyPagingSource
import com.example.deputy_chamber_app.data.dto.DeputyCostsResponse
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

    override suspend fun getDeputyCosts(id: Int, page: Int?): List<CostItem> {
        val response = service.getDeputyCosts (id, page)
        if (response.isSuccessful) {
            return mapToCostItem(
                response.body()
            )
        }
        return emptyList()
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

fun mapToCostItem(deputyCostsResponse: DeputyCostsResponse?): List<CostItem>{
    return deputyCostsResponse?.data?.map {
        CostItem(
            type = it.type,
            supplier = it.supplier,
            supplierCpfCnpj = it.supplierCpfCnpj,
            installment = it.installment,
            value = it.value,
            documentType = it.documentType,
            documentLink = it.documentLink
        )
    } ?: emptyList()
}