package com.myapplication.model

import com.google.gson.annotations.SerializedName

data class SpotifyMusics(
	@SerializedName("id")
	val id: String,
	@SerializedName("name")
	val title: String,
	@SerializedName("artists")
	val artist: List<SpotifyArtists>,
	@SerializedName("album")
	val album: SpotifyAlbum,
	@SerializedName("duration_ms")
	val duration: String
)

data class SpotifyAlbum(
	@SerializedName("images")
	val albumCover: List<PictureSpotify>,
	@SerializedName("name")
	val albumName: String,
	@SerializedName("release_date")
	val releaseDate: String
)

data class Track(
	@SerializedName("track")
	val spotifyMusic: SpotifyMusics
)

data class SpotifyResultTracks(
	@SerializedName("items")
	val tracks: List<Track>
)