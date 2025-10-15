package com.bagmanovam.thousand_courses.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bagmanovam.thousand_courses.domain.model.Courses
import com.bagmanovam.thousand_courses.domain.useCases.GetCoursesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getCoursesUseCase: GetCoursesUseCase
) : ViewModel() {

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing = _isRefreshing.asStateFlow()
    private val _courseList = MutableStateFlow<Courses>(Courses(emptyList()))
    val courseList = _courseList.asStateFlow()

    fun getCourseList() {
        viewModelScope.launch {
            _isRefreshing.value = true
            _courseList.value = getCoursesUseCase()
            Log.i(TAG, "getCourseList: ${_courseList.value.courses.size}")
            _isRefreshing.value = false
        }
    }



    companion object {
        private val TAG = HomeViewModel::class.java.simpleName
    }
}