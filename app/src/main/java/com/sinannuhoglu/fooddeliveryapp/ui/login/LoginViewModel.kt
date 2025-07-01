package com.sinannuhoglu.fooddeliveryapp.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.sinannuhoglu.fooddeliveryapp.util.UserSession
import com.sinannuhoglu.fooddeliveryapp.util.getFriendlyErrorMessage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    var email = MutableStateFlow("")
    var password = MutableStateFlow("")

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage = _errorMessage.asStateFlow()

    private val _loginSuccess = MutableStateFlow(false)
    val loginSuccess = _loginSuccess.asStateFlow()

    fun onLoginClick() {
        if (email.value.isBlank() || password.value.isBlank()) {
            _errorMessage.value = "E-posta ve şifre boş olamaz."
            return
        }

        viewModelScope.launch {
            auth.signInWithEmailAndPassword(email.value, password.value)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        UserSession.email = email.value
                        _errorMessage.value = null
                        _loginSuccess.value = true
                    } else {
                        _errorMessage.value = getFriendlyErrorMessage(task.exception?.message)
                    }
                }
        }
    }
}
