package com.example.chat.events

sealed class LoginViewEvent {
    object Success : LoginViewEvent()
    data class Error(val message: String) : LoginViewEvent()
}