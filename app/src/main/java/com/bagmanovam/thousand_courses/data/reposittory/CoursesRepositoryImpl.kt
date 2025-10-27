package com.bagmanovam.thousand_courses.data.reposittory

import com.bagmanovam.thousand_courses.core.data.safeCall
import com.bagmanovam.thousand_courses.core.domain.NetworkError
import com.bagmanovam.thousand_courses.core.domain.Result
import com.bagmanovam.thousand_courses.core.domain.map
import com.bagmanovam.thousand_courses.data.db.ThousandCourseDao
import com.bagmanovam.thousand_courses.data.internet.CourseApi
import com.bagmanovam.thousand_courses.data.mapper.domainToEntity
import com.bagmanovam.thousand_courses.data.mapper.entityToDomain
import com.bagmanovam.thousand_courses.data.mapper.toDomains
import com.bagmanovam.thousand_courses.domain.model.Course
import com.bagmanovam.thousand_courses.domain.model.Courses
import com.bagmanovam.thousand_courses.domain.reposittory.CoursesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CoursesRepositoryImpl(
    private val dao: ThousandCourseDao,
    private val api: CourseApi,
) : CoursesRepository {
    override suspend fun requestCourses(): Result<Courses, NetworkError> {
        val response = safeCall { api.getCourses() }.map { dto ->
            val courses = dto.toDomains()
            dao.saveCourses(courses.courses.map {
                it.domainToEntity()
            })
            courses
        }
        return response
    }

    override suspend fun getCourses(): Flow<List<Course>> {
        return dao.getCourses().map { list ->
            list.map {
                it.entityToDomain()
            }
        }
    }

    override suspend fun setFavouriteStatus(id: Int) {
        dao.switchHasLikeStatus(id)
    }

    override suspend fun saveCourses(courses: List<Course>) {
        dao.saveCourses(courses.map { it.domainToEntity() })
    }

    override suspend fun sortByPublishDate(): Flow<List<Course>> {
        return dao.getCourses().map { list ->
            list.map {
                it.entityToDomain()
            }
        }
    }

    companion object {
        private val TAG = CoursesRepositoryImpl::class.java.simpleName
    }
}