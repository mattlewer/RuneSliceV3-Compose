package com.runeslice.models

import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@Parcelize
data class Clue(
    val name: String,
    val rank: Int,
    val num: Int,
) : Parcelable
