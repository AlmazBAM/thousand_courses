package com.bagmanovam.thousand_courses.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bagmanovam.thousand_courses.domain.useCases.GetCoursesUseCase
import com.bagmanovam.thousand_courses.domain.useCases.RequestCoursesUseCase
import com.bagmanovam.thousand_courses.domain.useCases.SetFavouriteStatusUseCase
import com.bagmanovam.thousand_courses.presentation.home.event.HomeEvent
import com.bagmanovam.thousand_courses.presentation.home.state.HomeUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val requestCoursesUseCase: RequestCoursesUseCase,
    private val getCoursesUseCase: GetCoursesUseCase,
    private val setFavouriteStatusUseCase: SetFavouriteStatusUseCase,
) : ViewModel() {


    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState
        .onStart {
            viewModelScope.launch {
                _uiState.update { state ->
                    state.copy(
                        isRefreshing = true
                    )
                }
                delay(2000) // иммитация длительной операции
                getCoursesUseCase().collect { list ->
                    _uiState.update { state ->
                        state.copy(
                            listCourses = list.courses,
                            isRefreshing = false
                        )
                    }
                }
            }
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
                    requestCoursesUseCase()
                }
            }

            HomeEvent.OnSorted -> {

            }

            is HomeEvent.OnBookMarkClick -> {
                viewModelScope.launch {
                    setFavouriteStatusUseCase(event.id)
                    val list = getCoursesUseCase().first().courses
                    _uiState.update { state ->
                        state.copy(
                            listCourses = list,
                            isRefreshing = false
                        )
                    }
                }
            }
        }
    }


    companion object {
        private val TAG = HomeViewModel::class.java.simpleName
    }
}