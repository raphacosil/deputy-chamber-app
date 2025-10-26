package com.example.deputy_chamber_app.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.deputy_chamber_app.data.dto.CostItemDto
import com.example.deputy_chamber_app.data.service.DeputyService

class DeputyCostPagingSource(
    private val deputyService: DeputyService,
    private val deputyId: Int
): PagingSource<Int, CostItemDto>() {
    override fun getRefreshKey(state: PagingState<Int, CostItemDto>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CostItemDto> {
        val nextPageNumber = params.key ?: 1
        val result = deputyService.getDeputyCosts(nextPageNumber, deputyId)
        return LoadResult.Page(
            data = result.body()?.data ?: emptyList(),
            prevKey = params.key?.minus(1),
            nextKey = nextPageNumber.plus(1)
        )
    }
}