package com.bagmanovam.thousand_courses.data.reposittory

import android.content.Context
import android.util.Log
import com.bagmanovam.thousand_courses.domain.model.Course
import com.bagmanovam.thousand_courses.domain.model.Courses
import com.bagmanovam.thousand_courses.domain.reposittory.CourseRepository
import kotlinx.coroutines.delay
import kotlinx.serialization.json.Json
import java.io.File

class CourseRepositoryImpl(private val context: Context) : CourseRepository {

    override suspend fun getCourses(): Courses {
        delay(2000)

        return try {
            val content = context.assets.open("courses.json").bufferedReader().use {
                it.readText().trim()
            }
            Json.decodeFromString(content)
        } catch (e: Exception) {
            Log.e("TAG", "getCourses: caught exception ${e.printStackTrace()}")
            Courses(
                courses = emptyList()
            )
        }
    }
}