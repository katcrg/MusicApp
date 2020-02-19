package com.kcruz.musicapp.ui.Gallery.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kcruz.musicapp.R

class PhotoAdapter(private val context: Context, private val photoList: List<Int?>): RecyclerView.Adapter<PhotoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_photo, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return photoList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentImage = photoList[position]
        val imageView = holder.imageView
        Glide.with(context)
            .load(currentImage)
            .into(imageView)
    }


    class ViewHolder: RecyclerView.ViewHolder, View.OnClickListener {

        var imageView: ImageView

        constructor(itemView: View) : super(itemView) {
            imageView = itemView.findViewById(R.id.iv_photo)
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            //clickListener
        }
    }

}