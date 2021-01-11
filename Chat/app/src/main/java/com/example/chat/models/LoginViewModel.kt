package com.example.chat.models

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.chat.events.LoginViewEvent
import com.example.chat.events.SingleLiveEvent
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginViewModel : ViewModel(){

    private val auth : FirebaseAuth = Firebase.auth
    val loginViewEventLiveData = SingleLiveEvent<LoginViewEvent>()

    fun login(email : String ,password : String){
    auth.signInWithEmailAndPassword(email,password)
    .addOnCompleteListener {
        if (it.isSuccessful){
            loginViewEventLiveData.postValue(LoginViewEvent.Success)
        }
        else{
            Log.w(".Login", "createUserWithEmail:failure", it.exception)
            loginViewEventLiveData.postValue(LoginViewEvent.Error("The password is invalid or the user does not have a password."))

        }
    }
   }
}