package com.bagmanovam.thousand_courses.data.reposittory

import com.bagmanovam.thousand_courses.data.db.ThousandCourseDao
import com.bagmanovam.thousand_courses.data.mapper.domainToEntity
import com.bagmanovam.thousand_courses.data.mapper.entityToDomain
import com.bagmanovam.thousand_courses.domain.model.Course
import com.bagmanovam.thousand_courses.domain.reposittory.CourseDbRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CourseDbRepositoryImpl(
    private val dao: ThousandCourseDao,
) : CourseDbRepository {
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
        private val TAG = CourseDbRepositoryImpl::class.java.simpleName
    }
}