package com.bagmanovam.thousand_courses.domain.interactor

import com.bagmanovam.thousand_courses.domain.reposittory.CoursesRepository
import com.bagmanovam.thousand_courses.domain.useCases.RequestCoursesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RequestCoursesInteractor(private val repository: CoursesRepository) : RequestCoursesUseCase {

    override suspend operator fun invoke() = withContext(Dispatchers.IO) {
        repository.requestCourses()
    }
}