package com.bagmanovam.thousand_courses.di

import com.bagmanovam.thousand_courses.data.internet.CourseApi
import com.bagmanovam.thousand_courses.data.reposittory.CourseRepositoryImpl
import com.bagmanovam.thousand_courses.domain.reposittory.CourseRepository
import com.bagmanovam.thousand_courses.domain.useCases.GetCoursesUseCase
import com.bagmanovam.thousand_courses.domain.useCases.GetFavouriteCoursesUseCase
import com.bagmanovam.thousand_courses.domain.useCases.RequestCoursesUseCase
import com.bagmanovam.thousand_courses.domain.useCases.SetFavouriteStatusUseCase
import com.bagmanovam.thousand_courses.domain.useCases.SortByPublishDateUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val dataModule = module {
    single<CourseRepository> { CourseRepositoryImpl(get()) }
    singleOf(::GetCoursesUseCase)
    singleOf(::SetFavouriteStatusUseCase)
    singleOf(::GetFavouriteCoursesUseCase)
    singleOf(::RequestCoursesUseCase)
    singleOf(::SortByPublishDateUseCase)

    single {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://drive.usercontent.google.com/")
            .build()
            .create(CourseApi::class.java)
    }
}