package com.bagmanovam.thousand_courses

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.bagmanovam.thousand_courses.presentation.MainScreen
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
            val homeViewModel = koinViewModel<HomeViewModel>()
            val uiState by homeViewModel.uiState.collectAsStateWithLifecycle()

            MainScreen(
                uiState = uiState,
                onHomeActionClick = homeViewModel::onAction,
                onFavouriteActionClick = homeViewModel::onAction
            )
        }
    }

}