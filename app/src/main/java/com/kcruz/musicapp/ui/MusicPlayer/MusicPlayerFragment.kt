package com.kcruz.musicapp.ui.MusicPlayer

import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

import com.kcruz.musicapp.R
import com.kcruz.musicapp.model.Song
import com.kcruz.musicapp.model.SongViewModel
import kotlinx.android.synthetic.main.fragment_music_player.*

class MusicPlayerFragment : Fragment() {

    lateinit var mediaPlayer: MediaPlayer
    lateinit var presenter: MusicPlayerPresenter
    private lateinit var songsViewModel: SongViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        songsViewModel = run {
            ViewModelProviders.of(this).get(SongViewModel::class.java)
        }
        presenter = MusicPlayerPresenter(this, songsViewModel)
        presenter.start()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_music_player, container, false)
    }

    fun initializeMusicPlayer(trackPath: Uri) {
        mediaPlayer = MediaPlayer.create(context, trackPath)
        mediaPlayer.setOnCompletionListener { skipNext() }

        btn_play_pause?.setOnClickListener{ checkPlayerStatus() }
        btn_skip_next?.setOnClickListener { skipNext() }
        btn_skip_previous?.setOnClickListener { skipPrevious() }
    }

    fun setSongInfo(song: Song) {
        song_name?.text = song.name
        artist?.text = song.artist
    }

    fun checkPlayerStatus() {
        if (mediaPlayer.isPlaying)
            pause()
        else
            play()
    }

    fun play() {
        btn_play_pause?.setImageResource(R.drawable.ic_pause_50dp)
        mediaPlayer.start()
    }

    fun pause() {
        btn_play_pause?.setImageResource(R.drawable.ic_play_arrow_50dp)
        mediaPlayer.pause()
    }

    fun skipNext() {
        mediaPlayer.stop()
        mediaPlayer = MediaPlayer.create(context, presenter.getNextTrack())
        mediaPlayer.setOnCompletionListener { skipNext() }
        btn_play_pause?.setImageResource(R.drawable.ic_pause_50dp)
        mediaPlayer.start()

    }

    fun skipPrevious() {
        mediaPlayer.stop()
        mediaPlayer = MediaPlayer.create(context, presenter.getPreviousTrack())
        mediaPlayer.setOnCompletionListener { skipNext() }
        btn_play_pause?.setImageResource(R.drawable.ic_pause_50dp)
        mediaPlayer.start()
    }

    override fun onPause() {
        super.onPause()
        mediaPlayer.stop()
    }

    override fun onStop() {
        super.onStop()
        mediaPlayer.stop()
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            MusicPlayerFragment().apply {
            }
    }
}
