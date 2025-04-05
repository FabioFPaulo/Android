package com.fabiofpaulo.todolist.models

import com.fabiofpaulo.todolist.enums.TodoPriority
import com.fabiofpaulo.todolist.enums.TodoStatus
import java.util.UUID

data class Todo (
    val id: UUID = UUID.randomUUID(),
    val label: String,
    val status: TodoStatus,
    val priority: TodoPriority
)

