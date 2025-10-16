package com.bagmanovam.thousand_courses.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class CoursesDto(
    @SerialName("courses")
    val courses: List<CourseDto>
)