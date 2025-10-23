package com.bagmanovam.thousand_courses.data.reposittory

import com.bagmanovam.thousand_courses.core.data.safeCall
import com.bagmanovam.thousand_courses.core.domain.NetworkError
import com.bagmanovam.thousand_courses.core.domain.Result
import com.bagmanovam.thousand_courses.core.domain.map
import com.bagmanovam.thousand_courses.data.internet.CourseApi
import com.bagmanovam.thousand_courses.data.mapper.toDomains
import com.bagmanovam.thousand_courses.domain.model.Courses
import com.bagmanovam.thousand_courses.domain.reposittory.CourseApiRepository

class CourseApiRepositoryImpl(
    private val api: CourseApi,
) : CourseApiRepository {

    override suspend fun requestCourses(): Result<Courses, NetworkError> {
        val response = safeCall { api.getCourses() }.map {
            it.toDomains()
        }
        return response
    }
}