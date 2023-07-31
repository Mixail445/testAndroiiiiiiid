package com.example.testandroiiiiiiid.VIewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.testandroiiiiiiid.Retrofit.RetrofitService
import com.example.testandroiiiiiiid.paging.CriticPagingSource

import com.example.testandroiiiiiiid.paging.ReviewsPagingSource

class ReviewsViewModel(val apiRepository: RetrofitService) : ViewModel() {

    val reviews =
        Pager(config = PagingConfig(pageSize = 10, prefetchDistance = 2), pagingSourceFactory = {
            ReviewsPagingSource()
        }).flow.cachedIn(viewModelScope)
    val critic =
        Pager(config = PagingConfig(10,2), pagingSourceFactory = {
            CriticPagingSource()
        }).flow.cachedIn(viewModelScope)
}
