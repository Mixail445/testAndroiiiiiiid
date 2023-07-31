package com.example.testandroiiiiiiid.VIewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testandroiiiiiiid.Retrofit.RetrofitService


class ReviewsViewModelFactory(  private val api: RetrofitService):ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ReviewsViewModel(api) as T
    }
}
