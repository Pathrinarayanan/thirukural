package com.example.kuralify

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    val baseUrl  = "https://pathri14-kuralify.hf.space"
    val client = OkHttpClient.Builder()
        .addInterceptor {chain->
            val req = chain.request().newBuilder()
                .addHeader("User-Agent", "Kuralify")
                .build()
            chain.proceed(req)
        }
        .build()

    fun getInstance() : Retrofit{
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}