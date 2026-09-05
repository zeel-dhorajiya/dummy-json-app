package com.example.cleanarchtemplate.data.local

import android.content.Context
import android.content.SharedPreferences

class TokenManager(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

    fun saveToken(token: String) {
        prefs.edit().putString("ACCESS_TOKEN", token).apply()
    }

    fun getToken(): String? {
        return prefs.getString("ACCESS_TOKEN", null)
    }

    fun clearToken() {
        prefs.edit().remove("ACCESS_TOKEN").apply()
    }
}
