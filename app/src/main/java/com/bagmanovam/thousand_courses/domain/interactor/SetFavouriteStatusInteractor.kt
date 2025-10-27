package com.bagmanovam.thousand_courses.domain.interactor

import com.bagmanovam.thousand_courses.domain.reposittory.CoursesRepository
import com.bagmanovam.thousand_courses.domain.useCases.SetFavouriteStatusUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SetFavouriteStatusInteractor(private val repository: CoursesRepository): SetFavouriteStatusUseCase {

    override suspend operator fun invoke(id: Int) = withContext(Dispatchers.IO) {
        repository.setFavouriteStatus(id)
    }
}