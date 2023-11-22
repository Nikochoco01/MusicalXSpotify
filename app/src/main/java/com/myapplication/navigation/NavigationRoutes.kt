package com.myapplication.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import com.myapplication.R
import com.myapplication.ui.MusicalIcons


object MusicalRoute {
    const val READER = "Reader"
    const val PLAYLISTS = "Playlists"
    const val REMOVE_PLAYLIST = "Playlists"
    const val MUSICS = "Musics"
    const val REMOVE_MUSICS = "Musics"
    const val SETTINGS = "Settings"
    const val PLAYLISTS_PARAMETER = "/{userID}"
    const val MUSICS_PARAMETER = "/{playlistID}"
}
object MusicalRouteName {
    const val READER_NAME = "Reader"
    const val PLAYLISTS_NAME = "Playlists"
    const val REMOVE_PLAYLIST_NAME = "Remove"
    const val MUSICS_NAME = "Musics"
    const val REMOVE_MUSICS_NAME = "Remove"
    const val SETTINGS_NAME = "Settings"
}

sealed class  MusicalPlaylistDetails(
    val route: String,
    var idPlaylist: Int? = null
){
    object Music: MusicalPlaylistDetails(
        route = MusicalRoute.MUSICS,
        idPlaylist = null
    )
    object MusicRemove: MusicalPlaylistDetails(
        route = MusicalRoute.REMOVE_MUSICS,
        idPlaylist = null
    )
}

sealed class MusicalBarRoute(
    val routeName: String,
    val route: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
){
    object Reader: MusicalBarRoute(
        routeName = MusicalRouteName.READER_NAME,
        route = MusicalRoute.READER,
        selectedIcon = MusicalIcons.iconReader,
        unselectedIcon = MusicalIcons.iconReader
    )
    object Playlist: MusicalBarRoute(
        routeName = MusicalRouteName.PLAYLISTS_NAME,
        route = MusicalRoute.PLAYLISTS,
        selectedIcon = MusicalIcons.iconPlaylist,
        unselectedIcon = MusicalIcons.iconPlaylist
    )
    object PlaylistRemove: MusicalBarRoute(
        routeName = MusicalRouteName.REMOVE_PLAYLIST_NAME,
        route = MusicalRoute.REMOVE_PLAYLIST,
        selectedIcon = MusicalIcons.iconPlaylist,
        unselectedIcon = MusicalIcons.iconPlaylist
    )
    object Settings: MusicalBarRoute(
        routeName = MusicalRouteName.SETTINGS_NAME,
        route = MusicalRoute.SETTINGS,
        selectedIcon = MusicalIcons.iconSettings,
        unselectedIcon = MusicalIcons.iconSettings
    )
}
