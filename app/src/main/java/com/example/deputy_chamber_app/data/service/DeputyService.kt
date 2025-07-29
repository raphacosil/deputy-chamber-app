package com.example.deputy_chamber_app.data.service

import com.example.deputy_chamber_app.data.dto.DeputyDetailResponse
import com.example.deputy_chamber_app.data.dto.GetDeputiesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DeputyService {

    @GET("deputados")
    suspend fun getDeputies(
        @Path("page") page: Int?
    ): Response<GetDeputiesResponse>

    @GET("deputados/{id}")
    suspend fun getDeputyDetail(
        @Path("id") id: Int
    ): Response<DeputyDetailResponse>
}