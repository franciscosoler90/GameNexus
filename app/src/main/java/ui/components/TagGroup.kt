/*
 * Copyright (c) 2023. Francisco José Soler Conchello
 */

package ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TagGroup(
    tag: List<String>,
    modifier: Modifier = Modifier,
    isLimited: Boolean = false)
{
    val limitedGenres = remember(tag) {
        if (tag.size >= 3) 3 else tag.size
    }

    FlowRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        repeat(if (isLimited) limitedGenres else tag.size) {
            TagChip(name = tag[it])
        }
    }
}

@Composable
fun TagChip(name: String, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(100.dp),
        color = MaterialTheme.colorScheme.tertiary
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onTertiary,
            modifier = Modifier.padding(vertical = 3.dp, horizontal = 12.dp)
        )
    }
}