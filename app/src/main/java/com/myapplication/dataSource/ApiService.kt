package com.myapplication.dataSource


import com.myapplication.model.accessToken.AccessToken
import com.myapplication.model.accessToken.ApiAccess
import retrofit2.Response
import retrofit2.http.*


interface ApiService {
    @FormUrlEncoded
    @POST("api/token")
    suspend fun getAccessToken(
        @Field("grant_type") grantType: String,
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String
    ): Response<AccessToken>

}