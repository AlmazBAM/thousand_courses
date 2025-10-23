package com.bagmanovam.thousand_courses.domain.interactor

import com.bagmanovam.thousand_courses.domain.reposittory.CourseApiRepository
import com.bagmanovam.thousand_courses.domain.useCases.RequestCoursesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RequestCoursesInteractor(private val repository: CourseApiRepository) : RequestCoursesUseCase {

    override suspend operator fun invoke() = withContext(Dispatchers.IO) {
        repository.requestCourses()
    }
}