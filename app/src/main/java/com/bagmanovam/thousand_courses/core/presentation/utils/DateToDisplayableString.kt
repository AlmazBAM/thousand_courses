package com.bagmanovam.thousand_courses.core.presentation.utils

import com.bagmanovam.thousand_courses.other.Commons
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale


fun String.dateToDisplayableString(): String {
    val inputFormatter = DateTimeFormatter.ofPattern(Commons.DATE_INPUT_FORMAT)
    val outputFormatter = DateTimeFormatter.ofPattern(Commons.DATE_OUTPUT_FORMAT, Locale("ru"))

    val date = LocalDate.parse(this, inputFormatter)
    return date.format(outputFormatter)
}
