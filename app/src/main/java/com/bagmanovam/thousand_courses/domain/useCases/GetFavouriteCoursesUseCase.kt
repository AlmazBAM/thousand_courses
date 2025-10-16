package com.bagmanovam.thousand_courses.domain.useCases

import com.bagmanovam.thousand_courses.data.mapper.toDomains
import com.bagmanovam.thousand_courses.domain.reposittory.CourseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import kotlin.collections.filter

class GetFavouriteCoursesUseCase(private val repository: CourseRepository) {

    suspend operator fun invoke() = withContext(Dispatchers.IO) {
        repository.getCourses().map { it.toDomains().courses.filter { it.hasLike } }
    }
}