package com.bagmanovam.thousand_courses.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    [
        ThousandCoursesEntity::class
    ], version = 1, exportSchema = false
)
abstract class ThousandCoursesDatabase : RoomDatabase() {
    abstract fun getDao(): ThousandCourseDao
}