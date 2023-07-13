package com.yumtaufikhidayat.xnews.data.remote

import com.yumtaufikhidayat.xnews.model.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(UrlConstant.ENDPOINT_TOP_HEADLINES)
    suspend fun getTopHeadlinesNews(
        @Query(UrlConstant.QUERY_COUNTRY) country: String,
        @Query(UrlConstant.QUERY_PAGE_SIZE) pageSize: Int,
        @Query(UrlConstant.QUERY_PAGE) page: Int,
        @Query(UrlConstant.QUERY_API_KEY) apiKey: String
    ): Response<NewsResponse>
}