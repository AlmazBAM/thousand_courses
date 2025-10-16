package com.bagmanovam.thousand_courses.domain.useCases

import com.bagmanovam.thousand_courses.domain.reposittory.CourseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SetFavouriteStatusUseCase(private val repository: CourseRepository) {

    suspend operator fun invoke(id: Int) = withContext(Dispatchers.IO) {
        repository.setFavouriteStatus(id)
    }
}