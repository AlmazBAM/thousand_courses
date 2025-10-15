package com.bagmanovam.thousand_courses.core.presentation.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.bagmanovam.thousand_courses.R
import com.bagmanovam.thousand_courses.core.presentation.Tab

@Composable
fun Tab.navItemToString() = when (this) {
    Tab.Home -> stringResource(R.string.home)
    Tab.Favourite -> stringResource(R.string.bookmark)
    Tab.Profile -> stringResource(R.string.person)
}