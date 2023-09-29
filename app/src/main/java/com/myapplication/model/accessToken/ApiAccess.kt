package com.myapplication.model.accessToken

import com.google.gson.annotations.SerializedName

data class ApiAccess(
    @SerializedName("grant_type")
    val grantType: String = "client_credentials",
    @SerializedName("client_id")
    val clientId: String = "97e4ffd132de48b994e860461e571c6a",
    @SerializedName("client_secret")
    val clientSecret: String = "633a5550b7374b63939eeef8dbc0a091")
