package com.kakapo.designsystem.component.userInput

import androidx.annotation.StringRes
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun DefaultTextField(
    query: String,
    onQueryChanged: (String) -> Unit,
    @StringRes hint: Int,
    modifier: Modifier = Modifier,
    keyboardType: KeyboardType = KeyboardType.Text
){
    TextField(
        value = query,
        onValueChange = onQueryChanged,
        maxLines = 1,
        singleLine = true,
        leadingIcon = {
            Spacer(modifier = Modifier.width(16.dp))
        },
        placeholder = {
            Text(
                text = stringResource(id = hint)
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
        ),
        shape = CircleShape,
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        modifier = Modifier
            .width(343.dp)
            .then(modifier)
            .border(width = 1.dp, color = MaterialTheme.colorScheme.outline, shape = CircleShape)
    )
}