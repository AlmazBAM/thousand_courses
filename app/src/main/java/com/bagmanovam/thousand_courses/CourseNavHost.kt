package com.bagmanovam.thousand_courses

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


@Composable
fun CourseNavHost(
    navHostController: NavHostController,
) {
    NavHost(
        navController = navHostController,
        startDestination = Login
    ) {
        composable<Login> {

        }

        composable<Home> {

        }

        composable<Favourite> {

        }
    }

}