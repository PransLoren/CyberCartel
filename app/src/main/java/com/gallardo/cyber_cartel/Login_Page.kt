package com.gallardo.cyber_cartel


import android.content.Intent
import android.os.Bundle
import android.util.JsonToken
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.gallardo.cyber_cartel.cb_api.ApiService
import com.gallardo.cyber_cartel.cb_api.LoginResponse
import com.gallardo.cyber_cartel.cb_api.LoginUser
import com.gallardo.cyber_cartel.cb_api.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Login_Page : AppCompatActivity() {

    private lateinit var createAccount: TextView
    private lateinit var button : Button
    private lateinit var email : EditText
    private lateinit var password : EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)

        createAccount = findViewById(R.id.tv_createAcc)
        button = findViewById(R.id.bt_logIn)
        email = findViewById(R.id.et_email_logInPage)
        password = findViewById(R.id.et_passwordLogIn)


        createAccount.setOnClickListener (){
            val intent = Intent(this, Create_account::class.java)
            startActivity(intent)
            finish()
        }

        button.setOnClickListener(){
            loginUser()
        }


    }
//    private fun loginUser(){
//        val retrofit = Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//
//        val service = retrofit.create(ApiService::class.java)
//        val request = LoginUser(email.text.toString(), password.text.toString())
//        val loginCall = service.loginUser(request)
//
//        loginCall.enqueue(object : Callback<LoginResponse?> {
//            override fun onResponse(
//                call: Call<LoginResponse?>, response: Response<LoginResponse?>) {
//
//                if (response.isSuccessful){
//                    val loginResponse = response.body()
//                    if (loginResponse != null) {
//                        Log.d("Login", response.body().toString())
//                        val authToken = "Bearer ${response.body()?.token}"
//                    }
//                }
//            }
//
//            override fun onFailure(call: Call<LoginResponse?>, t: Throwable) {
//                //
//            }
//        }))
//        )
//    }

    // PINALITAN KO RESPONSES FROM LOGUNUSER TO LOGIN RESPONSE
    private fun loginUser() {
        val email = findViewById<EditText>(R.id.et_email_logInPage).text.toString().trim()
        val password = findViewById<EditText>(R.id.et_passwordLogIn).text.toString().trim()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(
                this@Login_Page,
                "Please fill in all fields",
                Toast.LENGTH_SHORT
            ).show()
            return
        }
        Log.d("LoginRequest", "Email: $email, Password: $password")

        val call = RetrofitClient.getService().loginUser(LoginUser(email = email, password = password))

        call.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {

                    // TOKEN
                    val authToken = "Bearer ${response.body()?.token}"

                    // Login successful, navigate to main activity
                    loginNav(authToken)
                    finish()
                    Toast.makeText(
                        this@Login_Page,
                        "Login successful!",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    // Login failed, show error message
                    val errorBody = response.errorBody()?.string()
                    val errorMessage = if (errorBody.isNullOrEmpty()) {
                        "Login failed. Please check your credentials."
                    } else {
                        errorBody
                    }

                    Log.e("LoginError", "Error body: $errorMessage")
                    Toast.makeText(
                        this@Login_Page,
                        errorMessage,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.e("LoginError", "Login request failed", t)
                Toast.makeText(
                    this@Login_Page,
                    "Login request failed. Please try again later.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
    private fun loginNav(authToken: String){
        intent.putExtra("authToken", authToken)
        startActivity(Intent(this@Login_Page, Rv_Home_Page::class.java))
    }

}

