package com.fabiofpaulo.todolist.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ChipColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.fabiofpaulo.todolist.enums.TodoPriority
import com.fabiofpaulo.todolist.enums.TodoStatus
import com.fabiofpaulo.todolist.models.Todo
import java.util.UUID

@Composable
fun TodoList(data: List<Todo>, onEdit: (id: UUID, data: Todo) -> Unit, onRemove: (id: UUID) -> Unit, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier.padding(top = 20.dp).fillMaxHeight(),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        items(data, key = { it.id }) {
            ListCard(
                label = it.label,
                priority = it.priority,
                status = it.status,
                onRemove = {
                    onRemove(it.id)
                },
                onEdit = {
                    onEdit(it.id, it)
                })
        }
    }
}

@Composable
fun ListCard(
    label: String,
    priority: TodoPriority,
    status: TodoStatus,
    onRemove: () -> Unit,
    onEdit: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )

    ) {
        Row(
            modifier = Modifier
                .padding(vertical = 20.dp, horizontal = 30.dp)
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    label,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(3.dp)
                ) {
                    AssistChip(
                        onClick = {},
                        label = {
                            Text(
                                priority.name,
                                style = MaterialTheme.typography.labelSmall,
                                color = if (priority == TodoPriority.HIGH) Color(0xffffffff) else Color.Unspecified
                            )
                        },
                        colors = AssistChipDefaults.assistChipColors(
                            containerColor = when (priority) {
                                TodoPriority.LOW -> MaterialTheme.colorScheme.surfaceContainer
                                TodoPriority.MEDIUM -> Color(0xffffc107)
                                TodoPriority.HIGH -> MaterialTheme.colorScheme.error
                            }
                        ),
                        border = null
                    )
                    AssistChip(
                        onClick = {},
                        label = {
                            Text(
                                status.name,
                                style = MaterialTheme.typography.labelSmall,
                                color = if (status != TodoStatus.PENDING) Color(0xffffffff) else Color.Unspecified
                            )
                        },
                        border = null,
                        colors = AssistChipDefaults.assistChipColors(
                            containerColor = when (status) {
                                TodoStatus.ONGOING -> Color(0xff0d6efd)
                                TodoStatus.PENDING -> MaterialTheme.colorScheme.surfaceContainer
                                TodoStatus.COMPLETE -> Color(0xff198754)
                            }
                        ),
                    )
                }
            }
            Column {
                IconButton(onClick = onEdit) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = null,
                        tint = Color(0xffffc107)
                    )
                }
                IconButton(onClick = onRemove) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
    }
}