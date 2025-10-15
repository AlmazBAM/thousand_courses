package com.bagmanovam.thousand_courses.di

import com.bagmanovam.thousand_courses.data.reposittory.CourseRepositoryImpl
import com.bagmanovam.thousand_courses.domain.reposittory.CourseRepository
import com.bagmanovam.thousand_courses.domain.useCases.GetCoursesUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


val dataModule = module {
    single<CourseRepository> { CourseRepositoryImpl(get()) }
    singleOf(::GetCoursesUseCase)
}