package com.bagmanovam.thousand_courses.presentation.favourite

sealed interface FavouriteScreenAction {
    data class OnBookMarkClick(val id: Int) : FavouriteScreenAction
}