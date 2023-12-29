/*
 * Copyright (c) 2023. Francisco José Soler Conchello
 */

package ui.components.search

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import interfaces.GameListInterface

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun searchBar(gameListCallbacks: GameListInterface, title : String) {

    var query by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }

    SearchBar(
        query = query,
        onQueryChange = {query = it},
        onSearch = {active = false},
        active = active,
        onActiveChange = {active = it},
        placeholder = {
            Text(text = "Buscar en $title")
        },
        leadingIcon = {
            IconButton(onClick = {
                gameListCallbacks.backToPlatforms()
            }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                    tint = MaterialTheme.colorScheme.onSurface,
                    contentDescription = null
                )
            }
        },
        trailingIcon = {
            IconButton(onClick = {}, enabled = query.isNotEmpty()) {
                Icon(
                    imageVector = Icons.Rounded.Search,
                    tint = MaterialTheme.colorScheme.onSurface,
                    contentDescription = null
                )
            }
        },
        content = {},
        tonalElevation = 0.dp,
        modifier = Modifier.fillMaxWidth(),
        shape = SearchBarDefaults.fullScreenShape,
        colors = SearchBarDefaults.colors()
    )
}