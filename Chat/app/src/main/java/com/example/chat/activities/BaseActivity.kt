package com.example.chat.activities

import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity(){
    fun loginActivity() {
        val intent = LoginActivity.intent(applicationContext)
        startActivity(intent)
        finish()
    }

     fun registerActivity() {
        val intent = RegisterActivity.intent(applicationContext)
        startActivity(intent)

    }

     fun mainActivity() {
        val intent = MainActivity.intent(applicationContext)
        startActivity(intent)
        finishAffinity()
    }
}