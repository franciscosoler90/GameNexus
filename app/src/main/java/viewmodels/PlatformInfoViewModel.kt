/*
 * Copyright (c) 2023. Francisco José Soler Conchello
 */

package viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import api.API

class PlatformInfoViewModel(private val platformId : Int): ViewModel() {

    var platformName by mutableStateOf("")

    init {
        loadData()
    }

    private fun loadData() {

        API.loadGamesPlatform(platformId,{ platform ->
            platformName = platform.name
        }, {
            println("Error")
        })

    }

}