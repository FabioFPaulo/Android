package com.fabiofpaulo.todolist.models.view

import androidx.lifecycle.ViewModel
import com.fabiofpaulo.todolist.models.Todo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.UUID

class MainLayoutViewModel: ViewModel() {
    private val _removeDialog = MutableStateFlow<UUID?>(null)
    var removeDialog: StateFlow<UUID?> = _removeDialog

    fun dismissRemoveDialog() {
        _removeDialog.value = null
    }

    fun openRemoveDialog(todoId: UUID){
        _removeDialog.value = todoId
    }

    private val _editDialog = MutableStateFlow<Boolean>(false)
    private val _editDialogData = MutableStateFlow<Todo?>(null)
    var editDialog: StateFlow<Boolean> = _editDialog
    var editDialogData: StateFlow<Todo?> = _editDialogData

    fun openEditDialog(data: Todo? = null){
        _editDialogData.value = data
        _editDialog.value = true
    }

    fun dismissEditDialog(){
        _editDialogData.value = null
        _editDialog.value = false
    }
}