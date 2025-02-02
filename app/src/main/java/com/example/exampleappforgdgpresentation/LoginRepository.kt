package com.example.exampleappforgdgpresentation

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginRepository {
    private var _isLoggedIn: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isLoggedIn = _isLoggedIn.asStateFlow()

    suspend fun onLogin(): Boolean {
        delay(300)
        _isLoggedIn.value = true
        return true
    }

    fun onLogout() {
        _isLoggedIn.value = false
    }
}