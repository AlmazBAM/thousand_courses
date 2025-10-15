package com.bagmanovam.thousand_courses.domain.reposittory

import com.bagmanovam.thousand_courses.domain.model.Courses

interface CourseRepository {

    suspend fun getCourses(): Courses
}