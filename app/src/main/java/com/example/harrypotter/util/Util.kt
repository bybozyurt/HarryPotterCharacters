package com.example.harrypotter.util

import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.harrypotter.R
import com.squareup.picasso.Picasso
//ImageView Extension
fun ImageView.downloadFromApi(url : String?, progressDrawable: CircularProgressDrawable){

       val options = RequestOptions()
           .placeholder(progressDrawable)
           .error(R.drawable.ic_launcher_background)

       Glide.with(context)
           .setDefaultRequestOptions(options)
           .load(url)
           .into(this)

}

fun placeHolderProgressBar(context : Context) : CircularProgressDrawable {
       return CircularProgressDrawable(context).apply {
           //dp olmalı
           strokeWidth = 8f
           centerRadius = 40f
           start()
       }
}
