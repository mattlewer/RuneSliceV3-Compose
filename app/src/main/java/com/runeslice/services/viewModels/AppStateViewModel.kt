package com.runeslice.services.viewModels;

import android.content.Context
import android.widget.Toast
import androidx.core.content.edit
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.runeslice.dataClass.GlobalData
import com.runeslice.dataClass.SearchState
import com.runeslice.models.User
import com.runeslice.services.api.UserRequestCallback
import com.runeslice.services.api.UserSearch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AppStateViewModel(context: Context) : ViewModel(),
    UserRequestCallback {
    private val _appState = MutableStateFlow(GlobalData())
    val appState: StateFlow<GlobalData> = _appState.asStateFlow()
    val context = context
    val userSearch = UserSearch(context);

    fun isSavedUser(user: User): Boolean {
        return appState.value.savedUsers.any { it.name == user.name }
    }

    fun searchUser(username: String) {
        _appState.update { currentState ->
            currentState.copy(
                searchState = SearchState.LOADING
            )
        }
        userSearch.getData(username, this)
    }

    override fun onDataReceived(userData: User) {
        _appState.update { currentState ->
            currentState.copy(
                searchState = SearchState.SEARCHED
            )
        }
        setSearchedUser(userData)
    }

    override fun onFailure(message: String) {
        _appState.update { currentState ->
            currentState.copy(
                searchState = SearchState.IDLE
            )
        }
        Toast.makeText(context, "Error: Please try again", Toast.LENGTH_LONG).show()
    }

    fun setSearchedUser(userData: User?) {
        _appState.update { currentState ->
            currentState.copy(
                searchedUser = userData
            )
        }
    }

    fun toggleSave(userData: User) {
        if (isSavedUser(userData)) removeUser(userData) else saveUser(userData)
    }

    fun saveUser(userData: User) {
        val newList = appState.value.savedUsers;
        newList.add(userData)
        _appState.update { currentState ->
            currentState.copy(
                savedUsers = newList
            )
        }
        val gson = Gson()
        val mutableDataJson = gson.toJson(newList)
        context.getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE).edit {
            putString("savedUsers", mutableDataJson)
        }
    }

    fun removeUser(userData: User) {
        val newList = appState.value.savedUsers
        newList.removeAll { it.name == userData.name }
        _appState.update { currentState ->
            currentState.copy(
                savedUsers = newList
            )
        }
        val gson = Gson()
        val mutableDataJson = gson.toJson(newList)
        context.getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE).edit {
            putString("savedUsers", mutableDataJson)
        }
    }

    fun updateUser(userData: User) {
        val updatedUsers = appState.value.savedUsers
        updatedUsers.forEach { user ->
            if (user.name == userData.name) {
                user.skills = userData.skills
                user.boss = userData.boss
                user.clues = userData.clues
            }
        }
        _appState.update { currentState ->
            currentState.copy(
                savedUsers = updatedUsers
            )
        }
        val gson = Gson()
        val mutableDataJson = gson.toJson(updatedUsers)
        context.getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE).edit {
            putString("savedUsers", mutableDataJson)
        }
    }

    fun loadSavedUsers() {
        val gson = Gson()
        val sharedPrefs = context.getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val userJson = sharedPrefs?.getString("savedUsers", null)
        if (userJson != null) {
            _appState.update { currentState ->
                currentState.copy(
                    savedUsers = gson.fromJson(
                        userJson,
                        object : TypeToken<MutableList<User>>() {}.type
                    )
                )
            }
        }
    }
}