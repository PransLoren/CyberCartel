package com.gallardo.cyber_cartel.cb_api

import com.google.gson.annotations.SerializedName
data class User(
    @SerializedName("id")
    val id: String? = null,

    @SerializedName("name")
    val name: String,

    @SerializedName("email")
    val email: String,

    @SerializedName("password")
    val password: String,

    @SerializedName("confirm_password")
    val confirm_password: String
)
