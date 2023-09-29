package com.myapplication.dataSource

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object SpotifyClient {
    private const val ACCESS_TOKEN_URL: String = "https://accounts.spotify.com/"
    private const val BASE_URL: String = "https://api.spotify.com/v1/"

    private val gson : Gson by lazy {
        GsonBuilder().setLenient().create()
    }

    private val httpClient : OkHttpClient by lazy {
        OkHttpClient.Builder().build()
    }

    private val retrofitAccessToken : Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(ACCESS_TOKEN_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    private val retrofitCallApi: Retrofit by lazy{
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    val apiServiceAccessToken :  ApiService by lazy{
        retrofitAccessToken.create(ApiService::class.java)
    }

    val apiServiceCallAPi: ApiService by lazy{
        retrofitCallApi.create(ApiService::class.java)
    }
}