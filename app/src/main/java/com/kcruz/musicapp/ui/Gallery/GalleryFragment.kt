package com.kcruz.musicapp.ui.Gallery

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kcruz.musicapp.ui.Gallery.Adapter.PhotoAdapter
import com.kcruz.musicapp.ui.Gallery.Adapter.PhotoProvider

import com.kcruz.musicapp.R

class GalleryFragment : Fragment() {

    private val photoProvider = PhotoProvider()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         return inflater.inflate(R.layout.fragment_gallery, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeViews()
    }

    fun initializeViews() {
        val photoAdapter = PhotoAdapter(context!!, photoProvider.photos)
        var recyclerView = view?.findViewById<RecyclerView>(R.id.rv_gallery)
        recyclerView?.layoutManager = LinearLayoutManager(context)
        recyclerView?.adapter = photoAdapter
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            GalleryFragment().apply {
            }
    }
}
