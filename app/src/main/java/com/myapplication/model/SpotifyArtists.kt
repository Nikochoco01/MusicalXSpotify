package com.myapplication.model

import com.google.gson.annotations.SerializedName

data class SpotifyArtists(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String
)
