package com.bagmanovam.thousand_courses.di

import com.bagmanovam.thousand_courses.presentation.favourite.FavouriteViewModel
import com.bagmanovam.thousand_courses.presentation.home.HomeViewModel
import com.bagmanovam.thousand_courses.presentation.login.LoginViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {

    viewModelOf(::LoginViewModel)
    viewModelOf(::HomeViewModel)
    viewModelOf(::FavouriteViewModel)
}