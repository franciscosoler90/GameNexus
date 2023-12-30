/*
 * Copyright (c) 2023. Francisco José Soler Conchello
 */

package ui.components.game

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ui.components.search.TagChip

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TagGroup(
    tag: List<String>,
    modifier: Modifier = Modifier,
    isLimited: Boolean = false
) {
    val limitedTags = remember(tag, isLimited) {
        if (isLimited) tag.take(2).sorted() else tag.sorted()
    }

    FlowRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        limitedTags.forEach { tagName ->
            TagChip(name = tagName)
        }
    }
}