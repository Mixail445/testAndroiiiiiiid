package com.example.testandroiiiiiiid.VIewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.testandoid.ui.main.data.Result
import com.example.testandroiiiiiiid.Retrofit.RetrofitService
import com.example.testandroiiiiiiid.paging.CriticPagingSource

import com.example.testandroiiiiiiid.paging.ReviewsPagingSource
import com.example.testandroiiiiiiid.paging.detalPaging
import com.example.testandroiiiiiiid.paging.searchPaging
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ReviewsViewModel(val apiRepository: RetrofitService) : ViewModel() {

    val reviews =
        Pager(config = PagingConfig(pageSize = 10, prefetchDistance = 2), pagingSourceFactory = {
            ReviewsPagingSource()
        }).flow.cachedIn(viewModelScope)
    val critic =
        Pager(config = PagingConfig(10,2), pagingSourceFactory = {
            CriticPagingSource()
        }).flow.cachedIn(viewModelScope)
    fun af(st:String): Flow<PagingData<Result>> {
        val search = Pager(config = PagingConfig(10,2),
            pagingSourceFactory = {
                searchPaging(st) }).flow.cachedIn(viewModelScope)
        return search
  }


fun af1(search:Flow<PagingData<com.example.testandroiiiiiiid.dataCritic.Result>>):Flow<PagingData<com.example.testandroiiiiiiid.dataCritic.Result>>{
    val search = Pager(config = PagingConfig(10,2),
        pagingSourceFactory = {
            CriticPagingSource() }).flow.cachedIn(viewModelScope)
    return search
}
    val _itemAmount = MutableLiveData<Int>()
    val itemAmount: LiveData<Int> get() = _itemAmount
    fun setItemAmount(amount: Int) {
        _itemAmount.value = amount
    }


    val detailsMovie = MutableLiveData<com.example.testandroiiiiiiid.dataCritic.Result>()
    fun loadDetailsMovie(id: Int) = viewModelScope.launch {
        val response =  RetrofitService.retrofitService!!.getcritic("GW5a0tJfWOcfQ7k3dpQizIsrmpZ33Bmm",id)
        detailsMovie.postValue(response.body()?.results?.get(id))
    }


 fun load(st: String):Flow<PagingData<com.example.testandoid.ui.main.data.Result>>{
        val critic1 =
            Pager(config = PagingConfig(10,2), pagingSourceFactory = {
                detalPaging(st)
            }).flow.cachedIn(viewModelScope)
return critic1
    }
    }


