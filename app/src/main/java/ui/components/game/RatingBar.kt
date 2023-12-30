/*
 * Copyright (c) 2023. Francisco José Soler Conchello
 */

package ui.components.game

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.SolidColor

@Composable
fun RatingBar(
    rating: String,
    modifier: Modifier = Modifier,
    color: Color = Yellow
) {
    if(rating.isNotEmpty()){

        val newRating = rating.toFloat()

        Row(modifier = modifier.wrapContentSize()) {
            (1..5).forEach { step ->
                val stepRating = when {
                    newRating > step -> 1f
                    step.rem(newRating) < 1 -> newRating - (step - 1f)
                    else -> 0f
                }
                RatingStar(stepRating, color)
            }
        }
    }
}

@Composable
private fun RatingStar(
    rating: Float,
    ratingColor: Color = Yellow,
    backgroundColor: Color = MaterialTheme.colorScheme.secondary
) {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxHeight()
            .aspectRatio(1f)
            .clip(starShape)
    ) {
        Canvas(modifier = Modifier.size(maxHeight)) {
            drawRect(
                brush = SolidColor(backgroundColor),
                size = Size(
                    height = size.height * 1.4f,
                    width = size.width * 1.4f
                ),
                topLeft = Offset(
                    x = -(size.width * 0.1f),
                    y = -(size.height * 0.1f)
                )
            )
            if (rating > 0) {
                drawRect(
                    brush = SolidColor(ratingColor),
                    size = Size(
                        height = size.height * 1.1f,
                        width = size.width * rating
                    )
                )
            }
        }
    }
}

private val starShape = GenericShape { size, _ ->
    addPath(starPath(size.height))
}

private val starPath = { size: Float ->
    Path().apply {
        val outerRadius: Float = size / 1.8f
        val innerRadius: Double = outerRadius / 2.5
        var rot: Double = Math.PI / 2 * 3
        val cx: Float = size / 2
        val cy: Float = size / 20 * 11
        var x: Float
        var y: Float
        val step = Math.PI / 5

        moveTo(cx, cy - outerRadius)
        repeat(5) {
            x = (cx + kotlin.math.cos(rot) * outerRadius).toFloat()
            y = (cy + kotlin.math.sin(rot) * outerRadius).toFloat()
            lineTo(x, y)
            rot += step

            x = (cx + kotlin.math.cos(rot) * innerRadius).toFloat()
            y = (cy + kotlin.math.sin(rot) * innerRadius).toFloat()
            lineTo(x, y)
            rot += step
        }
        close()
    }
}
