package com.myapplication.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import com.myapplication.R
import com.myapplication.ui.MusicalIcons


object MusicalRoute {
    const val READER = "reader"
    const val USER_PLAYLISTS = "user/{userID}/playlists"
    const val PLAYLIST_REMOVE = "user/{userID}/playlists/remove"
    const val PLAYLIST_MUSICS = "playlists/{playlistID}/musics"
    const val MUSICS_REMOVE = "playlists/{playlistID}/musics/remove"
    const val SETTINGS = "Settings"
}
object MusicalRouteName {
    const val READER_NAME = "Reader"
    const val PLAYLISTS_NAME = "Playlists"
    const val REMOVE_PLAYLIST_NAME = "Remove Playlists"
    const val MUSICS_NAME = "Musics"
    const val REMOVE_MUSICS_NAME = "Remove Musics"
    const val SETTINGS_NAME = "Settings"
}

sealed class MusicalInternalAppRoute (
    val routeName: String,
    val route: String,
    var idPlaylist: Int? = null
){
    object LoadPlaylist: MusicalInternalAppRoute(
        routeName = MusicalRouteName.MUSICS_NAME,
        route = MusicalRoute.PLAYLIST_MUSICS,
        idPlaylist = null
    )
    object RemoveMusic: MusicalInternalAppRoute(
        routeName = MusicalRouteName.REMOVE_MUSICS_NAME,
        route = MusicalRoute.MUSICS_REMOVE,
        idPlaylist = null
    )
    object RemovePlaylist: MusicalInternalAppRoute(
        routeName = MusicalRouteName.REMOVE_PLAYLIST_NAME,
        route = MusicalRoute.PLAYLIST_REMOVE,
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
        selectedIcon = MusicalIcons.iconReaderSelected,
        unselectedIcon = MusicalIcons.iconReader
    )
    object Playlist: MusicalBarRoute(
        routeName = MusicalRouteName.PLAYLISTS_NAME,
        route = MusicalRoute.USER_PLAYLISTS,
        selectedIcon = MusicalIcons.iconPlaylistSelected,
        unselectedIcon = MusicalIcons.iconPlaylist
    )
    object Settings: MusicalBarRoute(
        routeName = MusicalRouteName.SETTINGS_NAME,
        route = MusicalRoute.SETTINGS,
        selectedIcon = MusicalIcons.iconSettingsSelected,
        unselectedIcon = MusicalIcons.iconSettings
    )
}
