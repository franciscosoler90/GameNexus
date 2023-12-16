/*
 * Copyright (c) 2023. Francisco José Soler Conchello
 */

package viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import api.API
import entidades.Platform

class PlatformListViewModel: ViewModel() {

    var platformList: List<Platform> by mutableStateOf(listOf())

    init {
        loadData()
    }

    private fun loadData() {
        API.loadPlatforms({ result ->
            // Convierte la lista de PlatformParent a una lista de Platform utilizando flatMap
            platformList = result.result.flatMap { platformParent ->
                platformParent.platforms // Accede a la lista de plataformas dentro de PlatformParent
            } }, { println("Error") })
    }


}