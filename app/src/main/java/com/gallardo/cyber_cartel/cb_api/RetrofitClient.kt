package com.gallardo.cyber_cartel.cb_api

    import okhttp3.OkHttpClient
    import okhttp3.logging.HttpLoggingInterceptor
    import retrofit2.Retrofit
    import retrofit2.converter.gson.GsonConverterFactory



    interface RetrofitClient {
        companion object{
            private const val BASE_URL = "https://floppy-bobcats-admire.loca.lt/"

            fun getService(): ApiService{
                val loggingInterceptor = HttpLoggingInterceptor().apply{
                    level = HttpLoggingInterceptor.Level.BODY
                }
                val client = OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    .build()

                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()

                return retrofit.create(ApiService::class.java)

            }
        }
    }
