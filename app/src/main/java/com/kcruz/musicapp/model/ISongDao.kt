package com.kcruz.musicapp.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ISongDao {
    @Query("SELECT * FROM " + Song.TABLE_NAME )
    fun getAllSongs(): LiveData<List<Song>>

    @Insert
    fun insertSong(song: Song)

}