package com.bagmanovam.thousand_courses.domain.useCases

import com.bagmanovam.thousand_courses.domain.model.Course

interface SaveCoursesUseCase {

    suspend operator fun invoke(courses: List<Course>)
}