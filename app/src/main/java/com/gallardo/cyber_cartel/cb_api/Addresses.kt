package com.gallardo.cyber_cartel.cb_api

import com.google.gson.annotations.SerializedName

data class Addresses(
    @SerializedName("id")
    val id: String? = null,

    @SerializedName("region")
    val region: String,

    @SerializedName("city")
    val city: String,

    @SerializedName("address")
    val address: String,

    @SerializedName("postal_code")
    val postal_code: String,

//    @SerializedName("user_id")
//    val user_id: String
)
