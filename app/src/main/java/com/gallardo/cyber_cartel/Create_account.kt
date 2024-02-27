package com.gallardo.cyber_cartel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.gallardo.cyber_cartel.api.RetrofitClient
import com.gallardo.cyber_cartel.api.models.DefaultResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Create_account : AppCompatActivity() {

    private lateinit var alreadyhaveanaccount: TextView
    private lateinit var login: TextView
    private lateinit var confirm : Button

    lateinit var et_username_createAccountPage: EditText
    lateinit var et_email_createAccountPage: EditText
    lateinit var et_Confirmpassword_createAccountPage: EditText
    lateinit var et_password_createAccountPage: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        alreadyhaveanaccount = findViewById(R.id.tv_alreadyHave_anAccount)
        login = findViewById(R.id.tv_already_have_an_account_logIn)
        confirm= findViewById((R.id.bt_createAccount))

        alreadyhaveanaccount.setOnClickListener (){
            val intent = Intent(this, Login_Page::class.java)
            startActivity(intent)
        }

        login.setOnClickListener (){
            val intent = Intent(this, Login_Page::class.java)
            startActivity(intent)
        }

        confirm.setOnClickListener(){

            val name = et_username_createAccountPage.toString().trim()
            val email = et_email_createAccountPage.toString().trim()
            val password = et_password_createAccountPage.toString().trim()
            val confirm_password = et_Confirmpassword_createAccountPage.toString().trim()

            if(name.isEmpty()){
                et_username_createAccountPage.error = "Name required"
                et_username_createAccountPage.requestFocus()
                return@setOnClickListener
            }

            if(email.isEmpty()){
                et_email_createAccountPage.error = "Email required"
                et_email_createAccountPage.requestFocus()
                return@setOnClickListener
            }

            if(password.isEmpty()){
                et_password_createAccountPage.error = "Password required"
                et_password_createAccountPage.requestFocus()
                return@setOnClickListener
            }

            if(confirm_password.isEmpty()){
                et_Confirmpassword_createAccountPage.error = "Confirm Password required"
                et_Confirmpassword_createAccountPage.requestFocus()
                return@setOnClickListener
            }

            RetrofitClient.instance.register(name, email, password, confirm_password)
                .enqueue(object:Callback<DefaultResponse>{
                    override fun onResponse(
                        call: Call<DefaultResponse>,
                        response: Response<DefaultResponse>
                    ) {
                        Toast.makeText(applicationContext, response.body()?.message, Toast.LENGTH_LONG).show()
                    }

                    override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
                        Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                    }

                })


            val intent = Intent(this, Login_Page::class.java)
            startActivity(intent)
        }
    }
}