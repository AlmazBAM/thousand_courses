package com.bagmanovam.thousand_courses.presentation.login

import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.intl.LocaleList
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import com.bagmanovam.thousand_courses.R
import com.bagmanovam.thousand_courses.core.presentation.LoginItem
import com.bagmanovam.thousand_courses.other.LoginEnums
import com.bagmanovam.thousand_courses.presentation.theme.Blue
import com.bagmanovam.thousand_courses.presentation.theme.Green
import com.bagmanovam.thousand_courses.presentation.theme.Orange
import com.bagmanovam.thousand_courses.presentation.theme.Thousand_coursesTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    onLoginClick: () -> Unit,
    emailState: TextFieldState,
    passwordState: TextFieldState,
    isValid: Boolean,
) {
    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { }
    Scaffold { innerPadding ->
        Column(
            modifier = modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,

            ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp),
                text = stringResource(R.string.enter),
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = 28.sp,
                    lineHeight = 36.sp,
                    textAlign = TextAlign.Start
                )
            )
            LoginItem(
                modifier = Modifier.padding(vertical = 8.dp),
                label = LoginEnums.EMAIL,
                placeholder = LoginEnums.EMAIL_PLACEHOLDER,
                state = emailState,
                isValid = isValid,
                keyBoardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Done,
                    capitalization = KeyboardCapitalization.Words,
                    autoCorrectEnabled = true,
                    showKeyboardOnFocus = true,
                    hintLocales = LocaleList(Locale("ru"))
                )
            )
            LoginItem(
                modifier = Modifier.padding(vertical = 8.dp),
                label = LoginEnums.PASSWORD,
                placeholder = LoginEnums.PASSWORD_PLACEHOLDER,
                state = passwordState,
                keyBoardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done,
                    capitalization = KeyboardCapitalization.Words,
                    showKeyboardOnFocus = true,
                    hintLocales = LocaleList(Locale("ru"))
                )
            )
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp, bottom = 16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Green,
                    disabledContainerColor = Green.copy(alpha = 0.2f),
                    disabledContentColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.2f)
                ),
                enabled = isValid,
                onClick = onLoginClick
            ) {
                Text(
                    text = stringResource(R.string.enter)
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = stringResource(R.string.no_account),
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = MaterialTheme.colorScheme.onPrimary
                        ),
                    )
                    Text(
                        modifier = Modifier
                            .clickable {

                            },
                        text = stringResource(R.string.registration),
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = Green
                        ),
                    )
                }
                Text(
                    modifier = Modifier
                        .clickable {

                        },
                    text = stringResource(R.string.forget_pass),
                    style = MaterialTheme.typography.labelSmall.copy(
                        color = Green
                    ),
                    textAlign = TextAlign.Center
                )
            }

            HorizontalDivider(
                modifier = Modifier.padding(vertical = 32.dp),
                thickness = 1.dp, color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Button(
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Blue
                    ),
                    onClick = {
                        Intent(Intent.ACTION_VIEW, "https://vk.com/".toUri()).also {
                            launcher.launch(it)
                        }
                    }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.vk),
                        contentDescription = "Vk button"
                    )
                }
                Button(
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Orange
                    ),
                    onClick = {
                        Intent(Intent.ACTION_VIEW, "https://ok.ru/".toUri()).also {
                            launcher.launch(it)
                        }
                    }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ok),
                        contentDescription = "Vk button"
                    )
                }
            }

        }
    }
}

@Preview
@Composable
private fun LoginScreenPreview() {
    Thousand_coursesTheme {
        LoginScreen(
            onLoginClick = {},
            emailState = TextFieldState(),
            passwordState = TextFieldState(),
            isValid = true
        )
    }
}