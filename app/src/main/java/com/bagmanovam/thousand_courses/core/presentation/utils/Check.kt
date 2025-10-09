package com.bagmanovam.thousand_courses.core.presentation.utils

import com.bagmanovam.thousand_courses.other.Commons

object Check {

    fun String.isValidEmail() = this.matches(Commons.emailRegex.toRegex())
}