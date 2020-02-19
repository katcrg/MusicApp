package com.kcruz.musicapp.ui.Main

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.kcruz.musicapp.R

import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.kcruz.musicapp.model.SongViewModel
import com.kcruz.musicapp.ui.Gallery.GalleryFragment
import com.kcruz.musicapp.ui.MusicPlayer.MusicPlayerFragment
import com.kcruz.musicapp.ui.Profile.ProfileFragment


class MainActivity : AppCompatActivity() {

    private lateinit var songsViewModel: SongViewModel
    private lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bottomNavigationBar = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationBar.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.navigation_gallery -> {
                    showFragment(GalleryFragment.newInstance())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigation_music -> {
                    showFragment(MusicPlayerFragment.newInstance())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigation_profile -> {
                    showFragment(ProfileFragment.newInstance())
                    return@setOnNavigationItemSelectedListener true
                }
                else -> return@setOnNavigationItemSelectedListener false
            }
        }

        songsViewModel = run {
            ViewModelProviders.of(this).get(SongViewModel::class.java)
        }
        presenter = MainPresenter(this, songsViewModel)
        presenter.start()

        checkPermissions()
    }


    fun checkPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET)
            != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.INTERNET)) {
            } else {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.INTERNET),
                    REQUEST_PERMISSION_CODE)
            }
        } else {
            showFragment(GalleryFragment.newInstance())
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            REQUEST_PERMISSION_CODE -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    showFragment(GalleryFragment.newInstance())
                }
                return
            }
            else -> {}
        }
    }

    fun showFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    companion object {
        const val REQUEST_PERMISSION_CODE = 123
    }
}
