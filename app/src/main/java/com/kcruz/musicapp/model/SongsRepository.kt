package com.kcruz.musicapp.model

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class SongsRepository(application: Application) {
    private val songDao: ISongDao? = RoomSongDatabase.getInstance(application)?.roomsongDao()

    fun insertSong(song: Song) {
        if (songDao != null) InsertAsyncTask(songDao).execute(song)
    }

    fun getSongs(): LiveData<List<Song>> {
        return songDao?.getAllSongs() ?: MutableLiveData<List<Song>>()
    }

    private class InsertAsyncTask(private val songDao: ISongDao) :
        AsyncTask<Song, Void, Void>() {
        override fun doInBackground(vararg songs: Song?): Void? {
            for (song in songs) {
                if (song != null) songDao.insertSong(song)
            }
            return null
        }
    }
}