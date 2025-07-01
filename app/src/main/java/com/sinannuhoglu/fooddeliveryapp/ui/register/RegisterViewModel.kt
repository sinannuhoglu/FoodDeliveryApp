package com.sinannuhoglu.fooddeliveryapp.ui.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.sinannuhoglu.fooddeliveryapp.util.getFriendlyErrorMessage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    var email = MutableStateFlow("")
    var password = MutableStateFlow("")
    var confirmPassword = MutableStateFlow("")

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage = _errorMessage.asStateFlow()

    private val _registerSuccess = MutableStateFlow(false)
    val registerSuccess = _registerSuccess.asStateFlow()

    fun onRegisterClick() {
        if (email.value.isBlank() || password.value.isBlank() || confirmPassword.value.isBlank()) {
            _errorMessage.value = "Tüm alanlar doldurulmalıdır."
            return
        }

        if (password.value != confirmPassword.value) {
            _errorMessage.value = "Şifreler eşleşmiyor."
            return
        }

        viewModelScope.launch {
            auth.createUserWithEmailAndPassword(email.value, password.value)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        _errorMessage.value = null
                        _registerSuccess.value = true
                    } else {
                        _errorMessage.value = getFriendlyErrorMessage(task.exception?.message)
                    }
                }
        }
    }
}
