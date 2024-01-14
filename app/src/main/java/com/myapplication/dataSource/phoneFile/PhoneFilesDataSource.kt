package com.myapplication.dataSource.phoneFile

import android.content.Context
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.util.Log
import androidx.documentfile.provider.DocumentFile
import com.myapplication.model.Musics
import com.myapplication.model.MusicalPlaylists

object PhoneFilesDataSource {
    private var playlists = mutableListOf<MusicalPlaylists>()
    private var playlistID: Int = playlists.size

    fun getPlaylistByID(id: Int): MusicalPlaylists{
        return playlists[id - 1]
    }
    fun getAllPlaylists(): List<MusicalPlaylists>{
        return playlists
    }

    fun createPlaylist(context: Context, directory: Uri): Boolean{
        var isCreate = false
        var documentFile: DocumentFile
        var musicsID = 0
        var tracks = mutableListOf<Musics>()

        try {
            if(directory != null){
                documentFile = DocumentFile.fromTreeUri(context, directory)!!
                if(documentFile != null && documentFile.exists()){
                    if(documentFile.name !== null){
                        var directoryName = documentFile.name
                        for (file in documentFile.listFiles()){
                            if(!file.name!!.startsWith(".")){
                                try {
                                    if(file.isDirectory){
                                        Log.e("error", "IS DIRECTORY $directoryName DIRECTORY NAME $directoryName")
                                        createPlaylist(context, file.uri)
                                    }
                                    else if (file.isFile){
                                        var music = createMusic(context, file.uri, ++musicsID)
                                        tracks.add(music)
                                        Log.e("error", "MUSIC $music")
                                    }
                                }
                                catch (e: Exception){
                                    Log.e("error", e.toString())
                                }
                            }
                        }
                        if(!alreadyExists(directoryName!!)){
                            var playlist = MusicalPlaylists(playlistID, "" , directoryName!!, tracks)
                            Log.e("error", "CREATE PLAYLIST PLAY  $playlist")
                            if (playlist != null) {
                                Log.e("error", "TRACKS Number ${tracks.size}")
                                playlist.tracks = tracks
                                Log.e("error", "PLAYLIST NOT NULL  ${playlist.tracks.size}")
                                playlists.add(playlist)
                                isCreate = true
                            }
                        }
                    }
                    else
                        Log.e("error", "LE DOSSIER N A PAS DE NOM ${documentFile.name}")
                }
            }
        }
        catch (e: Exception){
            Log.e("Create playlist error", e.toString())
        }
        Log.e("error", "PLAY IS CREATE $isCreate")
        return isCreate
    }

    private fun alreadyExists(fileName: String): Boolean{
        var isExists = false
        for (playlist in playlists){
            if (playlist.name == fileName)
                isExists = true
        }
        return isExists
    }
    private fun createMusic(context: Context, fileUri: Uri, musicID: Int): Musics{
        var retriever = MediaMetadataRetriever()
        lateinit var musics: Musics
        var cover: String
        var title: String
        var artists: String
        var album: String
        var releaseDate: String
        var duration: String

        try {
            retriever.setDataSource(context, fileUri)
            cover = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_HAS_IMAGE).toString()
            title = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE).toString()
            artists = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST).toString()
            album = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM).toString()
            releaseDate = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DATE).toString()
            duration = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION).toString()
            musics = Musics(musicID, cover, title, artists, album, releaseDate, duration)
        }
        catch (e: Exception){
            Log.e("Create music error", e.toString())
        }
        finally {
        	retriever.release()
        }
        return musics
    }
}