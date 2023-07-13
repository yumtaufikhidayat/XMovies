package com.yumtaufikhidayat.xnews.data.source

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.yumtaufikhidayat.xnews.data.paging.TopHeadlinesNewsPagingSource
import com.yumtaufikhidayat.xnews.data.remote.ApiService
import com.yumtaufikhidayat.xnews.utils.Constants
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val apiService: ApiService
) {
    fun getTopHeadlinesNews() = Pager(
        PagingConfig(
            pageSize = Constants.LIMIT_PER_INDEX,
            enablePlaceholders = false
        ), pagingSourceFactory = {
            TopHeadlinesNewsPagingSource(apiService)
        }).flow
}