package com.example.harrypotter.util.extesions

import android.view.View

/**
 * Created by Ahmet Bozyurt on 3.11.2021
 */

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.INVISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}