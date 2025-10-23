package com.bagmanovam.thousand_courses.domain.useCases

interface SetFavouriteStatusUseCase {

    suspend operator fun invoke(id: Int)
}