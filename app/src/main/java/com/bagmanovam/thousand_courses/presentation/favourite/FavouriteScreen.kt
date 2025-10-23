package com.bagmanovam.thousand_courses.presentation.favourite

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bagmanovam.thousand_courses.R
import com.bagmanovam.thousand_courses.core.presentation.CourseCard
import com.bagmanovam.thousand_courses.presentation.favourite.FavouriteScreenAction
import com.bagmanovam.thousand_courses.presentation.favourite.state.FavouriteUiState
import com.bagmanovam.thousand_courses.presentation.theme.Thousand_coursesTheme

@Composable
fun FavouriteScreen(
    modifier: Modifier = Modifier,
    uiState: FavouriteUiState,
    onFavouriteActionClick: (FavouriteScreenAction) -> Unit,
) {

    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        Text(
            modifier = Modifier.padding(vertical = 16.dp),
            text = stringResource(R.string.favourite),
            style = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 22.sp,
                lineHeight = 28.sp
            )
        )
        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(uiState.listCourses) { course ->
                CourseCard(
                    modifier = Modifier.fillMaxWidth(),
                    course = course,
                    onBookMarkClick = { onFavouriteActionClick(FavouriteScreenAction.OnBookMarkClick(course.id)) }
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Preview
@Composable
private fun FavouriteScreenPreview() {
    Thousand_coursesTheme {
        FavouriteScreen(
            uiState = FavouriteUiState(),
            onFavouriteActionClick = {}
        )
    }
}