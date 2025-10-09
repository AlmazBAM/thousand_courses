package com.bagmanovam.thousand_courses.core.presentation.utils

import com.bagmanovam.thousand_courses.R
import com.bagmanovam.thousand_courses.other.LoginEnums

fun labelToStringResources(label: LoginEnums): Int {
    return when (label) {
        LoginEnums.EMAIL -> R.string.email
        LoginEnums.PASSWORD -> R.string.password
        LoginEnums.EMAIL_PLACEHOLDER -> R.string.email_placeholder
        LoginEnums.PASSWORD_PLACEHOLDER -> R.string.password_placeholder
    }
}