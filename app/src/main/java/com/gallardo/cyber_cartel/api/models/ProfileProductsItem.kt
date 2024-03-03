package com.gallardo.cyber_cartel.api.models

data class ProfileProductsItem(
    val category: String,
    val details: String,
    val id: Int,
    val name: String,
    val photo: String,
    val price: String,
    val quantity: Int,
    val user_id: Int
)