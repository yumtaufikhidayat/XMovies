package com.yumtaufikhidayat.xnews.data.repository

import com.yumtaufikhidayat.xnews.data.source.RemoteDataSource
import javax.inject.Inject

class XNewsRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) {
    fun getTopHeadlinesNews() = remoteDataSource.getTopHeadlinesNews()
}