/*
 * Copyright (c) 2023. Francisco José Soler Conchello
 */

package ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun TagsRow(list : List<String>) {

    // Utilizar LazyRow para organizar los Tags en filas
    LazyRow(
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier.fillMaxWidth()
    ) {
        items(list) { text ->
            TagElement(text = text)
        }
    }

}