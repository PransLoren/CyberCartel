package com.gallardo.cyber_cartel.cb_api

data class LoginResponse(
    val token : String,
    val user: User,
)
