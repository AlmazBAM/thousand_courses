package com.bagmanovam.thousand_courses.domain.interactor

import com.bagmanovam.thousand_courses.domain.reposittory.CourseDbRepository
import com.bagmanovam.thousand_courses.domain.useCases.GetCoursesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetCoursesInteractor(private val repository: CourseDbRepository) : GetCoursesUseCase {

    override suspend operator fun invoke() = withContext(Dispatchers.IO) {
        repository.getCourses()
    }
}