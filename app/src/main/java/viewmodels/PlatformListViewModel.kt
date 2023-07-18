/*
 * Copyright (c) 2023. Francisco José Soler Conchello
 */

package viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import api.API
import entity.Platform

class PlatformListViewModel: ViewModel() {

    var platformList: List<Platform> by mutableStateOf(listOf())
    var next: String? by mutableStateOf("")

    init {
        loadData()
    }

    private fun loadData() {
        API.loadPlatforms(1,{ result ->
            platformList = result.result }, { println("Error") })
    }

    fun updatePage(page : Int){
        API.loadPlatforms(page,{ result ->
            this.platformList = result.result
            this.next = result.next
        }, {
            println("Error")
        })
    }
}