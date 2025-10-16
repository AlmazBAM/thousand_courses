package com.bagmanovam.thousand_courses.presentation.favourite.state

import com.bagmanovam.thousand_courses.domain.model.Course

data class FavouriteUiState(
    val listCourses: List<Course> = emptyList()
)
