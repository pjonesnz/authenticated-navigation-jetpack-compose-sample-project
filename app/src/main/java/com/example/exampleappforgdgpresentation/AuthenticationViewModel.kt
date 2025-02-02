package com.example.exampleappforgdgpresentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

sealed interface AuthenticationState {
    data object Loading: AuthenticationState
    data object NotLoggedIn: AuthenticationState
    data object LoggedIn: AuthenticationState
}

@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    loginRepository: LoginRepository
): ViewModel() {
    val authenticationState = loginRepository.isLoggedIn.map { isLoggedIn ->
        when {
            isLoggedIn -> AuthenticationState.LoggedIn
            else -> AuthenticationState.NotLoggedIn
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = AuthenticationState.Loading
    )
}