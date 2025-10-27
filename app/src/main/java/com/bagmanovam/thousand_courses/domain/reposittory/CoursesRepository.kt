package com.bagmanovam.thousand_courses.domain.reposittory

import com.bagmanovam.thousand_courses.core.domain.NetworkError
import com.bagmanovam.thousand_courses.core.domain.Result
import com.bagmanovam.thousand_courses.domain.model.Course
import com.bagmanovam.thousand_courses.domain.model.Courses
import kotlinx.coroutines.flow.Flow

interface CoursesRepository {
    suspend fun requestCourses(): Result<Courses, NetworkError>
    suspend fun getCourses(): Flow<List<Course>>
    suspend fun saveCourses(courses: List<Course>)
    suspend fun setFavouriteStatus(id: Int)
    suspend fun sortByPublishDate(): Flow<List<Course>>
}