package com.yumtaufikhidayat.xnews.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.yumtaufikhidayat.xnews.data.repository.XNewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: XNewsRepository
) : ViewModel() {
    fun getTopHeadlinesNews() = repository.getTopHeadlinesNews().asLiveData()
}