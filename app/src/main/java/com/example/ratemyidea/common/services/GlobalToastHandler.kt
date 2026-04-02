package com.example.ratemyidea.common.services

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import com.example.ratemyidea.addideascreen.IdeaViewModel
import android.widget.Toast

@Composable
fun GlobalToastHandler (
    addIdeaScreenViewModel: IdeaViewModel
) {
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        addIdeaScreenViewModel.toastEvent.collect { message ->
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        }
    }
}