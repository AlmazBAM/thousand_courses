package com.bagmanovam.thousand_courses.domain.reposittory

import com.bagmanovam.thousand_courses.data.model.CoursesDto
import com.bagmanovam.thousand_courses.domain.model.Courses
import kotlinx.coroutines.flow.Flow

interface CourseRepository {

    suspend fun requestCourses()
    suspend fun getCourses(): Flow<CoursesDto>
    suspend fun setFavouriteStatus(id: Int)
}