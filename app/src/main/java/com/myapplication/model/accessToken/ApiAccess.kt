package com.myapplication.model.accessToken

import com.google.gson.annotations.SerializedName
import com.myapplication.BuildConfig

data class ApiAccess(
    @SerializedName("grant_type")
    val grantType: String = BuildConfig.GRANT_TYPE,
    @SerializedName("client_id")
    val clientId: String = BuildConfig.CLIENT_ID,
    @SerializedName("client_secret")
    val clientSecret: String = BuildConfig.CLIENT_SECRET)
