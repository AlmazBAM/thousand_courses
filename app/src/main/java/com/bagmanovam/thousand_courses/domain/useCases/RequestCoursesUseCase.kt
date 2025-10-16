package com.bagmanovam.thousand_courses.domain.useCases

import com.bagmanovam.thousand_courses.data.mapper.toDomains
import com.bagmanovam.thousand_courses.domain.reposittory.CourseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class RequestCoursesUseCase(private val repository: CourseRepository) {

    suspend operator fun invoke() = withContext(Dispatchers.IO) {
        repository.requestCourses()
    }
}