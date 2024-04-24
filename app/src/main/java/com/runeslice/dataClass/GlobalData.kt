package com.runeslice.dataClass

import com.runeslice.models.User

data class GlobalData(
    val savedUsers: MutableList<User> = mutableListOf(),
    val searchedUser: User? = null,
    var searchState: SearchState = SearchState.IDLE,
)
