package com.example.chat.events

sealed class RegisterViewEvent{

    object Success :RegisterViewEvent()
    data class Error(val message:String ):RegisterViewEvent()
}
