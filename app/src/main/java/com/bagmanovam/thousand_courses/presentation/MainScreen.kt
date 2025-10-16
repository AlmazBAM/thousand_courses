package com.bagmanovam.thousand_courses.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.bagmanovam.thousand_courses.core.presentation.Tab
import com.bagmanovam.thousand_courses.core.presentation.utils.navItemToIcon
import com.bagmanovam.thousand_courses.core.presentation.utils.navItemToString
import com.bagmanovam.thousand_courses.presentation.favourite.FavouriteScreen
import com.bagmanovam.thousand_courses.presentation.home.HomeScreen
import com.bagmanovam.thousand_courses.presentation.home.event.HomeEvent
import com.bagmanovam.thousand_courses.presentation.home.state.HomeUiState
import com.bagmanovam.thousand_courses.presentation.profile.ProfileScreen
import com.bagmanovam.thousand_courses.presentation.theme.Green
import com.bagmanovam.thousand_courses.presentation.theme.Grey200


@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    uiState: HomeUiState,
    onHomeActionClick: (HomeEvent) -> Unit,
    onFavouriteActionClick: (HomeEvent) -> Unit
) {
    var selected by remember { mutableStateOf(Tab.Home) }

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.onBackground,
            ) {
                Column {
                    HorizontalDivider(
                        thickness = 2.dp, color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Row {
                        listOf(Tab.Home, Tab.Favourite, Tab.Profile).forEach { item ->
                            NavItem(
                                item = item,
                                selected = selected,
                                primary = Green,
                                unselected = Grey200,
                                onClick = { selected = item }
                            )
                        }
                    }
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            when (selected) {
                Tab.Home -> HomeScreen(
                    uiState = uiState,
                    onHomeActionClick = onHomeActionClick
                )
                Tab.Favourite -> FavouriteScreen(
                    courseList = uiState.listCourses,
                    onFavouriteActionClick = { onFavouriteActionClick }
                )
                Tab.Profile -> ProfileScreen()
            }
        }
    }
}

@Composable
private fun RowScope.NavItem(
    item: Tab,
    selected: Tab,
    primary: Color,
    unselected: Color,
    onClick: (Tab) -> Unit,
) {
    NavigationBarItem(
        selected = selected == item,
        onClick = { onClick(item) },
        icon = {
            Icon(
                painter = item.navItemToIcon(),
                contentDescription = item.description,
                tint = if (selected == item) Green else MaterialTheme.colorScheme.onPrimary
            )
        },
        label = {
            Text(
                text = item.navItemToString(), style = MaterialTheme.typography.labelSmall.copy(
                    color = if (selected == item) Green else MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = primary,
            selectedTextColor = primary,
            indicatorColor = Color.Transparent,
            unselectedIconColor = unselected,
            unselectedTextColor = unselected
        )
    )
}
