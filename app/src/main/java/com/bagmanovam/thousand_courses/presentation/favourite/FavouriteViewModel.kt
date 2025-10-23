package com.bagmanovam.thousand_courses.presentation.favourite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bagmanovam.thousand_courses.domain.useCases.GetFavouriteCoursesUseCase
import com.bagmanovam.thousand_courses.domain.useCases.SetFavouriteStatusUseCase
import com.bagmanovam.thousand_courses.presentation.favourite.FavouriteScreenAction
import com.bagmanovam.thousand_courses.presentation.favourite.state.FavouriteUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FavouriteViewModel(
    private val getFavouriteCoursesUseCase: GetFavouriteCoursesUseCase,
    private val setFavouriteStatusUseCase: SetFavouriteStatusUseCase,
) : ViewModel() {


    private val _uiState = MutableStateFlow(FavouriteUiState())
    val uiState = _uiState
        .onStart {
            viewModelScope.launch {
                getFavouriteCoursesUseCase().collect { list ->
                    _uiState.update { state ->
                        state.copy(
                            listCourses = list,
                        )
                    }
                }
            }
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            FavouriteUiState()
        )

    fun onAction(event: FavouriteScreenAction) {
        when (event) {

            is FavouriteScreenAction.OnBookMarkClick -> {
                viewModelScope.launch {
                    setFavouriteStatusUseCase(event.id)
//                    val list = getFavouriteCoursesUseCase()
//                    _uiState.update { state ->
//                        state.copy(
//                            listCourses = list,
//                        )
//                    }
                }
            }
        }
    }
}