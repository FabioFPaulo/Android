package com.fabiofpaulo.todolist.ui.layouts

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fabiofpaulo.todolist.enums.TodoStatus
import com.fabiofpaulo.todolist.models.view.MainLayoutViewModel
import com.fabiofpaulo.todolist.models.view.TodoViewModel
import com.fabiofpaulo.todolist.ui.components.FormDialog
import com.fabiofpaulo.todolist.ui.components.RemoveDialog
import com.fabiofpaulo.todolist.ui.components.TodoList
import com.fabiofpaulo.todolist.ui.components.TodoTabs
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainLayout(
    modifier: Modifier = Modifier,
    todoViewModel: TodoViewModel = viewModel(),
    mainLayoutViewModel: MainLayoutViewModel = viewModel()
) {


    val filteredItems by todoViewModel.filteredTodos.collectAsStateWithLifecycle()
    val checkList by todoViewModel.checkList.collectAsStateWithLifecycle()

    val removeDialog by mainLayoutViewModel.removeDialog.collectAsStateWithLifecycle()

    val editDialog by mainLayoutViewModel.editDialog.collectAsStateWithLifecycle()
    val editDialogData by mainLayoutViewModel.editDialogData.collectAsStateWithLifecycle()



    Scaffold(modifier = modifier.fillMaxSize(), topBar = {
        CenterAlignedTopAppBar(
            title = {
                Text("ToDo List")
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary
            )
        )
    }) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxSize()
                .padding(top = 30.dp, start = 15.dp, end = 15.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .widthIn(max = 1000.dp),
            ) {
                TodoTabs(
                    onQuery = { todoViewModel.search(it) },
                    actions = listOf(
                        {
                            if (checkList.isNotEmpty()){
                                TextButton(
                                    onClick = { todoViewModel.removeAll() },
                                    colors = ButtonDefaults.textButtonColors(
                                        contentColor = MaterialTheme.colorScheme.error
                                    )
                                ) {
                                    Text("Remove All")
                                }
                            }
                        }
                    )
                ) { selectedIndex ->
                    TodoList(
                        data = when (selectedIndex) {
                            1 -> filteredItems.filter { it.status == TodoStatus.PENDING }
                            2 -> filteredItems.filter { it.status == TodoStatus.ONGOING }
                            3 -> filteredItems.filter { it.status == TodoStatus.COMPLETE }
                            else -> filteredItems
                        },
                        onEdit = { id, data ->
                            mainLayoutViewModel.openEditDialog(data)
                        },
                        onRemove = { id ->
                            mainLayoutViewModel.openRemoveDialog(id)
                        },
                        onCheck = { id ->
                            todoViewModel.toggleCheck(id)
                        },
                        checked = { id ->
                            id in checkList
                        }
                    )
                }
            }
        }
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(20.dp)
        ) {
            FloatingActionButton(
                onClick = {
                    mainLayoutViewModel.openEditDialog()
                },
                modifier = Modifier.align(Alignment.BottomEnd)
            ) {
                Icon(Icons.Filled.Add, "Floating action button.")
            }
        }

        if (editDialog) {
            FormDialog(
                onDismissRequest = {
                    mainLayoutViewModel.dismissEditDialog()
                },
                onConfirmation = { data ->
                    if (editDialogData == null) {
                        todoViewModel.add(data)
                    } else {
                        todoViewModel.edit(data.id, data)
                    }
                    mainLayoutViewModel.dismissEditDialog()
                },
                data = editDialogData
            )
        }

        if (removeDialog != null) {
            RemoveDialog(
                onDismissRequest = {
                    mainLayoutViewModel.dismissRemoveDialog()
                },
                onConfirmation = {
                    todoViewModel.remove(removeDialog!!)
                    mainLayoutViewModel.dismissRemoveDialog()
                },
                "Remove ToDo",
                "Are you sure you want to remove this ToDo?"
            )
        }
    }
}