package com.example.testandroiiiiiiid.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.testandoid.ui.main.data.Critic
import com.example.testandoid.ui.main.data.Result
import com.example.testandroiiiiiiid.Retrofit.RetrofitService
import retrofit2.Response

class ReviewsPagingSource()
    :PagingSource<Int,com.example.testandoid.ui.main.data.Result>() {
    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
      return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        return try {
            val currentPage= params.key?:1
            val response: Response<Critic> = RetrofitService.getInstance().getreview("GW5a0tJfWOcfQ7k3dpQizIsrmpZ33Bmm\n" +
                    "\n",currentPage)
            val data= response.body()?.results
            val responsedata = mutableListOf<com.example.testandoid.ui.main.data.Result>()
            responsedata.addAll(data!!)

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