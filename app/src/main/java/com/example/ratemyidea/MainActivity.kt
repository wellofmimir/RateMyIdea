package com.example.ratemyidea

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier


import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.ratemyidea.common.HeaderSection
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import com.example.ratemyidea.addideascreen.AddIdeaScreen

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

import com.example.ratemyidea.common.NavigationBarSection
import com.example.ratemyidea.ideasscreen.IdeaScreen
import com.example.ratemyidea.ui.theme.LocalColorScheme
import com.example.ratemyidea.addideascreen.AddIdeaScreenViewModel

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        val controller = WindowInsetsControllerCompat(window, window.decorView)
        controller.hide(WindowInsetsCompat.Type.navigationBars())
        controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE

        enableEdgeToEdge()
        setContent {

            val addIdeaScreenViewModel: AddIdeaScreenViewModel = viewModel (
                factory = object: ViewModelProvider.Factory {
                    override fun<T: ViewModel> create(modelClass: Class<T>): T {
                        return AddIdeaScreenViewModel() as T
                    }
                }
            )

            val color = LocalColorScheme.current
            var selectedIndex by remember { mutableStateOf(0) }

            Scaffold (
                topBar = {
                    HeaderSection (
                        modifier = Modifier
                            .padding(top = 24.dp)
                    )
                },
                bottomBar = {
                    NavigationBarSection (
                        selectedIndex = selectedIndex,
                        onItemSelected = {
                            selectedIndex = it
                        }
                    )
                }
            ) { paddingValues ->
                Box (
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color.background),
                    contentAlignment = Alignment.TopCenter
                ) {
                    when (selectedIndex) {
                        0 -> {
                            IdeaScreen (
                                modifier = Modifier
                                    .padding(paddingValues),
                                paddingValues = paddingValues,
                                addIdeaScreenViewModel = addIdeaScreenViewModel
                            )
                        }

                        1 -> {
                            AddIdeaScreen (
                                paddingValues = paddingValues
                            )
                        }
                    }
                }
            }
        }
    }
}

