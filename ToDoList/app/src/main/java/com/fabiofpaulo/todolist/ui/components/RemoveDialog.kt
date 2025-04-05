package com.fabiofpaulo.todolist.ui.components


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun RemoveDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String, modifier: Modifier = Modifier
) {
    Dialog(onDismissRequest = onDismissRequest) {
        Card(
            modifier = modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(30.dp, 15.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,

                    ) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.error
                    )
                    Text(
                        dialogTitle,
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.error
                    )
                }
                Text(
                    text = dialogText,
                    modifier = Modifier
                        .wrapContentSize(Alignment.Center),
                    textAlign = TextAlign.Start
                )
                Row(
                    modifier = Modifier.padding(top= 20.dp).fillMaxSize(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = {
                        onDismissRequest()
                    }) {
                        Text("Cancel")
                    }

                    TextButton(onClick = {
                        onConfirmation()
                    }) {
                        Text("Remove")
                    }
                }
            }
        }
    }
}