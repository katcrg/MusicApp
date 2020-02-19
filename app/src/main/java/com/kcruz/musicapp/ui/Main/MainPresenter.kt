package com.kcruz.musicapp.ui.Main

import android.content.Context
import androidx.lifecycle.Observer
import com.kcruz.musicapp.R
import com.kcruz.musicapp.model.Song
import com.kcruz.musicapp.model.SongViewModel
import java.io.InputStream
import java.lang.Exception

class MainPresenter(var view: MainActivity, var songsViewModel: SongViewModel) {
    fun start() {
        addSongsToStorage()
        generateUserInfo()
    }

    fun addSongsToStorage() {
        try {
            var inputStream: InputStream = view.resources.openRawResource(R.raw.moonlight_sonata_by_beethoven)
            var outputStream = view.applicationContext.openFileOutput("moonlight_sonata_by_beethoven", Context.MODE_PRIVATE)
            outputStream.write(inputStream.readBytes())

            inputStream = view.resources.openRawResource(R.raw.waltz_of_the_flowers_by_tchaikovsky)
            outputStream = view.applicationContext.openFileOutput("waltz_of_the_flowers_by_tchaikovsky", Context.MODE_PRIVATE)
            outputStream.write(inputStream.readBytes())

            inputStream = view.resources.openRawResource(R.raw.dance_of_the_sugar_plum_fairies_by_tchaikovsky)
            outputStream = view.applicationContext.openFileOutput("dance_of_the_sugar_plum_fairies_by_tchaikovsky", Context.MODE_PRIVATE)
            outputStream.write(inputStream.readBytes())

            inputStream = view.resources.openRawResource(R.raw.fur_elise_by_beethoven)
            outputStream = view.applicationContext.openFileOutput("fur_elise_by_beethoven", Context.MODE_PRIVATE)
            outputStream.write(inputStream.readBytes())

            outputStream.close()


        } catch (e: Exception) {
            e.printStackTrace();
        }

        val observer = Observer<List<Song>> { songs ->
            if (songs.size == 0) {
                songsViewModel.saveSong(Song(0, "Moonlight Sonata", "Beethoven", "/data/data/com.kcruz.musicapp/files/moonlight_sonata_by_beethoven"))
                songsViewModel.saveSong(Song(0, "Waltz of the Flowers", "Tchaikovsky", "/data/data/com.kcruz.musicapp/files/waltz_of_the_flowers_by_tchaikovsky"))
                songsViewModel.saveSong(Song(0, "Dance of the Sugar Plum Fairies", "Tchaikovsky", "/data/data/com.kcruz.musicapp/files/dance_of_the_sugar_plum_fairies_by_tchaikovsky"))
                songsViewModel.saveSong(Song(0, "Fur Elise", "Beethoven", "/data/data/com.kcruz.musicapp/files/fur_elise_by_beethoven"))
            }
        }
        songsViewModel.songs.observe(view, observer)
    }

    fun generateUserInfo() {
        val sharedPref = view?.getPreferences(Context.MODE_PRIVATE) ?: return
        with (sharedPref.edit()) {
            putString(view.applicationContext.getString(R.string.username_key), "@kcruz")
            putString(view.applicationContext.getString(R.string.fullname_key), "Katia Cruz")
            putString(view.applicationContext.getString(R.string.user_biography_key), "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum cursus dui felis, sed ullamcorper massa varius id. Phasellus consectetur mauris quis pharetra molestie. Nunc ultrices sagittis est, a sodales elit scelerisque ac. In iaculis urna eu tortor convallis iaculis.")
            commit()
        }
    }
}