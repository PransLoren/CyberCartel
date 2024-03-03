package com.gallardo.cyber_cartel.api.models

data class CartItem(
    val id: Int,
    val name: String,
//    val photo: String,
    val price: String,
    val category: String,
    val details: String,
    val quantity: String,
    val user_id: Int
//    val created_at: String,
//    val updated_at: String,
)