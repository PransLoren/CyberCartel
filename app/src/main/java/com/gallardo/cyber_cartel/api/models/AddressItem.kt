package com.gallardo.cyber_cartel.api.models

data class AddressItem(
    val id: Int,
    val region: String,
    val address: String,
    val city: String,
    val postal_code: String,
    val user_id: Int
)