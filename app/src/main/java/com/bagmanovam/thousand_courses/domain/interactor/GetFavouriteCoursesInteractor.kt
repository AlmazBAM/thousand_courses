package com.bagmanovam.thousand_courses.domain.interactor

import com.bagmanovam.thousand_courses.domain.reposittory.CourseDbRepository
import com.bagmanovam.thousand_courses.domain.useCases.GetFavouriteCoursesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class GetFavouriteCoursesInteractor(private val repository: CourseDbRepository): GetFavouriteCoursesUseCase {

    override suspend operator fun invoke() = withContext(Dispatchers.IO) {
        repository.getCourses().map { courses -> courses.filter { it.hasLike } }
    }
}