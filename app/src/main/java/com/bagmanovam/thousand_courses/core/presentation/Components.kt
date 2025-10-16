package com.bagmanovam.thousand_courses.core.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.rememberAsyncImagePainter
import com.bagmanovam.thousand_courses.R
import com.bagmanovam.thousand_courses.core.presentation.utils.dateToDisplayableString
import com.bagmanovam.thousand_courses.core.presentation.utils.labelToStringResources
import com.bagmanovam.thousand_courses.domain.model.Course
import com.bagmanovam.thousand_courses.other.LoginEnums
import com.bagmanovam.thousand_courses.presentation.theme.Green
import com.bagmanovam.thousand_courses.presentation.theme.Grey30
import com.bagmanovam.thousand_courses.presentation.theme.White
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

@Composable
fun CourseCard(
    modifier: Modifier = Modifier,
    course: Course,
    onBookMarkClick: (Course) -> Unit,
) {
    val listImages = listOf(
        R.mipmap.it_course_1,
        R.mipmap.it_course_2,
        R.mipmap.it_course_3,
        R.mipmap.it_course_4,
        R.mipmap.it_course_5
    )
    Column(
        modifier = modifier
            .background(
                MaterialTheme.colorScheme.onBackground,
                RoundedCornerShape(18.dp)
            )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Image(
                modifier = Modifier
                    .heightIn(max = 126.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(18.dp)),
                painter = rememberAsyncImagePainter(
                    model = listImages.random(),
                    contentScale = ContentScale.FillWidth
                ),
                contentDescription = "",
                contentScale = ContentScale.FillWidth
            )
            BlurredContainer(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(8.dp)
                    .size(28.dp),
                shape = CircleShape,
                paddingValues = PaddingValues(0.dp)
            ) {
                IconButton(
                    onClick = { onBookMarkClick(course) }
                ) {
                    Icon(
                        modifier = Modifier.size(16.dp),
                        painter = painterResource(R.drawable.bookmark),
                        tint = if (course.hasLike) Green else White,
                        contentDescription = "Add to favourite button"
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomStart)
                    .padding(horizontal = 8.dp)
                    .padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                BlurredContainer(
                    modifier = Modifier
                        .width(46.dp)
                        .height(22.dp)
                ) {
                    Row(
                        modifier = Modifier,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.star_fill),
                            contentDescription = "Rate value",
                            tint = Green
                        )
                        Text(
                            text = course.rate,
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontSize = 12.sp,
                                letterSpacing = 0.4.sp,
                                lineHeight = 14.sp,
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                        )
                    }
                }

                BlurredContainer(
                    modifier = Modifier
                        .height(22.dp),
                ) {
                    Text(
                        text = course.startDate.dateToDisplayableString(),
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontSize = 12.sp,
                            letterSpacing = 0.4.sp,
                            lineHeight = 14.sp,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    )
                }
            }
        }
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, start = 8.dp, end = 8.dp),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                text = course.title,
                style = MaterialTheme.typography.labelLarge.copy(
                    fontSize = 16.sp,
                    letterSpacing = 0.15.sp,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                text = course.text,
                style = MaterialTheme.typography.bodySmall.copy(
                    fontSize = 12.sp,
                    letterSpacing = 0.4.sp,
                    color = MaterialTheme.colorScheme.surface
                )
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = course.price + " ₽",
                    style = MaterialTheme.typography.labelLarge.copy(
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontSize = 16.sp,
                        lineHeight = 18.sp
                    )
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.additional),
                        style = MaterialTheme.typography.labelLarge.copy(
                            color = Green,
                            fontSize = 12.sp,
                            lineHeight = 15.sp
                        )
                    )
                    Icon(
                        painter = painterResource(R.drawable.arrow_right),
                        contentDescription = "Sort by date",
                        tint = Green
                    )
                }
            }
        }
    }
}

@Composable
fun BlurredContainer(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(12.dp),
    paddingValues: PaddingValues = PaddingValues(6.dp, 4.dp, 6.dp, 4.dp),
    content: @Composable () -> Unit,
) {
    Box(
        modifier = modifier.clip(shape),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .clip(shape)
                .blur(32.dp)
                .padding(paddingValues)
        )

        Box(
            modifier = Modifier
                .clip(shape)
                .background(brush = Brush.linearGradient(
                    colors = listOf(
                        Grey30.copy(alpha = 0.3f),
                        Grey30.copy(alpha = 0.6f)
                    )
                ))
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            content()
        }
    }
}

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    text: String,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onValueChanged: (String) -> Unit,
    onFilterClicked: () -> Unit,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TextField(
            modifier = Modifier.weight(1f),
            value = text,
            onValueChange = onValueChanged,
            placeholder = {
                Text(
                    text = "Search courses...",
                    style = MaterialTheme.typography.labelSmall.copy(
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 14.sp,
                        lineHeight = 14.sp
                    )
                )
            },
            keyboardOptions = keyboardOptions,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.onBackground,
                unfocusedContainerColor = MaterialTheme.colorScheme.onBackground,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            leadingIcon = {
                Icon(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .size(32.dp),
                    imageVector = Icons.Outlined.Search,
                    contentDescription = "search notes",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            },
            shape = RoundedCornerShape(28.dp)
        )
        Box(
            modifier = Modifier
                .size(56.dp)
                .background(MaterialTheme.colorScheme.onSurface, CircleShape)
                .clickable {
                    onFilterClicked()
                },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(R.drawable.funnel),
                contentDescription = "Filter button",
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}