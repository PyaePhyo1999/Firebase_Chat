package com.example.chat

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterActivity : BaseActivity(){

    companion object{
        fun intent(context : Context) : Intent {
            return Intent(context,RegisterActivity::class.java)
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
    private lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        auth = Firebase.auth
        btnRegister.setOnClickListener {
            register()
        }
    }

    private fun register() {
        val email = etEmail.text.toString()
        val password = etPassword.text.toString()
        val cfPassword = etPasswordAgain.text.toString()

        if (password != cfPassword)
        {
            Toast.makeText(this,"Password does not match",Toast.LENGTH_LONG).show()
        }
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){
                if (it.isSuccessful){
                 mainActivity()
                }
                else
                {
                    Log.w(".Register", "createUserWithEmail:failure", it.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()

                }
            }
    }



}