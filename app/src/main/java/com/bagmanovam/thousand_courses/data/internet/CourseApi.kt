package com.bagmanovam.thousand_courses.data.internet

import com.bagmanovam.thousand_courses.data.model.CoursesDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Streaming


interface CourseApi {

    @GET("u/0/uc?id=15arTK7XT2b7Yv4BJsmDctA4Hg-BbS8-q&export=download")
    @Streaming
    suspend fun getCourses(): Response<CoursesDto>
}