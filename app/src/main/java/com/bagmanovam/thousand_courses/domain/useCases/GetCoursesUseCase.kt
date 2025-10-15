package com.bagmanovam.thousand_courses.domain.useCases

import com.bagmanovam.thousand_courses.domain.reposittory.CourseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetCoursesUseCase(private val repository: CourseRepository) {

    suspend operator fun invoke() = withContext(Dispatchers.IO) {
        repository.getCourses()
    }
}