package com.zee.suhaatecs.mediation.customadview

import android.content.Context
import androidx.core.content.ContextCompat
import com.zee.suhaatecs.R

import com.zee.suhaatecs.mediation.hDp


data class TrueStroke(
    private val zContext: Context,
    var zColor: Int = ContextCompat.getColor(
        zContext,
        R.color.colorPrimaryDark
    ),
    var zWidth: Int = 1,
) {
    init {
        zWidth = hDp(zContext, zWidth).toInt()
    }
}

