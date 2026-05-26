package com.molokosoft.ratemyidea.myideasscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.foundation.layout.Row
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle

import com.molokosoft.ratemyidea.R
import com.molokosoft.ratemyidea.network.model.Idea
import com.molokosoft.ratemyidea.ui.theme.LocalAppTypography
import com.molokosoft.ratemyidea.ui.theme.LocalColorScheme
import com.molokosoft.ratemyidea.ui.theme.PostIdeaYellow

@Composable
fun InsightsDialog (
    idea: Idea,
    onDismissRequest: () -> Unit
) {
    val colors = LocalColorScheme.current
    val typography = LocalAppTypography.current

    Dialog (
        onDismissRequest = {
            onDismissRequest()
        }
    ) {
        Box (
            modifier = Modifier
                .border (
                    width = 1.dp,
                    color = Color.Black,
                    shape = RoundedCornerShape(12.dp)
                )
                .background (
                    color = PostIdeaYellow,
                    shape = RoundedCornerShape(12.dp)
                )
                .clip (
                    RoundedCornerShape(12.dp)
                )
                .fillMaxHeight(0.75f)
                .fillMaxWidth()
                .clickable () {
                    onDismissRequest()
                }
        ) {
            Image (
                painter = painterResource(id = R.drawable.background),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .matchParentSize()
            )

            Box (
                modifier = Modifier
                    .matchParentSize()
                    .background (
                        PostIdeaYellow.copy(alpha = 0.4f)
                    )
            )

            Column (
            ) {
                Text (
                    text = "Gender",
                    color = colors.tertiary,
                    fontSize = typography.labelLarge.fontSize,
                    fontWeight = typography.titleMedium.fontWeight,
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier
                        .padding(start = 12.dp, top = 12.dp)
                )

                Row (
                    modifier = Modifier
                        .padding(top = 12.dp)
                        .weight(1f),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text (
                        text = buildAnnotatedString {
                            withStyle (
                                style = SpanStyle(
                                    textDecoration = TextDecoration.Underline
                                )
                            ) {
                                append("Male:\n")
                            }
                            append(idea.male.toString())
                        },
                        color = colors.tertiary,
                        fontSize = typography.labelMedium.fontSize,
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 24.dp)
                    )

                    Text (
                        text = buildAnnotatedString {
                            withStyle (
                                style = SpanStyle(
                                    textDecoration = TextDecoration.Underline
                                )
                            ) {
                                append("Female:\n")
                            }
                            append(idea.female.toString())
                        },
                        color = colors.tertiary,
                        fontSize = typography.labelMedium.fontSize,
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 24.dp)
                    )
                }

                Text (
                    text = "Age-Brackets",
                    color = colors.tertiary,
                    fontSize = typography.labelLarge.fontSize,
                    fontWeight = typography.titleMedium.fontWeight,
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier
                        .padding(start = 12.dp, top = 12.dp)
                )

                Row (
                    modifier = Modifier
                        .padding(top = 12.dp)
                        .weight(1f),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text (
                        text = buildAnnotatedString {
                            withStyle (
                                style = SpanStyle(
                                    textDecoration = TextDecoration.Underline
                                )
                            ) {
                                append("Male 18-24:\n")
                            }
                            append(idea.maleEighteenToTwentyFour.toString())
                        },
                        color = colors.tertiary,
                        fontSize = typography.labelMedium.fontSize,
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 24.dp)
                    )

                    Text (
                        text = buildAnnotatedString {
                            withStyle (
                                style = SpanStyle(
                                    textDecoration = TextDecoration.Underline
                                )
                            ) {
                                append("Female 18-24:\n")
                            }
                            append(idea.femaleEighteenToTwentyFour.toString())
                        },
                        color = colors.tertiary,
                        fontSize = typography.labelMedium.fontSize,
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 24.dp)
                    )
                }

                Row (
                    modifier = Modifier
                        .padding(top = 12.dp)
                        .weight(1f),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text (
                        text = buildAnnotatedString {
                            withStyle (
                                style = SpanStyle(
                                    textDecoration = TextDecoration.Underline
                                )
                            ) {
                                append("Male 25-34:\n")
                            }
                            append(idea.maleTwentyFiveToThirtyFour.toString())
                        },
                        color = colors.tertiary,
                        fontSize = typography.labelMedium.fontSize,
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 24.dp)
                    )

                    Text (
                        text = buildAnnotatedString {
                            withStyle (
                                style = SpanStyle(
                                    textDecoration = TextDecoration.Underline
                                )
                            ) {
                                append("Female 25-34:\n")
                            }
                            append(idea.femaleTwentyFiveToThirtyFour.toString())
                        },
                        color = colors.tertiary,
                        fontSize = typography.labelMedium.fontSize,
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 24.dp)
                    )
                }

                Row (
                    modifier = Modifier
                        .padding(top = 12.dp)
                        .weight(1f),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text (
                        text = buildAnnotatedString {
                            withStyle (
                                style = SpanStyle(
                                    textDecoration = TextDecoration.Underline
                                )
                            ) {
                                append("Male 34-45:\n")
                            }
                            append(idea.maleThirtyFiveToFortyFour.toString())
                        },
                        color = colors.tertiary,
                        fontSize = typography.labelMedium.fontSize,
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 24.dp)
                    )

                    Text (
                        text = buildAnnotatedString {
                            withStyle (
                                style = SpanStyle(
                                    textDecoration = TextDecoration.Underline
                                )
                            ) {
                                append("Female 35-44:\n")
                            }
                            append(idea.femaleThirtyFiveToFortyFour.toString())
                        },
                        color = colors.tertiary,
                        fontSize = typography.labelMedium.fontSize,
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 24.dp)
                    )
                }

                Row (
                    modifier = Modifier
                        .padding(top = 12.dp)
                        .weight(1f),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text (
                        text = buildAnnotatedString {
                            withStyle (
                                style = SpanStyle(
                                    textDecoration = TextDecoration.Underline
                                )
                            ) {
                                append("Male 45-54:\n")
                            }
                            append(idea.maleFortyFiveToFiftyFour.toString())
                        },
                        color = colors.tertiary,
                        fontSize = typography.labelMedium.fontSize,
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 24.dp)
                    )

                    Text (
                        text = buildAnnotatedString {
                            withStyle (
                                style = SpanStyle(
                                    textDecoration = TextDecoration.Underline
                                )
                            ) {
                                append("Female 45-54:\n")
                            }
                            append(idea.femaleFortyFiveToFiftyFour.toString())
                        },
                        color = colors.tertiary,
                        fontSize = typography.labelMedium.fontSize,
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 24.dp)
                    )
                }

                Row (
                    modifier = Modifier
                        .padding(top = 12.dp)
                        .weight(1f),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text (
                        text = buildAnnotatedString {
                            withStyle (
                                style = SpanStyle(
                                    textDecoration = TextDecoration.Underline
                                )
                            ) {
                                append("Male 55-64:\n")
                            }
                            append(idea.maleFiftyFiveToSixtyFour.toString())
                        },
                        color = colors.tertiary,
                        fontSize = typography.labelMedium.fontSize,
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 24.dp)
                    )

                    Text (
                        text = buildAnnotatedString {
                            withStyle (
                                style = SpanStyle(
                                    textDecoration = TextDecoration.Underline
                                )
                            ) {
                                append("Female 55-64:\n")
                            }
                            append(idea.femaleFiftyFiveToSixtyFour.toString())
                        },
                        color = colors.tertiary,
                        fontSize = typography.labelMedium.fontSize,
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 24.dp)
                    )
                }

                Row (
                    modifier = Modifier
                        .padding(top = 12.dp)
                        .weight(1f),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text (
                        text = buildAnnotatedString {
                            withStyle (
                                style = SpanStyle(
                                    textDecoration = TextDecoration.Underline
                                )
                            ) {
                                append("Male 65+:\n")
                            }
                            append(idea.maleSixtyFivePlus.toString())
                        },
                        color = colors.tertiary,
                        fontSize = typography.labelMedium.fontSize,
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 24.dp)
                    )

                    Text (
                        text = buildAnnotatedString {
                            withStyle (
                                style = SpanStyle(
                                    textDecoration = TextDecoration.Underline
                                )
                            ) {
                                append("Female 65+:\n")
                            }
                            append(idea.femaleSixtyFivePlus.toString())
                        },
                        color = colors.tertiary,
                        fontSize = typography.labelMedium.fontSize,
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 24.dp)
                    )
                }
            }
        }
    }
}