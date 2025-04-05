package com.fabiofpaulo.todolist.ui.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.fabiofpaulo.todolist.enums.TodoPriority
import com.fabiofpaulo.todolist.enums.TodoStatus
import com.fabiofpaulo.todolist.models.Todo
import java.util.UUID

@Composable
fun FormDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: (data: Todo) -> Unit,
    modifier: Modifier = Modifier,
    data: Todo? = null
) {
    var label by remember { mutableStateOf(data?.label ?: "") }

    val priorityOptions = TodoPriority.entries.toTypedArray()
    var priority by remember { mutableStateOf(data?.priority ?: TodoPriority.LOW) }

    val statusOptions = TodoStatus.entries.toTypedArray()
    var status by remember { mutableStateOf(data?.status ?: TodoStatus.PENDING) }

    val priorityScrollState = rememberScrollState()
    val statusScrollState = rememberScrollState()

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
                // TITLE
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,

                    ) {
                    Icon(
                        imageVector = if (data == null) Icons.Default.Add else Icons.Default.Edit,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = if (data == null) "New ToDo" else "Update ToDo",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                //FORM
                Column(
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    OutlinedTextField(
                        value = label,
                        onValueChange = { label = it },
                        label = { Text("Label") }
                    )

                    Column {
                        Text("Priority", style = MaterialTheme.typography.labelLarge)
                        Row(
                            modifier = Modifier
                                .selectableGroup()
                                .horizontalScroll(priorityScrollState),
                            horizontalArrangement = Arrangement.spacedBy(25.dp)
                        ) {
                            priorityOptions.forEach { p ->
                                Row(
                                    modifier = Modifier
                                        .selectable(
                                            selected = (p == priority),
                                            onClick = { priority = p },
                                            role = Role.RadioButton
                                        ),
                                    verticalAlignment = Alignment.CenterVertically,
                                ) {
                                    RadioButton(onClick = null, selected = priority == p)
                                    Text(
                                        text = p.name,
                                        style = MaterialTheme.typography.bodyLarge,
                                        modifier = Modifier.padding(start = 3.dp)
                                    )
                                }

                            }
                        }
                    }

                    Column {
                        Text("Status", style = MaterialTheme.typography.labelLarge)
                        Row(
                            modifier = Modifier
                                .selectableGroup()
                                .horizontalScroll(statusScrollState),
                            horizontalArrangement = Arrangement.spacedBy(25.dp)
                        ) {
                            statusOptions.forEach { s ->
                                Row(
                                    modifier = Modifier
                                        .selectable(
                                            selected = (s == status),
                                            onClick = { status = s },
                                            role = Role.RadioButton
                                        ),
                                    verticalAlignment = Alignment.CenterVertically,
                                ) {
                                    RadioButton(onClick = null, selected = status == s)
                                    Text(
                                        text = s.name,
                                        style = MaterialTheme.typography.bodyLarge,
                                        modifier = Modifier.padding(start = 3.dp)
                                    )
                                }

                            }
                        }
                    }

                }

                Row(
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .fillMaxSize(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = {
                        onDismissRequest()
                    }) {
                        Text("Cancel")
                    }

                    TextButton(onClick = {
                        onConfirmation(
                            Todo(
                                label = label,
                                priority = priority,
                                status = status,
                                id = data?.id
                                    ?: UUID.randomUUID()
                            )
                        )
                    }) {
                        Text("Submit")
                    }
                }
            }
        }
    }
}