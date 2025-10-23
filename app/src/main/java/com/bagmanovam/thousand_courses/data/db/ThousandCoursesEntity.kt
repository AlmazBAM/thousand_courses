package com.bagmanovam.thousand_courses.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity("courses")
data class ThousandCoursesEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val text: String,
    val price: String,
    val rate: String,
    @ColumnInfo("start_date")
    val startDate: String,
    @ColumnInfo("has_like")
    val hasLike: Boolean,
    @ColumnInfo("publish_date")
    val publishDate: String
)