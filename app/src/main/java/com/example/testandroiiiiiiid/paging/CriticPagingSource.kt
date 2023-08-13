package com.example.testandroiiiiiiid.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.testandoid.ui.main.data.Critic
import com.example.testandoid.ui.main.data.Result
import com.example.testandroiiiiiiid.Retrofit.RetrofitService
import com.example.testandroiiiiiiid.dataCritic.crit
import retrofit2.Response

class CriticPagingSource() : PagingSource<Int, com.example.testandroiiiiiiid.dataCritic.Result>() {
    override fun getRefreshKey(state: PagingState<Int, com.example.testandroiiiiiiid.dataCritic.Result>): Int? {
       return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, com.example.testandroiiiiiiid.dataCritic.Result> {
        return try {
            val currentPage= params.key?:1
            val response: Response<crit> = RetrofitService.getInstance().getcritic("GW5a0tJfWOcfQ7k3dpQizIsrmpZ33Bmm\n" +
                    "\n",currentPage)
            val data= response.body()!!.results
            val responsedata = mutableListOf<com.example.testandroiiiiiiid.dataCritic.Result>()
            data.let { responsedata.addAll(it) }

            LoadResult.Page(
                data = responsedata,
                prevKey = if(currentPage==1)null else -1,
                nextKey = currentPage.plus(1)
            )
        }catch (e:java.lang.Exception){
            LoadResult.Error(e)
        }
    }
}
