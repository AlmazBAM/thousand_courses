package com.bagmanovam.thousand_courses.core.presentation.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bagmanovam.thousand_courses.core.presentation.utils.labelToStringResources
import com.bagmanovam.thousand_courses.other.LoginEnums
import com.bagmanovam.thousand_courses.presentation.theme.baseError


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginItem(
    modifier: Modifier = Modifier,
    label: LoginEnums,
    placeholder: LoginEnums,
    keyBoardOptions: KeyboardOptions,
    state: TextFieldState,
    isValid: Boolean = true,
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            text = stringResource(labelToStringResources(label)),
            style = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 16.sp,
                lineHeight = 18.sp,
                textAlign = TextAlign.Start
            )
        )
        BasicTextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp),
            state = state,
            textStyle = MaterialTheme.typography.bodySmall.copy(
                color = if (isValid) MaterialTheme.colorScheme.surface else baseError,
                fontSize = 14.sp
            ),
            lineLimits = TextFieldLineLimits.SingleLine,
            keyboardOptions = keyBoardOptions,
            decorator = { innerTextField ->
                TextFieldDefaults.DecorationBox(
                    value = state.text.toString(),
                    innerTextField = innerTextField,
                    enabled = true,
                    singleLine = true,
                    visualTransformation = VisualTransformation.None,
                    placeholder = {
                        Text(
                            text = stringResource(labelToStringResources(placeholder)),
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                fontSize = 14.sp,
                                lineHeight = 14.sp
                            )
                        )
                    },
                    interactionSource = remember { MutableInteractionSource() },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = MaterialTheme.colorScheme.onBackground,
                        unfocusedContainerColor = MaterialTheme.colorScheme.onBackground,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    shape = RoundedCornerShape(30.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp)
                )

            }
        )
    }
}