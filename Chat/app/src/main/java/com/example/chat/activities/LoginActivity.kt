package com.example.chat.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.chat.models.LoginViewModel
import com.example.chat.R
import com.example.chat.events.LoginViewEvent
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class LoginActivity : BaseActivity(){

    companion object{
        fun intent(context : Context) : Intent{
            return Intent(context, LoginActivity::class.java)
        }
    }

    private val etEmail by lazy {
        findViewById<TextInputEditText>(R.id.etEmail)
    }
    private val etPassword by lazy {
        findViewById<TextInputEditText>(R.id.etPassword)
    }
    private val btnLogin by lazy {
        findViewById<MaterialButton>(R.id.btnLogin)
    }
    private val btnRegister by lazy {
        findViewById<MaterialButton>(R.id.btnRegister)
    }

    private val vmLogin : LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        vmLogin.loginViewEventLiveData.observe(this,{ event ->
          when(event){
              LoginViewEvent.Success -> {
                  mainActivity()
                  Toast.makeText(this,"Success Login",Toast.LENGTH_LONG).show()
              }
              is LoginViewEvent.Error -> {
                  Toast.makeText(this,event.message,Toast.LENGTH_LONG).show()
              }
          }
        })

        btnRegister.setOnClickListener {
            registerActivity()
        }
        btnLogin.setOnClickListener {
           login()
        }
    }

    private fun login() {
         val email= etEmail.text.toString()
         val password = etPassword.text.toString()
         vmLogin.login(email,password)

    }

}