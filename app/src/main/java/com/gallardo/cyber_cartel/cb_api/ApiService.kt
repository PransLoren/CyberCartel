package com.gallardo.cyber_cartel.cb_api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST



interface ApiService {
    @GET("user")
    fun getUser(): Call<List<User>>
    @POST("api/auth/register")
    fun register(@Body user: User):Call<User>
    @POST("api/auth/login")
    fun loginUser(@Body loginUser: LoginUser): Call<LoginUser>



}