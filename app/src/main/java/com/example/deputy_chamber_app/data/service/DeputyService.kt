package com.example.deputy_chamber_app.data.service

import com.example.deputy_chamber_app.data.dto.DeputyCostsResponse
import com.example.deputy_chamber_app.data.dto.DeputyDetailResponse
import com.example.deputy_chamber_app.data.dto.GetDeputiesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DeputyService {

    @GET("deputados")
    suspend fun getDeputies(
        @Query("pagina") page: Int?,
        @Query("itens") items: Int = 15
    ): Response<GetDeputiesResponse>

    @GET("deputados/{id}")
    suspend fun getDeputyDetail(
        @Path("id") id: Int
    ): Response<DeputyDetailResponse>

    @GET("deputados/{id}/despesas")
    suspend fun getDeputyCosts(
        @Path("id") id: Int,
        @Query("pagina") page: Int?,
        @Query("itens") items: Int = 15
    ): Response<DeputyCostsResponse>
}