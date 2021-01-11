package com.example.chat.models

import androidx.lifecycle.ViewModel
import com.example.chat.events.RegisterViewEvent
import com.example.chat.events.SingleLiveEvent
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterViewModel : ViewModel(){
    private val auth : FirebaseAuth = Firebase.auth

    val registerViewEventLiveData = SingleLiveEvent<RegisterViewEvent>()

    fun register( email: String, password :String,cfPassword : String ){
        if (password != cfPassword)
        {
           registerViewEventLiveData.postValue(RegisterViewEvent.Error("Password do not match"))
            return
        }

       auth.createUserWithEmailAndPassword(email,password)
           .addOnCompleteListener{
             if (it.isSuccessful){
                 registerViewEventLiveData.postValue(RegisterViewEvent.Success)
             }
               else
             {
                 registerViewEventLiveData.postValue(RegisterViewEvent.Error("Authentication failed."))
             }
       }

    }
}