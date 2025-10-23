package com.bagmanovam.thousand_courses.data.mapper

import com.bagmanovam.thousand_courses.data.db.ThousandCoursesEntity
import com.bagmanovam.thousand_courses.data.model.CourseDto
import com.bagmanovam.thousand_courses.data.model.CoursesDto
import com.bagmanovam.thousand_courses.domain.model.Course
import com.bagmanovam.thousand_courses.domain.model.Courses


fun CourseDto.toDomain(): Course {
    return Course(
        id = this.id,
        title = this.title,
        text = this.text,
        price = this.price,
        rate = this.rate,
        startDate = this.startDate,
        hasLike = this.hasLike,
        publishDate = this.publishDate
    )
}

fun CoursesDto.toDomains(): Courses {
    return Courses(
        this.courses.map {
            it.toDomain()
        }
    )
}

fun Course.domainToEntity(): ThousandCoursesEntity {
    return ThousandCoursesEntity(
        id = this.id,
        title = this.title,
        text = this.text,
        price = this.price,
        rate = this.rate,
        startDate = this.startDate,
        hasLike = this.hasLike,
        publishDate = this.publishDate
    )
}

fun ThousandCoursesEntity.entityToDomain(): Course {
    return Course(
        id = this.id,
        title = this.title,
        text = this.text,
        price = this.price,
        rate = this.rate,
        startDate = this.startDate,
        hasLike = this.hasLike,
        publishDate = this.publishDate
    )
}