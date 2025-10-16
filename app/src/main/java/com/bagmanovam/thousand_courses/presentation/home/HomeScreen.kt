package com.bagmanovam.thousand_courses.presentation.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bagmanovam.thousand_courses.R
import com.bagmanovam.thousand_courses.core.presentation.CourseCard
import com.bagmanovam.thousand_courses.core.presentation.SearchBar
import com.bagmanovam.thousand_courses.presentation.home.event.HomeEvent
import com.bagmanovam.thousand_courses.presentation.home.state.HomeUiState
import com.bagmanovam.thousand_courses.presentation.theme.Green
import com.bagmanovam.thousand_courses.presentation.theme.Thousand_coursesTheme
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    uiState: HomeUiState,
    onHomeActionClick: (HomeEvent) -> Unit,
) {
    val refreshState = rememberSwipeRefreshState(uiState.isRefreshing)

    Column(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .fillMaxSize()
    ) {
        SearchBar(
            modifier = Modifier.padding(bottom = 16.dp),
            text = "",
            onValueChanged = {},
            onFilterClicked = {}
        )

        SwipeRefresh(
            modifier = Modifier.weight(1f),
            state = refreshState,
            onRefresh = { onHomeActionClick(HomeEvent.OnRequest) }
        ) {

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
            ) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                        contentAlignment = Alignment.CenterEnd
                    ) {
                        Row(
                            modifier = Modifier.clickable {
                                onHomeActionClick(HomeEvent.OnSorted)
                            },
                            horizontalArrangement = Arrangement.spacedBy(4.dp),
                            verticalAlignment = Alignment.Bottom
                        ) {
                            Text(
                                text = stringResource(R.string.by_added_date),
                                style = MaterialTheme.typography.bodySmall.copy(
                                    color = Green,
                                    fontSize = 14.sp,
                                    lineHeight = 20.sp
                                )
                            )
                            Icon(
                                painter = painterResource(R.drawable.arrows),
                                contentDescription = "Sort by date",
                                tint = Green
                            )
                        }

                    }
                }
                items(uiState.listCourses) { course ->
                    CourseCard(
                        modifier = Modifier.fillMaxWidth(),
                        course = course,
                        onBookMarkClick = { onHomeActionClick(HomeEvent.OnBookMarkClick(course)) }
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                }
            }
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    Thousand_coursesTheme {
        HomeScreen(
            uiState = HomeUiState(),
            onHomeActionClick = {}
        )
    }
}