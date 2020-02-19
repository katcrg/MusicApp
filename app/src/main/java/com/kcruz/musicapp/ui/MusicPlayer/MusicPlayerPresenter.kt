package com.kcruz.musicapp.ui.MusicPlayer

import android.net.Uri
import androidx.lifecycle.Observer
import com.kcruz.musicapp.model.Song
import com.kcruz.musicapp.model.SongViewModel

class MusicPlayerPresenter(var view: MusicPlayerFragment, var songsViewModel: SongViewModel) {

    private lateinit var playlistSongs: List<Song>
    private var currentSongIndex: Int = 0

    fun start() {
        getPlaylist()
    }

    fun getNextTrack(): Uri {
        if(currentSongIndex < playlistSongs.size - 1) currentSongIndex++
        else currentSongIndex = 0
        view.setSongInfo(playlistSongs[currentSongIndex])
        return Uri.parse(playlistSongs[currentSongIndex].trackPath)
    }

    fun getPreviousTrack(): Uri {
        if(currentSongIndex > 0) currentSongIndex--
        else currentSongIndex = playlistSongs.size - 1
        view.setSongInfo(playlistSongs[currentSongIndex])
        return Uri.parse(playlistSongs[currentSongIndex].trackPath)
    }

    fun getPlaylist() {
        val observer = Observer<List<Song>> { songs ->
            if (songs != null) {
                playlistSongs = songs
                view.initializeMusicPlayer(Uri.parse(playlistSongs[currentSongIndex].trackPath))
                view.setSongInfo(playlistSongs[currentSongIndex])
            }
        }
        songsViewModel.songs.observe(view, observer)
    }


}