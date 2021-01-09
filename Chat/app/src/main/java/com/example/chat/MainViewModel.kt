package com.example.chat

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class MainViewModel : ViewModel(), FirebaseAuth.AuthStateListener {

    val chatListLiveData = MutableLiveData<List<Chat>>()
    val isLoginLiveData = MutableLiveData<Boolean>()

    private val chatDateReference : DatabaseReference = Firebase.database.reference.child("Chat")
    private val auth : FirebaseAuth = Firebase.auth
    init {

        chatDateReference.addValueEventListener(
            object : ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {
                    val chatList = mutableListOf<Chat>()
                    snapshot.children.forEach {
                        val message = it.child("message").getValue<String>() ?: return@forEach
                        val sender = it.child("sender").getValue<String>() ?: return@forEach
                        val chatId = it.key ?: return@forEach

                        val chat = Chat(
                            chatId = chatId,
                            message = message,
                            userName = sender
                        )
                        chatList.add(chat)
                    }

                    chatListLiveData.postValue(chatList)

                }

                override fun onCancelled(error: DatabaseError) {
                    error.toException().printStackTrace()
                }


            })
        auth.addAuthStateListener(this)
    }
     fun messageSent(message: String) {
        chatDateReference.push().apply {
            child("message").setValue(message)
            child("sender").setValue(auth.currentUser?.email ?: "dummy user")
        }
    }

    override fun onAuthStateChanged(auth : FirebaseAuth) {
        val isLogin = auth.currentUser !=null
        isLoginLiveData.postValue(isLogin)
    }

    fun logout() {
        auth.signOut()
    }
}