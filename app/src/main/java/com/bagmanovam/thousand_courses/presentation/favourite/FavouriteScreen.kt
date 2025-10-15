package com.bagmanovam.thousand_courses.presentation.favourite

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bagmanovam.thousand_courses.core.presentation.SearchBar
import com.bagmanovam.thousand_courses.presentation.theme.Thousand_coursesTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun FavouriteScreen(
    modifier: Modifier = Modifier,
    viewModel: FavouriteViewModel = koinViewModel(),
) {

    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        SearchBar(
            modifier = Modifier,
            text = "",
            onValueChanged = {},
            onFilterClicked = {}
        )
        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {

        }
    }
}

@Preview
@Composable
private fun FavouriteScreenPreview() {
    Thousand_coursesTheme {
        FavouriteScreen()
    }
}