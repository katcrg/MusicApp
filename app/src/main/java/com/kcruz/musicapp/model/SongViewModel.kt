package com.kcruz.musicapp.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class SongViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = SongsRepository(application)
    val songs = repository.getSongs()

    fun saveSong(song: Song) {
        repository.insertSong(song)
    }
}