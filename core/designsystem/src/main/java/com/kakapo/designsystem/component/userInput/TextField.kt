package com.kakapo.designsystem.component.userInput

import androidx.annotation.StringRes
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.kakapo.designsystem.R

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextInputPassword(
    query: String,
    onQueryChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
    hint: Int = R.string.password
) {
    var isVisible by remember { mutableStateOf(false) }
    TextField(
        value = query,
        onValueChange = onQueryChanged,
        singleLine = true,
        trailingIcon = {
            val icon = if (isVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
            val description = if (isVisible) R.string.hide_password else R.string.show_password
            IconButton(
                onClick = { isVisible = !isVisible },
                content = {
                    Icon(imageVector = icon, contentDescription = stringResource(id = description))
                }
            )
        },
        placeholder = {
            Text(
                text = stringResource(id = hint)
            )
        },
        visualTransformation = if (isVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
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