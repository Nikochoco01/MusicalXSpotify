package com.myapplication.model.users

import com.google.gson.annotations.SerializedName

data class SpotifyUsers(
    @SerializedName("id")
    val id: Int,
    @SerializedName("images")
    val images: ArrayList<String>,
    @SerializedName("display_name")
    val name: String)
