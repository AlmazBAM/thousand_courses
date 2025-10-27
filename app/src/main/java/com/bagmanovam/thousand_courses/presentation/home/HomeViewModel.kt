package com.bagmanovam.thousand_courses.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bagmanovam.thousand_courses.core.domain.onError
import com.bagmanovam.thousand_courses.core.domain.onSuccess
import com.bagmanovam.thousand_courses.domain.useCases.GetCoursesUseCase
import com.bagmanovam.thousand_courses.domain.useCases.RequestCoursesUseCase
import com.bagmanovam.thousand_courses.domain.useCases.SetFavouriteStatusUseCase
import com.bagmanovam.thousand_courses.domain.useCases.SortByPublishDateUseCase
import com.bagmanovam.thousand_courses.presentation.home.state.HomeUiState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val requestCoursesUseCase: RequestCoursesUseCase,
    private val getCoursesUseCase: GetCoursesUseCase,
    private val sortByPublishDateUseCase: SortByPublishDateUseCase,
    private val setFavouriteStatusUseCase: SetFavouriteStatusUseCase,
) : ViewModel() {


    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState
        .onStart {
            viewModelScope.launch {
                getCoursesUseCase().collect { list ->
                    Log.e(TAG, "getCoursesUseCase collect: $list ", )
                    _uiState.update { state ->
                        state.copy(
                            listCourses = list,
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

    private val _events = Channel<HomeScreenEvent>()
    val events = _events.receiveAsFlow()

    fun onAction(event: HomeScreenAction) {
        when (event) {
            HomeScreenAction.OnRequest -> {
                _uiState.update { state ->
                    state.copy(
                        isRefreshing = true
                    )
                }
                viewModelScope.launch {
                    requestCoursesUseCase()
                        .onSuccess {
                            _uiState.update { state ->
                                state.copy(
                                    isRefreshing = false
                                )
                            }
                        }
                        .onError {
                            _events.send(HomeScreenEvent.Error(it))
                        }
                }
            }

            HomeScreenAction.OnSorted -> {
                viewModelScope.launch {
                    val courses = sortByPublishDateUseCase().firstOrNull().orEmpty()
                    _uiState.update { state ->
                        state.copy(
                            listCourses = courses,
                        )
                    }
                }
            }

            is HomeScreenAction.OnBookMarkClick -> {
                viewModelScope.launch {
                    setFavouriteStatusUseCase(event.id)
                }
            }
        }
    }


    companion object {
        private val TAG = HomeViewModel::class.java.simpleName
    }
}