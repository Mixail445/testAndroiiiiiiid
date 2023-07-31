package com.example.testandroiiiiiiid.Utils

import android.view.View

fun View.visible(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}
