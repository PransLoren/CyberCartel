package com.gallardo.cyber_cartel.api

data class User(
    val name: String,
    val email: String,
    val password: String,
    val confirm_password: String,
)
