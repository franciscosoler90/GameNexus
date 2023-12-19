/*
 * Copyright (c) 2023. Francisco José Soler Conchello
 */

package ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun DateComponent(title: String, date: String) {
    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {

        TextElement(text = title)

        Text(
            text = date,
            style = MaterialTheme.typography.titleSmall
        )
    }
}