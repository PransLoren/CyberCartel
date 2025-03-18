package com.gallardo.cyber_cartel.cb_api

import android.content.Context
import android.content.SharedPreferences

object SharedPreferencesManager {
    private const val PREF_NAME = "AccessTokenPref"
    private const val KEY_ACCESS_TOKEN = "accessToken"
    private const val KEY_USER_ID = "userId"
    private const val KEY_USERNAME = "username"
    private const val KEY_EMAIL = "email"

    // Function to save access token
    fun saveAccessToken(context: Context, accessToken: String) {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(KEY_ACCESS_TOKEN, accessToken)
        editor.apply()
    }

    // Function to get access token
    fun getAccessToken(context: Context): String? {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getString(KEY_ACCESS_TOKEN, null)
    }

    // Function to save user details
    fun saveUserDetails(context: Context, userId: String, username: String, email: String) {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(KEY_USER_ID, userId)
        editor.putString(KEY_USERNAME, username)
        editor.putString(KEY_EMAIL, email)
        editor.apply()
    }

    // Function to get user ID
    fun getUserId(context: Context): String? {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getString(KEY_USER_ID, null)
    }

    // Function to get username
    fun getUsername(context: Context): String? {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getString(KEY_USERNAME, null)
    }

    // Function to get email
    fun getEmail(context: Context): String? {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getString(KEY_EMAIL, null)
    }
}