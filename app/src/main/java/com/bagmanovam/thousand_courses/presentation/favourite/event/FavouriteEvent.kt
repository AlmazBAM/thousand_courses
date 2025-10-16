package com.bagmanovam.thousand_courses.presentation.favourite.event

sealed interface FavouriteEvent {
    data class OnBookMarkClick(val id: Int) : FavouriteEvent
}