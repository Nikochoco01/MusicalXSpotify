package com.myapplication.repository.bluetooth

object BluetoothCodeTransmissions {
	val GET_ALL_PLAYLIST: String = "/playlists"
	val GET_PLAYLIST_BY_ID: String = "/playlists/{id}"
	val PLAY_MUSIC: String = "/play"
	val PAUSE_MUSIC: String = "/pause"
	val PREVIOUS_MUSIC: String = "/previous/{id}"
	val NEXT_MUSIC: String = "/next/{id}"
}