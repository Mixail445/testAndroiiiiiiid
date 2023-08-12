package com.example.testandroiiiiiiid.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.testandoid.ui.main.data.Critic

import com.example.testandroiiiiiiid.Retrofit.RetrofitService
import com.example.testandroiiiiiiid.dataCritic.crit

import retrofit2.Response

class detalPaging(val st:String) :PagingSource<Int,com.example.testandoid.ui.main.data.Result>() {
    override fun getRefreshKey(state: PagingState<Int, com.example.testandoid.ui.main.data.Result>): Int? {
        return null
    }

    override suspend fun load(params: PagingSource.LoadParams<Int>): PagingSource.LoadResult<Int, com.example.testandoid.ui.main.data.Result> {
        return try {
            val currentPage= params.key?:1
            val response: Response<Critic> = RetrofitService.getInstance().getRc(
                st,
                "GW5a0tJfWOcfQ7k3dpQizIsrmpZ33Bmm",
                currentPage,
            )
            val data= response.body()!!.results
            val responsedata = mutableListOf<com.example.testandoid.ui.main.data.Result>()
            data.let { responsedata.addAll(it) }

            PagingSource.LoadResult.Page(
                data = responsedata,
                prevKey = if(currentPage==1)null else -1,
                nextKey = currentPage.plus(1)
            )
        }catch (e:java.lang.Exception){
            PagingSource.LoadResult.Error(e)
        }
    }
}