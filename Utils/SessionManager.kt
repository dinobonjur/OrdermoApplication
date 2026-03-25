package com.example.ordermoapplication.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.ordermoapplication.models.User
import com.google.gson.Gson

class SessionManager(context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = sharedPreferences.edit()
    private val gson = Gson()

    fun saveUser(user: User) {
        val userJson = gson.toJson(user)
        editor.putString(KEY_USER, userJson)
        editor.putBoolean(KEY_IS_LOGGED_IN, true)
        editor.apply()
    }

    fun getUser(): User? {
        val userJson = sharedPreferences.getString(KEY_USER, null)
        return if (userJson != null) {
            gson.fromJson(userJson, User::class.java)
        } else null
    }

    fun isLoggedIn(): Boolean = sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false)

    fun logout() {
        editor.clear()
        editor.apply()
    }

    fun updateUser(user: User) {
        saveUser(user)
    }

    companion object {
        private const val PREF_NAME = "OrderMorphPref"
        private const val KEY_USER = "user"
        private const val KEY_IS_LOGGED_IN = "isLoggedIn"
    }
}