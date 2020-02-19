package com.kcruz.musicapp.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Song::class],
    version = 1,
    exportSchema = false
)
abstract class RoomSongDatabase: RoomDatabase()  {
    abstract fun roomsongDao(): ISongDao

    companion object {
        private const val DATABASE_NAME = "songs_database"
        @Volatile
        private var INSTANCE: RoomSongDatabase? = null

        fun getInstance(context: Context): RoomSongDatabase? {
            INSTANCE ?: synchronized(this) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    RoomSongDatabase::class.java,
                    DATABASE_NAME
                ).build()
            }
            return INSTANCE
        }
    }
}