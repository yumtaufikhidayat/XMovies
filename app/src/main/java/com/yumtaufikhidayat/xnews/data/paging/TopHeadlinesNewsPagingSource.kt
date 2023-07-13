package com.yumtaufikhidayat.xnews.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.yumtaufikhidayat.xnews.BuildConfig
import com.yumtaufikhidayat.xnews.data.remote.ApiService
import com.yumtaufikhidayat.xnews.model.Article
import com.yumtaufikhidayat.xnews.utils.Constants
import retrofit2.HttpException

class TopHeadlinesNewsPagingSource(
    private val apiService: ApiService
) : PagingSource<Int, Article>() {
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let {anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return try {
            val currentPage = params.key ?: Constants.STARTING_PAGE_INDEX
            val response = apiService.getTopHeadlinesNews(
                country = Constants.COUNTRY,
                pageSize = Constants.LIMIT_PER_INDEX,
                page = currentPage,
                apiKey = BuildConfig.API_KEY
            )

            val data = response.body()?.articles
            val nextKey = if (data.isNullOrEmpty()) {
                null
            } else {
                currentPage + (params.loadSize / Constants.LIMIT_PER_INDEX)
            }

            LoadResult.Page(
                data = data ?: emptyList(),
                prevKey = if (currentPage == Constants.STARTING_PAGE_INDEX) null else currentPage - 1,
                nextKey = nextKey?.plus(1)
            )
        } catch (httpEx: HttpException) {
            LoadResult.Error(httpEx)
        } catch (ex: Exception) {
            LoadResult.Error(ex)
        }
    }
}