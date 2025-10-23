package com.bagmanovam.thousand_courses.domain.useCases

import com.bagmanovam.thousand_courses.domain.model.Course
import kotlinx.coroutines.flow.Flow

interface GetCoursesUseCase {

    suspend operator fun invoke(): Flow<List<Course>>
}