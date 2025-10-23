package com.bagmanovam.thousand_courses.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ThousandCourseDao {

    @Insert(entity = ThousandCoursesEntity::class)
    fun saveCourses(courses: List<ThousandCoursesEntity>)

    @Query("SELECT * FROM courses")
    fun getCourses(): Flow<List<ThousandCoursesEntity>>

    @Query("UPDATE courses SET has_like= NOT has_like WHERE id= :noteId")
    fun switchHasLikeStatus(noteId: Int)
}