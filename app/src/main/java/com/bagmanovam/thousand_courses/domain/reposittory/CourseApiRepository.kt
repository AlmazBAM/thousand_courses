package com.bagmanovam.thousand_courses.domain.reposittory

import com.bagmanovam.thousand_courses.core.domain.NetworkError
import com.bagmanovam.thousand_courses.core.domain.Result
import com.bagmanovam.thousand_courses.domain.model.Courses

interface CourseApiRepository {
    suspend fun requestCourses(): Result<Courses, NetworkError>
}