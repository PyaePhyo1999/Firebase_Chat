package com.example.chat

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class MainActivity : BaseActivity() {

    companion object {
        fun intent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

    private val rvChat by lazy {
        findViewById<RecyclerView>(R.id.rvMessage)
    }
    private val btnSent by lazy {
        findViewById<Button>(R.id.btnSent)
    }
    private val etSentMessage by lazy {
        findViewById<EditText>(R.id.etSentMessage)
    }

    private val vmMain: MainViewModel by viewModels<MainViewModel>()

    private val chatAdapter = ChatAdapter()

    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        vmMain.chatListLiveData.observe(this){
            chatAdapter.submitList(it)
        }
        vmMain.isLoginLiveData.observe(this){
            if(!it){
                loginActivity()
            }
        }

        auth = Firebase.auth
        if (auth.currentUser == null) {
            loginActivity()
        }

        rvChat.apply {
            adapter = chatAdapter
            layoutManager = LinearLayoutManager(applicationContext, RecyclerView.VERTICAL, false)
        }

        btnSent.setOnClickListener {
            val message = etSentMessage.text.toString()
            vmMain.messageSent(message)
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.action_logout) {
            vmMain.logout()
        }
        return true
    }
}