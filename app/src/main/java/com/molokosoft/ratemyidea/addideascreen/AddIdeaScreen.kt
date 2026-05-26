package com.molokosoft.ratemyidea.addideascreen

import android.app.Activity
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import com.molokosoft.ratemyidea.network.model.Idea

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.window.Dialog
import com.molokosoft.ratemyidea.ideasscreen.AddIdeaTile
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import com.molokosoft.ratemyidea.R
import com.molokosoft.ratemyidea.common.DecisionDialog
import com.molokosoft.ratemyidea.common.ErrorMessageDialog
import com.molokosoft.ratemyidea.ui.theme.LocalAppTypography
import com.molokosoft.ratemyidea.ui.theme.LocalColorScheme
import com.molokosoft.ratemyidea.ui.theme.PostIdeaYellow
import com.molokosoft.ratemyidea.billing.CreditCatalog

@Composable
fun GenderDialog (
    onDismissRequest: () -> Unit,
    onGenderChosen: (String) -> Unit
) {
    val colors = LocalColorScheme.current
    val typography = LocalAppTypography.current

    Dialog (
        onDismissRequest = {}
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
                .heightIn(225.dp, 275.dp)
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
                modifier = Modifier
                    .padding(horizontal = 12.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer (
                    modifier = Modifier
                        .height(32.dp)
                )

                Text (
                    text = "Of which gender are you?",
                    color = colors.tertiary,
                    fontSize = typography.labelLarge.fontSize,
                    textAlign = TextAlign.Center,
                    textDecoration = TextDecoration.Underline
                )

                Spacer (
                    modifier = Modifier
                        .height(16.dp)
                )

                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    TextButton (
                        modifier = Modifier
                            .border (
                                width = 1.dp,
                                color = Color.Black,
                                shape = RoundedCornerShape(12.dp)
                            )
                            .background (
                                color = Color.White,
                                shape = RoundedCornerShape(12.dp)
                            )
                            .weight(1f),
                        onClick = {
                            onGenderChosen("m")
                        }
                    ) {
                        Text (
                            text = "m",
                            color = colors.tertiary,
                            fontSize = typography.titleMedium.fontSize
                        )
                    }

                    Spacer (
                        modifier = Modifier
                            .width(1.dp)
                    )

                    TextButton (
                        modifier = Modifier
                            .border (
                                width = 1.dp,
                                color = Color.Black,
                                shape = RoundedCornerShape(12.dp)
                            )
                            .background (
                                color = Color.White,
                                shape = RoundedCornerShape(12.dp)
                            )
                            .weight(1f),
                        onClick = {
                            onGenderChosen("f")
                        }
                    ) {
                        Text (
                            text = "f",
                            color = colors.tertiary,
                            fontSize = typography.titleMedium.fontSize
                        )
                    }
                }
            }
        }
    }
}
@Composable
fun AgeBracketDialog (
    onDismissRequest: () -> Unit,
    onAgeBracketChosen: (String) -> Unit
) {
    val colors = LocalColorScheme.current
    val typography = LocalAppTypography.current

    Dialog (
        onDismissRequest = {

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
                .heightIn(225.dp, 275.dp)
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
                modifier = Modifier
                    .padding(horizontal = 12.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer (
                    modifier = Modifier
                        .height(32.dp)
                )

                Text (
                    text = "What age bracket are you in?",
                    color = colors.tertiary,
                    fontSize = typography.labelLarge.fontSize,
                    textAlign = TextAlign.Center,
                    textDecoration = TextDecoration.Underline
                )

                Spacer (
                    modifier = Modifier
                        .height(16.dp)
                )

                Column (
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    Row (
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        TextButton (
                            modifier = Modifier
                                .border (
                                    width = 1.dp,
                                    color = Color.Black,
                                    shape = RoundedCornerShape(12.dp)
                                )
                                .background (
                                    color = Color.White,
                                    shape = RoundedCornerShape(12.dp)
                                )
                                .weight(1f),
                            onClick = {
                                onAgeBracketChosen("18-24")
                            }
                        ) {
                            Text (
                                text = "18-24",
                                color = colors.tertiary,
                                fontSize = typography.titleMedium.fontSize
                            )
                        }

                        Spacer (
                            modifier = Modifier
                                .width(1.dp)
                        )

                        TextButton (
                            modifier = Modifier
                                .border (
                                    width = 1.dp,
                                    color = Color.Black,
                                    shape = RoundedCornerShape(12.dp)
                                )
                                .background (
                                    color = Color.White,
                                    shape = RoundedCornerShape(12.dp)
                                )
                                .weight(1f),
                            onClick = {
                                onAgeBracketChosen("25-34")
                            }
                        ) {
                            Text (
                                text = "25-34",
                                color = colors.tertiary,
                                fontSize = typography.titleMedium.fontSize
                            )
                        }

                        Spacer (
                            modifier = Modifier
                                .width(1.dp)
                        )

                        TextButton (
                            modifier = Modifier
                                .border (
                                    width = 1.dp,
                                    color = Color.Black,
                                    shape = RoundedCornerShape(12.dp)
                                )
                                .background (
                                    color = Color.White,
                                    shape = RoundedCornerShape(12.dp)
                                )
                                .weight(1f),
                            onClick = {
                                onAgeBracketChosen("35-44")
                            }
                        ) {
                            Text (
                                text = "35-44",
                                color = colors.tertiary,
                                fontSize = typography.titleMedium.fontSize
                            )
                        }
                    }

                    Spacer (
                        modifier = Modifier
                            .height(1.dp)
                    )

                    Row (
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        TextButton (
                            modifier = Modifier
                                .border (
                                    width = 1.dp,
                                    color = Color.Black,
                                    shape = RoundedCornerShape(12.dp)
                                )
                                .background (
                                    color = Color.White,
                                    shape = RoundedCornerShape(12.dp)
                                )
                                .weight(1f),
                            onClick = {
                                onAgeBracketChosen("45-54")
                            }
                        ) {
                            Text (
                                text = "45-54",
                                color = colors.tertiary,
                                fontSize = typography.titleMedium.fontSize
                            )
                        }

                        Spacer (
                            modifier = Modifier
                                .width(1.dp)
                        )

                        TextButton (
                            modifier = Modifier
                                .border (
                                    width = 1.dp,
                                    color = Color.Black,
                                    shape = RoundedCornerShape(12.dp)
                                )
                                .background (
                                    color = Color.White,
                                    shape = RoundedCornerShape(12.dp)
                                )
                                .weight(1f),
                            onClick = {
                                onAgeBracketChosen("55-64")
                            }
                        ) {
                            Text (
                                text = "55-64",
                                color = colors.tertiary,
                                fontSize = typography.titleMedium.fontSize
                            )
                        }

                        Spacer (
                            modifier = Modifier
                                .width(1.dp)
                        )

                        TextButton (
                            modifier = Modifier
                                .border (
                                    width = 1.dp,
                                    color = Color.Black,
                                    shape = RoundedCornerShape(12.dp)
                                )
                                .background (
                                    color = Color.White,
                                    shape = RoundedCornerShape(12.dp)
                                )
                                .weight(1f),
                            onClick = {
                                onAgeBracketChosen("65+")
                            }
                        ) {
                            Text (
                                text = "65+",
                                color = colors.tertiary,
                                fontSize = typography.titleMedium.fontSize
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun AddIdeaScreen (
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues = PaddingValues(),
    addIdeaScreenViewModel: IdeaViewModel
) {
    val colors = LocalColorScheme.current
    val typography = LocalAppTypography.current
    val context = LocalContext.current
    val activity = context as Activity

    var showError by remember { mutableStateOf(false) }
    var showInformationForPosting by remember { mutableStateOf(false) }
    var showDialogForAgeBracket by remember { mutableStateOf(false) }
    var showDialogForGender by remember { mutableStateOf(false) }
    var errorTitleAndText by remember { mutableStateOf("" to "") }
    var buttonText by remember { mutableStateOf("Rate My Idea") }
    fun errorInInputData() = addIdeaScreenViewModel.titleText.isBlank() || addIdeaScreenViewModel.descriptionText.isBlank()

    val uploadIdea = uploadIdea@ {
        if (errorInInputData()) {
            errorTitleAndText = "An error has occurred." to "Please fill in a title and a description."
            showError = true
            return@uploadIdea
        }

        val idea = Idea("", addIdeaScreenViewModel.titleText, addIdeaScreenViewModel.descriptionText)
        addIdeaScreenViewModel.updateIdea(idea)
        addIdeaScreenViewModel.clearTitleAndDescription()
        showInformationForPosting = false
    }

    val notificationPermissionLauncher = rememberLauncherForActivityResult (
        contract = ActivityResultContracts.RequestPermission()
    ) {}

    LaunchedEffect(Unit) {
        addIdeaScreenViewModel.ideaInserted.collect {
            if (Build.VERSION.SDK_INT >= 33) {
                notificationPermissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }

    LaunchedEffect(Unit) {
        addIdeaScreenViewModel.ageBracketNeeded.collect {
            showDialogForAgeBracket = it
        }
    }

    LaunchedEffect(Unit) {
        addIdeaScreenViewModel.genderNeeded.collect {
            showDialogForGender = it
        }
    }

    LaunchedEffect(Unit) {
        addIdeaScreenViewModel.postingUnlocked.collect { postingUnlocked ->
            if (!postingUnlocked) {
                showInformationForPosting = true
                return@collect
            }

            uploadIdea()
            showInformationForPosting = false
        }
    }

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues)
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (showError) {
            ErrorMessageDialog (
                titleText = errorTitleAndText.first,
                descriptionText = errorTitleAndText.second,
                onDismissRequest = {
                    showError = false
                }
            )
        }

        if (showInformationForPosting) {
            if (addIdeaScreenViewModel.ideasToRate() - addIdeaScreenViewModel.ideasRated() > 0) {
                DecisionDialog (
                    titleText = "You're almost there!",
                    descriptionText = "Rate ${addIdeaScreenViewModel.ideasToRate() - addIdeaScreenViewModel.ideasRated()} more ideas to unlock posting your own — or skip ahead for mere $1.99.",
                    confirmTextButton = "Post Idea",
                    dismissTextButton = "Cancel",
                    onConfirmRequest = {
                        addIdeaScreenViewModel.purchaseRateMyIdeaCredits(activity)
                        showInformationForPosting = false
                    },
                    onDismissRequest = {
                        showInformationForPosting = false
                    }
                )
            } else {
                DecisionDialog (
                    titleText = "You're almost there!",
                    descriptionText = "Post your idea for free after watching a short ad - it helps to keep this app for free for you.",
                    confirmTextButton = "Post Idea",
                    dismissTextButton = "Cancel",
                    onConfirmRequest = {
                        addIdeaScreenViewModel.checkPostingUnlocked(activity)
                        showInformationForPosting = false
                    },
                    onDismissRequest = {
                        showInformationForPosting = false
                    }
                )
            }
        }

        if (showDialogForAgeBracket) {
            AgeBracketDialog (
                onDismissRequest = {
                    showDialogForAgeBracket = false
                },
                onAgeBracketChosen = {
                    showDialogForAgeBracket = false
                    addIdeaScreenViewModel.setAgeBracket(ageBracket = it)
                    addIdeaScreenViewModel.checkPostingUnlocked(activity)
                }
            )
        }

        if (showDialogForGender) {
            GenderDialog (
                onDismissRequest = {
                    showDialogForGender = false
                },
                onGenderChosen = {
                    showDialogForGender = false
                    addIdeaScreenViewModel.setGender(gender = it)
                    addIdeaScreenViewModel.checkPostingUnlocked(activity)
                }
            )
        }

        Spacer (
            modifier = Modifier
                .height(32.dp)
        )

        AddIdeaTile (
            modifier = Modifier
                .weight(1f),
            titleText = addIdeaScreenViewModel.titleText,
            descriptionText = addIdeaScreenViewModel.descriptionText,
            onTitleChange = {
                addIdeaScreenViewModel.updateTitleText(it)
            },
            onDescriptionChange = {
                addIdeaScreenViewModel.updateDescriptionText(it)
            }
        )

        Spacer (
            modifier = Modifier
                .height(32.dp)
        )

        Box (
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background (
                    color = PostIdeaYellow,
                    shape = RoundedCornerShape(24.dp)
                )
                .border (
                    width = 1.dp,
                    shape = RoundedCornerShape(24.dp),
                    color = colors.tertiary
                )
                .clip (
                    RoundedCornerShape(24.dp)
                )
                .clickable (
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {
                    if (errorInInputData()) {
                        errorTitleAndText = "An error has occurred." to "Please fill in a title and a description."
                        showError = true
                        return@clickable
                    }

                    addIdeaScreenViewModel.checkPostingUnlocked(activity)
                },
            contentAlignment = Alignment.Center
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

            Text (
                text = buttonText,
                fontSize = typography.labelLarge.fontSize,
                fontWeight = typography.labelLarge.fontWeight
            )
        }

        Spacer (
            modifier = Modifier
                .height(32.dp)
        )
    }
}