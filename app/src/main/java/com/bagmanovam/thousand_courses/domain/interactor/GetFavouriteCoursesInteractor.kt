package com.bagmanovam.thousand_courses.domain.interactor

import com.bagmanovam.thousand_courses.domain.reposittory.CoursesRepository
import com.bagmanovam.thousand_courses.domain.useCases.GetFavouriteCoursesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class GetFavouriteCoursesInteractor(private val repository: CoursesRepository): GetFavouriteCoursesUseCase {

    override suspend operator fun invoke() = withContext(Dispatchers.IO) {
        repository.getCourses().map { courses -> courses.filter { it.hasLike } }
    }
}