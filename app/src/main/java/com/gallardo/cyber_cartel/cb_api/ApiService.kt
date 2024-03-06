package com.gallardo.cyber_cartel.cb_api

import com.gallardo.cyber_cartel.api.models.AddressItem
import com.gallardo.cyber_cartel.api.models.CartItem
import com.gallardo.cyber_cartel.api.models.ProfileProductsItem
import com.gallardo.cyber_cartel.api.models.ProductsItem
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface ApiService {
    @GET("user")
    fun getUser(): Call<List<User>>
    @POST("api/auth/register")
    fun register(@Body user: User):Call<User>
    @POST("api/auth/login")
    fun loginUser(@Body loginUser: LoginUser): Call<LoginResponse> // PINALITAN RESPONSE INTO LOGIN RESPONSE FROM LOGIN USER



    // ===== FOR PRODUCTS ===== //
    // SHOWS ALL PRODUCTS FOR SALE
    @GET("api/products/products-show")
    fun getProducts():Call<List<ProductsItem>> // <<<<< ---- ITO YUN PRI

    // SHOWS CPUS
    @GET("api/products/show-cpu")
    fun getCPUS(): Call<List<ProductsItem>>

    // SHOWS GPUS
    @GET("api/products/show-gpu")
    fun getGPUS(): Call<List<ProductsItem>>

    // SHOWS MOTHERBOARDS
    @GET("api/products/show-motherboard")
    fun getMotherboards(): Call<List<ProductsItem>>

    // SHOWS STORAGE
    @GET("api/products/show-storage")
    fun getStorage(): Call<List<ProductsItem>>

    // SHOWS PSUS
    @GET("api/products/show-psu")
    fun getPSUS(): Call<List<ProductsItem>>

    // SHOWS CASE
    @GET("api/products/show-case")
    fun getCase(): Call<List<ProductsItem>>

    // SHOWS RAM
    @GET("api/products/show-ram")
    fun getRAM(): Call<List<ProductsItem>>

    // SHOWS ALL PRODUCTS IN USER PROFILE
    @GET("api/profile/products-show-all")
    fun getAllProducts(): Call<List<ProfileProductsItem>>
    // ===== END FOR PRODUCTS ===== //


    // ===== FOR PROFILE ====== //
    // SHOWS ALL CANCELLED PRODUCTS
    @GET("api/profile/cancelled-index")
    fun getCancelled(): Call<List<ProfileProductsItem>>

    // SHOWS ALL BOUGHT PRODUCTS
    @GET("api/profile/bought-index")
    fun getBought(): Call<List<ProfileProductsItem>>

    // SHOWS ALL REFUNDED PRODUCTS
    @GET("api/profile/refunded-index")
    fun getRefunded(): Call<List<ProfileProductsItem>>

    @POST("api/profile/products-refund/{id}")
    fun refundProduct(@Path("id") id: Int): Call<Void>

    @POST("api/profile/products-cancel/{id}")
    fun cancelProduct(@Path("id") id: Int): Call<Void>
    // ===== END FOR PROFILE ===== //





    // ===== FOR CART =====//
    // SHOWS CART ITEMS
    @GET("api/cart/cart-show")
    fun getCart():Call<List<CartItem>>

    // SHOWS CART ITEMS IN CHECKOUT
    @GET("api/cart/cart-show")
    fun getCheckout(): Call<List<CartItem>>

    // ADD TO CART
    @POST("api/cart/cart-add/{id}")
    fun addToCart(@Path("id") id: Int): Call<Void>

    @POST("api/cart/cart-checkout")
    fun checkout(): Call<Void>

    // REMOVES FROM CART
    @DELETE("api/cart/cart-remove/{id}")
    fun removeItem(@Path("id") id: Int): Call<ResponseBody>

    // INCREASES QUANTITY SOMETHING FROM CART
    @POST("api/cart/increase/{id}")
    fun increaseItem(@Path("id")id:Int): Call<Void>

    // DECREASES QUANTITY SOMETHING FROM CART
    @POST("api/cart/decrease/{id}")
    fun decreaseItem(@Path("id")id:Int): Call<Void>
    // ===== END FOR CART===== //





    // ===== FOR ADDRESS ===== //
    //DISPLAY ADDRESS
    @GET("api/profile/address-index")
    fun getAddress(): Call<List<AddressItem>>

    // DELETE AN ADDRESS
    @DELETE("api/profile/address-delete/{id}")
    fun deleteAddress(@Path("id")id:Int): Call<ResponseBody>






}