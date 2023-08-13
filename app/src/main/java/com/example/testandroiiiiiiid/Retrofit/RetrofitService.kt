package com.example.testandroiiiiiiid.Retrofit

import com.example.testandoid.ui.main.data.Critic
import com.example.testandroiiiiiiid.dataCritic.crit
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
private const val BASE_URL = "https://api.nytimes.com/svc/movies/v2/"
interface RetrofitService {
    @GET("reviews/search.json?")
    suspend fun getreview(
        @Query("api-key") api_key: String,
        @Query("page") position: Int

    ): Response<Critic>
//Search
    @GET("reviews/search.json?")
    suspend fun getSearch(
    @Query("query") query: String?,
    @Query("api-key") api_key: String,
    @Query("page") position: Int

    ): Response<Critic>

    @GET("critics/all.json?")
    suspend fun getcritic(
        @Query("api-key") api_key: String,
        @Query("page") position: Int

    ): Response<crit>
    @GET("reviews/search.json?")
    suspend fun getRc(
        @Query("reviewer") reviewer: String?,
        @Query("api-key") api_key: String,
        @Query("page") position: Int,
        ):Response<Critic>
    @GET("reviews/search.json?")
    suspend fun getdata(
        @Query("opening-date") opening_date: String?,
        @Query("api-key") api_key: String,
        @Query("page") position: Int,
    ):Response<Critic>



    companion object {
        var retrofitService: RetrofitService? = null
        fun getInstance() : RetrofitService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }
        private val loggingInterceptor = run {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.apply {
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            }
        }

        private val baseInterceptor: Interceptor = Interceptor.invoke { chain ->
            val newUrl = chain
                .request()
                .url
                .newBuilder()
                .build()

            val request = chain
                .request()
                .newBuilder()
                .url(newUrl)
                .build()

            return@invoke chain.proceed(request)
        }

        private val client: OkHttpClient = OkHttpClient
            .Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(baseInterceptor)
            .build()
    }

    }
