package com.example.deputy_chamber_app.domain

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.deputy_chamber_app.domain.entity.DeputyItem
import com.example.deputy_chamber_app.domain.repository.DeputyRepository

class DeputyPagingSource(
    val deputyRepository: DeputyRepository,
): PagingSource<Int, DeputyItem>() {
    override fun getRefreshKey(state: PagingState<Int, DeputyItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DeputyItem> {
        val nextPageNumber = params.key ?: 1
        val result = deputyRepository.getDeputies(nextPageNumber)
        return LoadResult.Page(
            data = result?.itemList ?: emptyList(),
            prevKey = result?.previousPage,
            nextKey = result?.nextPage
        )
    }
}