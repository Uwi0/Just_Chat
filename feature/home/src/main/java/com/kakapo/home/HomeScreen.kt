package com.kakapo.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.kakapo.designsystem.component.DynamicAsyncImage

@Composable
internal fun HomeRoute(onChatItemClick: (String) -> Unit) {
    HomeScreen(onChatItemClick = onChatItemClick)
}

@Composable
internal fun HomeScreen(onChatItemClick: (String) -> Unit) {
    LazyColumn(
        content = {
            items(10) {
                ListItem(
                    modifier = Modifier.clickable { onChatItemClick.invoke("") },
                    headlineContent = {
                        Text(
                            text = "This A Group",
                            style = MaterialTheme.typography.titleMedium
                        )
                    },
                    leadingContent = {
                        DynamicAsyncImage(
                            imageUrl = "",
                            contentDescription = null,
                            placeholder = painterResource(id = R.drawable.sample_image_error),
                            modifier = Modifier
                                .size(48.dp)
                                .clip(CircleShape)
                        )
                    },
                    supportingContent = {
                        Text(
                            text = "you: this is message",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                )
            }
        }
    )
}