package com.sinannuhoglu.fooddeliveryapp.util

object UserSession {
    var email: String? = null

    val isLoggedIn: Boolean
        get() = email != null

    fun clearSession() {
        email = null
    }
}
