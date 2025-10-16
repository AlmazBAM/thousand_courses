package com.bagmanovam.thousand_courses.data.reposittory

import android.util.Log
import com.bagmanovam.thousand_courses.data.internet.CourseApi
import com.bagmanovam.thousand_courses.data.model.CoursesDto
import com.bagmanovam.thousand_courses.domain.reposittory.CourseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CourseRepositoryImpl(
    private val api: CourseApi,
) : CourseRepository {

    private var _courses = MutableStateFlow(CoursesDto(emptyList()))
    val courses: StateFlow<CoursesDto> = _courses

    override suspend fun requestCourses() {
        try {
            val response = api.getCourses()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    val courses = body
                    _courses.value = courses
                } else {
                    _courses.value = CoursesDto(emptyList())
                }
            } else {
                Log.e("Retrofit", "Ошибка ${response.code()}")
                _courses.value = CoursesDto(emptyList())
            }
        } catch (_: Exception) {
            _courses.value = CoursesDto(emptyList())
        }
    }

    override suspend fun getCourses(): Flow<CoursesDto> {
        Log.e(TAG, "getCourses: ${_courses.value.courses.isEmpty()}")
        if (_courses.value.courses.isEmpty()) {
            requestCourses()
        }
        return courses
    }

    override suspend fun setFavouriteStatus(id: Int) {
        _courses.value = CoursesDto(
            _courses.value.courses.map {
                if (it.id == id) {
                    it.copy(hasLike = !it.hasLike)
                } else {
                    it
                }
            }
        )
        _courses.value.courses.forEach {
            Log.i(TAG, "Course: ${it.id} ${it.hasLike}")
        }
    }

    override suspend fun sortByPublishDate() {
        _courses.value = CoursesDto(
            _courses.value.courses.sortedByDescending { it.publishDate }
        )
    }

    companion object {
        private val TAG = CourseRepositoryImpl::class.java.simpleName
    }
}