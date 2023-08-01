package com.kakapo.designsystem.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage

@Composable
fun DynamicAsyncImage(
    imageUrl: String,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    placeholder: Painter? = null,
    contentScale: ContentScale = ContentScale.Fit
) {
    AsyncImage(
        placeholder = placeholder,
        model = imageUrl,
        contentDescription = contentDescription,
        modifier = modifier,
        error = placeholder,
        contentScale = contentScale
    )
}
