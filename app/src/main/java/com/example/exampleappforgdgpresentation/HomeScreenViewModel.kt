package com.example.exampleappforgdgpresentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val loginRepository: LoginRepository
) : ViewModel() {
    fun onLogoutClick() {
        loginRepository.onLogout()
    }
}