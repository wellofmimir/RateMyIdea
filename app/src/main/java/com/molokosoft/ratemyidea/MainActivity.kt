package com.molokosoft.ratemyidea

import android.annotation.SuppressLint
import android.app.Activity
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier


import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.molokosoft.ratemyidea.common.HeaderSection
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.delay
import androidx.compose.ui.window.Dialog
import com.molokosoft.ratemyidea.addideascreen.AddIdeaScreen
import com.molokosoft.ratemyidea.aboutscreen.AboutScreen

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

import com.molokosoft.ratemyidea.common.NavigationBarSection
import com.molokosoft.ratemyidea.ideasscreen.IdeaScreen
import com.molokosoft.ratemyidea.ui.theme.LocalColorScheme
import com.molokosoft.ratemyidea.addideascreen.IdeaViewModel
import com.molokosoft.ratemyidea.advertisement.RewardedAdManager
import com.molokosoft.ratemyidea.common.services.GlobalToastHandler
import com.molokosoft.ratemyidea.database.Database
import com.molokosoft.ratemyidea.myideasscreen.MyIdeasScreen
import com.molokosoft.ratemyidea.network.IdeaClient
import com.molokosoft.ratemyidea.network.SharedHttpClient
import com.molokosoft.ratemyidea.repositories.IdeaRepository
import com.molokosoft.ratemyidea.securepreferences.SecurePreferences
import com.molokosoft.ratemyidea.billing.BillingManager
import com.molokosoft.ratemyidea.aboutscreen.PrivacyPolicy
import com.molokosoft.ratemyidea.aboutscreen.TermsOfService
import com.molokosoft.ratemyidea.billing.CreditCatalog

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.mutableIntStateOf

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
            val context = LocalContext.current

            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(NotificationChannel("ideas_rating", "ideas_rating", NotificationManager.IMPORTANCE_HIGH))

            val addIdeaScreenViewModel: IdeaViewModel = viewModel (
                factory = object: ViewModelProvider.Factory {
                    override fun<T: ViewModel> create(modelClass: Class<T>): T {

                        val database = Database(context)
                        val ideaClient = IdeaClient(SharedHttpClient.sharedClient)
                        val securePreferences = SecurePreferences(context)

                        val rewardedAdManager = RewardedAdManager(context)
                        rewardedAdManager.load("ca-app-pub-8967992746965159/4997477708")

                        val billingManager = BillingManager(context)
                        val ideaRepository = IdeaRepository(ideaClient, database, billingManager, rewardedAdManager, securePreferences)

                        return IdeaViewModel(ideaRepository) as T
                    }
                }
            )

            GlobalToastHandler (
                addIdeaScreenViewModel = addIdeaScreenViewModel
            )

            val color = LocalColorScheme.current
            var selectedIndex by remember { mutableIntStateOf(0) }
            val myIdeas by addIdeaScreenViewModel.userIdeas.collectAsState()
            var deeperInsightsUnlocked by remember { mutableStateOf(addIdeaScreenViewModel.deeperInsightsUnlocked()) }
            val firstIdeaInserted by remember { mutableStateOf(addIdeaScreenViewModel.alreadyInsertedAnIdea()) }

            LaunchedEffect(Unit) {
                addIdeaScreenViewModel.insertFirstIdeaEvent.collect {
                    selectedIndex = 1
                }
            }

            LaunchedEffect(Unit) {
                addIdeaScreenViewModel.firstIdeaInsertedEvent.collect {
                    selectedIndex = 2
                }
            }

            LaunchedEffect(Unit) {
                addIdeaScreenViewModel.fetchIdea()
                addIdeaScreenViewModel.fetchIdeasForUser()
                delay(500)
            }

            LaunchedEffect(selectedIndex) {
                if (selectedIndex == 0)
                    addIdeaScreenViewModel.fetchIdea()

                else if (selectedIndex == 2)
                    addIdeaScreenViewModel.fetchIdeasForUser()
            }

            LaunchedEffect(Unit) {
                CreditCatalog.deeperInsightsPurchased.collect {
                    deeperInsightsUnlocked = it
                }
            }

            Scaffold (
                topBar = {
                    HeaderSection (
                        modifier = Modifier
                            .padding(top = 44.dp)
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
                                paddingValues = paddingValues,
                                addIdeaScreenViewModel = addIdeaScreenViewModel
                            )
                        }

                        2 -> {
                            MyIdeasScreen (
                                modifier = Modifier
                                    .padding(top = 32.dp)
                                    .padding(paddingValues),
                                paddingValues = paddingValues,
                                myIdeas,
                                firstIdeaInserted,
                                deeperInsightsUnlocked,
                                onInsightsOfferAccepted = {
                                    val activity = context as Activity
                                    addIdeaScreenViewModel.purchaseDeeperInsights(activity)
                                }
                            )
                        }

                        3 -> {
                            var showPrivacyPolicy by remember { mutableStateOf(false) }
                            var showTermsOfService by remember { mutableStateOf(false) }

                            AboutScreen (
                                modifier = Modifier
                                    .padding(vertical = 32.dp)
                                    .padding(paddingValues),
                                onShowPrivacyPolicy = {
                                    showPrivacyPolicy = true
                                    showTermsOfService = false
                                },
                                onShowTermsOfService = {
                                    showTermsOfService = true
                                    showPrivacyPolicy = false
                                }
                            )

                            if (showPrivacyPolicy) {
                                Dialog (
                                    onDismissRequest = {
                                        showPrivacyPolicy = false
                                    }
                                ) {
                                    PrivacyPolicy()
                                }
                            }

                            if (showTermsOfService) {
                                Dialog (
                                    onDismissRequest = {
                                        showTermsOfService = false
                                    }
                                ) {
                                    TermsOfService()
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

