package com.myapplication.model

import com.google.gson.annotations.SerializedName

data class PictureSpotify(
	@SerializedName("height")
	val height: Int,
	@SerializedName("url")
	val url: String,
	@SerializedName("width")
	val width: Int
)