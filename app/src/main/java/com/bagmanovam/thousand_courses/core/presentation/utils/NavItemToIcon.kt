package com.bagmanovam.thousand_courses.core.presentation.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.bagmanovam.thousand_courses.R
import com.bagmanovam.thousand_courses.presentation.home.Tab

@Composable
fun Tab.navItemToIcon() = when (this) {
    Tab.Home -> painterResource(R.drawable.house)
    Tab.Favourite -> painterResource(R.drawable.bookmark)
    Tab.Profile -> painterResource(R.drawable.person)
}