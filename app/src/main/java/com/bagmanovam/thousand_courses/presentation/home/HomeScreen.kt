package com.bagmanovam.thousand_courses.presentation.home

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bagmanovam.thousand_courses.core.presentation.components.SearchBar
import com.bagmanovam.thousand_courses.core.presentation.utils.navItemToIcon
import com.bagmanovam.thousand_courses.core.presentation.utils.navItemToString
import com.bagmanovam.thousand_courses.presentation.theme.Green
import com.bagmanovam.thousand_courses.presentation.theme.Grey200
import com.bagmanovam.thousand_courses.presentation.theme.Thousand_coursesTheme
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import org.koin.androidx.compose.koinViewModel


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = koinViewModel(),
) {
    var selected by remember { mutableStateOf(Tab.Home) }
    val isRefreshing by viewModel.isRefreshing.collectAsStateWithLifecycle()
    val refreshState = rememberSwipeRefreshState(isRefreshing)

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
        Column(
            modifier = modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            SearchBar(
                modifier = Modifier,
                text = "",
                onValueChanged = {},
                onFilterClicked = {}
            )
            SwipeRefresh(
                modifier = Modifier.weight(1f),
                state = refreshState,
                onRefresh = {
                    viewModel.getCourseList()
                }
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {

                }
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
            indicatorColor = Color.Transparent, // без плашки под иконкой
            unselectedIconColor = unselected,
            unselectedTextColor = unselected
        )
    )
}

enum class Tab(val description: String) {
    Home("Home page"),
    Favourite("Favourite page"),
    Profile("Profile page"),
}

@Preview
@Composable
private fun HomeScreenPreview() {
    Thousand_coursesTheme {
        HomeScreen()
    }
}