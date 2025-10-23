package com.bagmanovam.thousand_courses.domain.reposittory

import com.bagmanovam.thousand_courses.domain.model.Course
import kotlinx.coroutines.flow.Flow

interface CourseDbRepository {

    suspend fun getCourses(): Flow<List<Course>>
    suspend fun saveCourses(courses: List<Course>)
    suspend fun setFavouriteStatus(id: Int)
    suspend fun sortByPublishDate(): Flow<List<Course>>
}