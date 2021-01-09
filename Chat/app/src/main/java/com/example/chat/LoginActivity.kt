package com.example.chat

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class LoginActivity : BaseActivity(){

    companion object{
        fun intent(context : Context) : Intent{
            return Intent(context,LoginActivity::class.java)
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        btnRegister.setOnClickListener {
            registerActivity()
        }
    }


}