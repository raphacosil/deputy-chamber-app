package com.example.deputy_chamber_app.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.deputy_chamber_app.data.dto.DeputyItemDto
import com.example.deputy_chamber_app.data.service.DeputyService

class DeputyPagingSource(
    private val deputyService: DeputyService,
): PagingSource<Int, DeputyItemDto>() {
    override fun getRefreshKey(state: PagingState<Int, DeputyItemDto>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DeputyItemDto> {
        val nextPageNumber = params.key ?: 1
        val result = deputyService.getDeputies(nextPageNumber)
        return LoadResult.Page(
            data = result.body()?.data ?: emptyList(),
            prevKey = params.key?.minus(1),
            nextKey = nextPageNumber.plus(1)
        )
    }
}