package com.kakapo.designsystem.component

import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.layout.LayoutModifier
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.MeasureResult
import androidx.compose.ui.layout.MeasureScope
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp

data class BaseLineHeightModifier(
    val heightFromBaseLine: Dp
) : LayoutModifier{
    override fun MeasureScope.measure(
        measurable: Measurable,
        constraints: Constraints
    ): MeasureResult {

        val textPlaceAble = measurable.measure(constraints)
        val firstBaseline = textPlaceAble[FirstBaseline]
        val lastBaseline = textPlaceAble[LastBaseline]

        val height = heightFromBaseLine.roundToPx() + lastBaseline - firstBaseline
        return layout(constraints.maxWidth, height){
            val topY = heightFromBaseLine.roundToPx() - firstBaseline
            textPlaceAble.place(0, topY)
        }
    }
}

fun Modifier.baselineHeight(heightFromBaseline: Dp): Modifier =
    this.then(BaseLineHeightModifier(heightFromBaseline))