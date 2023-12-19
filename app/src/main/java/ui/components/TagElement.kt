/*
 * Copyright (c) 2023. Francisco José Soler Conchello
 */

package ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TagElement(text : String) {

    Surface(
        modifier = Modifier.padding(horizontal = 0.dp),
        shadowElevation = 2.dp,
        shape = CircleShape,
        color = MaterialTheme.colorScheme.secondaryContainer
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(8.dp),
            style = MaterialTheme.typography.titleSmall,
            maxLines = 1,
            color = MaterialTheme.colorScheme.onSecondaryContainer)
    }

    Spacer(modifier = Modifier.padding(4.dp))
}