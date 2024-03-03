package com.gallardo.cyber_cartel.cb_api

import com.gallardo.cyber_cartel.api.models.CartItem
import com.gallardo.cyber_cartel.api.models.ProfileProductsItem
import com.gallardo.cyber_cartel.api.models.ProductsItem
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



    // ===== FOR PRODUCTS ===== //
    // SHOWS ALL PRODUCTS FOR SALE
    @GET("api/products/products-show")
    fun getProducts():Call<List<ProductsItem>>

    // SHOWS ALL PRODUCTS IN USER PROFILE
    @GET("api/profile/products-show-all")
    fun getAllProducts(): Call<List<ProfileProductsItem>>

    // SHOWS ALL CANCELLED PRODUCTS
    @GET("api/profile/cancelled-index")
    fun getCancelled(): Call<List<ProfileProductsItem>>

    // SHOWS ALL BOUGHT PRODUCTS
    @GET("api/profile/bought-index")
    fun getBought(): Call<List<ProfileProductsItem>>

    // SHOWS ALL REFUNDED PRODUCTS
    @GET("api/profile/refunded-index")
    fun getRefunded(): Call<List<ProfileProductsItem>>
    // ===== END FOR PRODUCTS ===== //






    // ===== FOR CART =====//
    // SHOWS CART ITEMS
    @GET("api/cart/cart-show")
    fun getCart():Call<List<CartItem>>

    // SHOWS CART ITEMS IN CHECKOUT
    @GET("api/cart/cart-show")
    fun getCheckout(): Call<List<CartItem>>
    // ===== END FOR CART===== //






}