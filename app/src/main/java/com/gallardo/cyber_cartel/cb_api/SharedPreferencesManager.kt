package com.gallardo.cyber_cartel.cb_api

import android.content.Context
import android.content.SharedPreferences
import com.gallardo.cyber_cartel.api.Adapters_Api.MyAdapter_Bought

object SharedPreferencesManager {
    private const val PREF_NAME = "AccessTokenPref"
    private const val KEY_ACCESS_TOKEN = "accessToken"

    // Function to save access token
    fun saveAccessToken(context: Context, accessToken: String) {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(KEY_ACCESS_TOKEN, accessToken)
        editor.apply()
    }
    fun getAccessToken(context: Context): String? {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getString(KEY_ACCESS_TOKEN, null)
    }

//    fun saveAuthToken(context: Context, accessToken: String) {
//        val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
//        val editor = sharedPreferences.edit()
//        editor.putString(KEY_ACCESS_TOKEN, accessToken)
//        editor.apply()
//    }

//    fun getAuthToken(context: MyAdapter_Bought): String? {
//        val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
//        return sharedPreferences.getString(KEY_ACCESS_TOKEN, null)
//    }

}