package com.bagmanovam.thousand_courses

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.bagmanovam.thousand_courses.presentation.MainScreen
import com.bagmanovam.thousand_courses.presentation.favourite.FavouriteViewModel
import com.bagmanovam.thousand_courses.presentation.home.HomeViewModel
import com.bagmanovam.thousand_courses.presentation.login.LoginScreen
import com.bagmanovam.thousand_courses.presentation.login.LoginViewModel
import org.koin.androidx.compose.koinViewModel


@Composable
fun CourseNavHost(
    navHostController: NavHostController,
) {
    NavHost(
        navController = navHostController,
        startDestination = Home
    ) {
        composable<Login> {
            val loginViewModel: LoginViewModel = koinViewModel()
            val emailState = loginViewModel.emailState
            val isValid by remember { loginViewModel.isValid }
            val passwordState = loginViewModel.passwordState
            LoginScreen(
                onLoginClick = {
                    navHostController.navigate(Home)
                },
                emailState = emailState,
                passwordState = passwordState,
                isValid = isValid
            )
        }

        composable<Home> {
            val homeViewModel = koinViewModel<HomeViewModel>(viewModelStoreOwner = it)
            val homeState by homeViewModel.uiState.collectAsStateWithLifecycle()
            val homeEvents = homeViewModel.events

            val favouriteViewModel = koinViewModel<FavouriteViewModel>(viewModelStoreOwner = it)
            val favouriteState by favouriteViewModel.uiState.collectAsStateWithLifecycle()

            MainScreen(
                homeState = homeState,
                events = homeEvents,
                favouriteState = favouriteState,
                onHomeActionClick = homeViewModel::onAction,
                onFavouriteActionClick = favouriteViewModel::onAction,
            )
        }
    }

}