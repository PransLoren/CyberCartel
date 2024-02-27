package com.gallardo.cyber_cartel.api

import com.gallardo.cyber_cartel.api.models.DefaultResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface Api {

    @FormUrlEncoded
    @POST("/auth/register")
    fun register(
        @Field("name") name:String,
        @Field("email") email:String,
        @Field("password") password:String,
        @Field("confirm_password") confirm_password:String,

        ):Call<DefaultResponse>
}