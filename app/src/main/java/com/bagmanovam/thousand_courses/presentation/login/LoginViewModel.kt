package com.bagmanovam.thousand_courses.presentation.login

import android.util.Patterns
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.derivedStateOf
import androidx.lifecycle.ViewModel

class LoginViewModel: ViewModel() {

    val emailState = TextFieldState()
    val isValid = derivedStateOf {
        Patterns.EMAIL_ADDRESS.matcher(emailState.text.toString()).matches()
    }
    val passwordState = TextFieldState()
}