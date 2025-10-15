package com.bagmanovam.thousand_courses.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Courses(
    @SerialName("courses")
    val courses: List<Course>
)