package com.gallardo.cyber_cartel.api.models

data class UserModel(
    val name: String,
    val email: String,
    val password: String,
    val confirm_password: String,
)
