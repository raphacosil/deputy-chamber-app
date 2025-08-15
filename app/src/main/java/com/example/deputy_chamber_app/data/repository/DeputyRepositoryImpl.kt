package com.example.deputy_chamber_app.data.repository

import android.util.Log
import com.example.deputy_chamber_app.data.dto.DeputyCostsResponse
import com.example.deputy_chamber_app.data.dto.DeputyDetailResponse
import com.example.deputy_chamber_app.data.dto.GetDeputiesResponse
import com.example.deputy_chamber_app.data.service.DeputyService
import com.example.deputy_chamber_app.domain.entity.CostItem
import com.example.deputy_chamber_app.domain.entity.DeputyDetail
import com.example.deputy_chamber_app.domain.entity.DeputyItem
import com.example.deputy_chamber_app.domain.repository.DeputyRepository

class DeputyRepositoryImpl(
    private val service: DeputyService
): DeputyRepository {
    override suspend fun getDeputies(page: Int?): List<DeputyItem> {
        val response = service.getDeputies(page)
        if (response.isSuccessful) {
            return mapToDeputyItem(
                response.body()
            )
        }
        return emptyList()
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

fun mapToDeputyItem(getDeputiesResponse: GetDeputiesResponse?): List<DeputyItem> {
    return getDeputiesResponse?.data?.map {
        DeputyItem(
            id = it.id,
            name = it.name,
            email = it.email,
            party = it.party,
            uf = it.uf,
            imageUrl = it.urlPhoto
        )
    } ?: emptyList()
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