/*
 * Copyright (c) 2023. Francisco José Soler Conchello
 */

package ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Tags(text: String, list : List<String>) {

    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {

        TextElement(text = text)
        Spacer(modifier = Modifier.padding(4.dp))
        TagsRow(list = list.map { it })
    }
}