package com.kcruz.musicapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = Song.TABLE_NAME)
data class Song(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_song")
    val idSong: Int,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "artist")
    val artist: String,

    @ColumnInfo(name = "trackPath")
    val trackPath: String
) {
    companion object {
        const val TABLE_NAME = "songs"
    }
}