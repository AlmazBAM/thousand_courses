package com.bagmanovam.thousand_courses.presentation.home.state

import com.bagmanovam.thousand_courses.domain.model.Course

data class HomeUiState(
    val isRefreshing: Boolean = false,
    val listCourses: List<Course> = emptyList()
)
