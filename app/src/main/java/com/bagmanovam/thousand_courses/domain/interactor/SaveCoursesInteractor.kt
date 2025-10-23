package com.bagmanovam.thousand_courses.domain.interactor

import com.bagmanovam.thousand_courses.domain.model.Course
import com.bagmanovam.thousand_courses.domain.reposittory.CourseDbRepository
import com.bagmanovam.thousand_courses.domain.useCases.SaveCoursesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SaveCoursesInteractor(private val repository: CourseDbRepository) : SaveCoursesUseCase {

    override suspend operator fun invoke(courses: List<Course>) = withContext(Dispatchers.IO) {
        repository.saveCourses(courses)
    }
}