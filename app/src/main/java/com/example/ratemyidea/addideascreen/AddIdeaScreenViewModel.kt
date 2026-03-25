package com.example.ratemyidea.addideascreen

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ratemyidea.network.FetchIdeaClient
import com.example.ratemyidea.network.SharedHttpClient
import kotlinx.coroutines.flow.MutableStateFlow
import com.example.ratemyidea.network.Idea
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AddIdeaScreenViewModel (
    private val fetchIdeaClient: FetchIdeaClient = FetchIdeaClient(SharedHttpClient.sharedClient)
): ViewModel() {

    private var internIdea = MutableStateFlow<Idea?>(null)
    val idea = internIdea.asStateFlow()

    init {
        fetchIdea()
    }

    fun fetchIdea() {
        viewModelScope.launch {
            val idea = fetchIdeaClient.fetchIdea()
            internIdea.value = idea
            val a: Int = 0
        }
    }
}