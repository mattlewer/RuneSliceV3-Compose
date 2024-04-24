package com.runeslice.models

import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@Parcelize
data class User(
    var name: String,
    var skills: MutableList<Skill>,
    var boss: MutableList<Boss>,
    var clues: MutableList<Clue>
) : Parcelable
