package com.bagmanovam.thousand_courses.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bagmanovam.thousand_courses.domain.useCases.GetCoursesUseCase
import com.bagmanovam.thousand_courses.presentation.home.event.HomeEvent
import com.bagmanovam.thousand_courses.presentation.home.state.HomeUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getCoursesUseCase: GetCoursesUseCase,
) : ViewModel() {


    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState
        .onStart {

        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            HomeUiState()
        )

    fun onAction(event: HomeEvent) {
        when (event) {
            HomeEvent.OnRequest -> {
                viewModelScope.launch {
                    _uiState.update { state ->
                        state.copy(
                            isRefreshing = true
                        )
                    }
                    val list = getCoursesUseCase().courses
                    _uiState.update { state ->
                        state.copy(
                            listCourses = list,
                            isRefreshing = false
                        )
                    }
                }
            }

            HomeEvent.OnSorted -> {

            }

            is HomeEvent.OnBookMarkClick -> {

            }
        }
    }


    companion object {
        private val TAG = HomeViewModel::class.java.simpleName
    }
}