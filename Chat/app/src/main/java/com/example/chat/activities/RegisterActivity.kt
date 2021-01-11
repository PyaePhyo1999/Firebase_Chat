package com.example.chat.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import com.example.chat.R
import com.example.chat.events.RegisterViewEvent
import com.example.chat.models.RegisterViewModel
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterActivity : BaseActivity(){

    companion object{
        fun intent(context : Context) : Intent {
            return Intent(context, RegisterActivity::class.java)
        }
    }

    private val etEmail by lazy {
        findViewById<TextInputEditText>(R.id.etEmail)
    }
    private val etPassword by lazy {
        findViewById<TextInputEditText>(R.id.etPassword)
    }
    private val etPasswordAgain by lazy {
        findViewById<TextInputEditText>(R.id.etPasswordAgain)
    }


    private val btnRegister by lazy {
        findViewById<MaterialButton>(R.id.btnRegister)
    }

    private val vmRegister : RegisterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        vmRegister.registerViewEventLiveData.observe(this,{event->
            when(event){
                RegisterViewEvent.Success -> {
                    mainActivity()
                }
                is RegisterViewEvent.Error -> {
                    Toast.makeText(this,event.message,Toast.LENGTH_LONG).show()
                }
            }

        })


        btnRegister.setOnClickListener {
            register()

        }
    }

    private fun register() {
        val email = etEmail.text.toString()
        val password = etPassword.text.toString()
        val cfPassword = etPasswordAgain.text.toString()
        vmRegister.register(email,password,cfPassword)
    }



}