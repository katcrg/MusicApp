package com.kcruz.musicapp.ui.Profile

import android.content.Context
import com.kcruz.musicapp.R

class ProfilePresenter(var view: ProfileFragment) {

    fun start() {
        getUserInfo()

    }

    fun getUserInfo() {
        val sharedPref = view.activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        val username = sharedPref.getString(view.context?.getString(R.string.username_key), "")
        val fullName = sharedPref.getString(view.context?.getString(R.string.fullname_key), "")
        val biography = sharedPref.getString(view.context?.getString(R.string.user_biography_key), "")

        view.setUserInfo(username!!, fullName!!, biography!!)
    }
}