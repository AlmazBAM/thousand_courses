package com.bagmanovam.thousand_courses.domain.interactor

import com.bagmanovam.thousand_courses.domain.reposittory.CourseDbRepository
import com.bagmanovam.thousand_courses.domain.useCases.SortByPublishDateUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import kotlin.collections.sortedByDescending

class SortByPublishDateInteractor(private val repository: CourseDbRepository) :
    SortByPublishDateUseCase {

    override suspend operator fun invoke() = withContext(Dispatchers.IO) {
        repository.sortByPublishDate().map { list -> list.sortedByDescending { it.publishDate } }
    }
}