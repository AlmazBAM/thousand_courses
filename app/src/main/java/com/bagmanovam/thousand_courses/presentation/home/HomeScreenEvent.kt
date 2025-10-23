package com.bagmanovam.thousand_courses.presentation.home

import com.bagmanovam.thousand_courses.core.domain.NetworkError

sealed interface HomeScreenEvent {
    data class Error(val error: NetworkError): HomeScreenEvent
}