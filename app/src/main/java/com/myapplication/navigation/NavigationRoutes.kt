package com.myapplication.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import com.myapplication.R
import com.myapplication.ui.MusicalIcons


object MusicalRoute {
    const val READER = "Reader"
    const val PLAYLISTS = "Playlists"
    const val REMOVE_PLAYLIST = "Remove"
    const val MUSICS = "Musics/{playlistID}"
    const val REMOVE_MUSICS = "Musics/{playlistID}"
    const val SETTINGS = "Settings"
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
    val route: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val iconTextId: Int
){
    object Reader: MusicalBarRoute(
        route = MusicalRoute.READER,
        selectedIcon = MusicalIcons.iconReader,
        unselectedIcon = MusicalIcons.iconReader,
        iconTextId = R.string.tab_reader
    )
    object Playlist: MusicalBarRoute(
        route = MusicalRoute.PLAYLISTS,
        selectedIcon = MusicalIcons.iconPlaylist,
        unselectedIcon = MusicalIcons.iconPlaylist,
        iconTextId = R.string.tab_playlists
    )
    object PlaylistRemove: MusicalBarRoute(
        route = MusicalRoute.REMOVE_PLAYLIST,
        selectedIcon = MusicalIcons.iconPlaylist,
        unselectedIcon = MusicalIcons.iconPlaylist,
        iconTextId = R.string.tab_playlists
    )
    object Settings: MusicalBarRoute(
        route = MusicalRoute.SETTINGS,
        selectedIcon = MusicalIcons.iconSettings,
        unselectedIcon = MusicalIcons.iconSettings,
        iconTextId = R.string.tab_settings
    )
}
