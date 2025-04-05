package com.fabiofpaulo.simplelazycolumn.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Form(onAdd: (name: String) -> Unit, modifier: Modifier = Modifier) {
    var name by remember { mutableStateOf("") }
    Row(
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            onValueChange = { name = it },
            label = { Text("Name") },
            value = name,
            modifier = Modifier.weight(1f)
        )
        IconButton(onClick = {
            onAdd(name)
        }) {
            Icon(imageVector = Icons.Filled.Add, contentDescription = null)
        }
    }
}