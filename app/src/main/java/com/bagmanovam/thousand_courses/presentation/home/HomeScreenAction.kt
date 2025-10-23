package com.bagmanovam.thousand_courses.presentation.home

sealed interface HomeScreenAction {
    data object OnRequest : HomeScreenAction
    data object OnSorted : HomeScreenAction
    data class OnBookMarkClick(val id: Int) : HomeScreenAction
}