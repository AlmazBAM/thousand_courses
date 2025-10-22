package com.bagmanovam.thousand_courses.presentation.login

import android.util.Patterns
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.derivedStateOf
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    val emailState = TextFieldState()
    val isValid = derivedStateOf {
        val regex = "^[A-Za-z0-9+-_.]+@[A-Za-z0-9.-]+.[A-Za-z]{2,}$".toRegex()
        emailState.text.toString().matches(regex)
    }
    val passwordState = TextFieldState()
}