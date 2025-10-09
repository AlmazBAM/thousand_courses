package com.bagmanovam.thousand_courses.presentation.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.bagmanovam.thousand_courses.presentation.theme.Thousand_coursesTheme


@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
) {
    Scaffold { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {

        }
    }
}

@Preview
@Composable
private fun LoginScreenPreview() {
    Thousand_coursesTheme {
        LoginScreen()
    }
}