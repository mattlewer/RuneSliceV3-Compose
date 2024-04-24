package com.runeslice.services.api

import android.content.Context
import android.widget.Toast
import com.runeslice.constants.BASE_URL
import com.runeslice.models.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import com.runeslice.util.isOnline
import com.runeslice.services.factories.UserFactory

interface UserRequestCallback {
    fun onDataReceived(userData: User)
    fun onFailure(message: String)
}

class UserSearch(var context: Context) {

    fun getData(username: String, callback: UserRequestCallback) {
        if (isOnline(context) == true) {
            val retrofitBuilder = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Api::class.java)
            handleReponse(retrofitBuilder.getUser(username), username, callback)
        }else{
            Toast.makeText(context, "Error: Network connection failed", Toast.LENGTH_LONG).show()
        }
    }

    fun handleReponse(retrofitData: Call<String>, username: String, callback: UserRequestCallback) {
        var userFactory = UserFactory(context)
        retrofitData.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                try {
                    val responseBody = response.body()!!
                    val userData: User = userFactory.createUser(username, responseBody)
                    callback.onDataReceived(userData)
                } catch (e: NullPointerException) {
                    callback.onFailure("Error: Check username and try again")
                }
            }
            override fun onFailure(call: Call<String>, t: Throwable) {
                callback.onFailure("Error: Please try again")
            }
        })
    }
}