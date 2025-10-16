package com.bagmanovam.thousand_courses.presentation.home.event

import com.bagmanovam.thousand_courses.domain.model.Course

sealed interface HomeEvent {
    data object OnRequest : HomeEvent
    data object OnSorted : HomeEvent
    data class OnBookMarkClick(val course: Course) : HomeEvent
}