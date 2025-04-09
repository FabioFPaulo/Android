package com.fabiofpaulo.todolist.models.view

import androidx.lifecycle.ViewModel
import com.fabiofpaulo.todolist.models.Todo
import com.fabiofpaulo.todolist.utils.generateRandomTodos
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.UUID

class TodoViewModel : ViewModel() {
    //https://developer.android.com/develop/ui/compose/quick-guides/content/filter-list-while-typing
    private val todos = generateRandomTodos(10)

    private val _filteredTodos = MutableStateFlow(todos)
    var filteredTodos: StateFlow<List<Todo>> = _filteredTodos

    private val _checkList = MutableStateFlow<List<UUID>>(listOf())
    var checkList: StateFlow<List<UUID>> = _checkList

    fun search(query: String) {
        if (query.isEmpty()) {
            _filteredTodos.value = todos
        } else {
            _filteredTodos.value = todos.filter { it.label.contains(query, ignoreCase = true) }
        }
    }

    fun add(data: Todo) {
        _filteredTodos.value = _filteredTodos.value + data
    }

    fun edit(id: UUID, data: Todo) {
        _filteredTodos.value = _filteredTodos.value.map {
            if (it.id == id) {
                data
            } else {
                it
            }
        }
    }

    fun remove(id: UUID) {
        _filteredTodos.value = _filteredTodos.value.filter { it.id != id }
    }

    fun removeAll() {
        _filteredTodos.value = _filteredTodos.value.filter { it.id !in _checkList.value }
        _checkList.value = listOf()
    }

    fun toggleCheck(id: UUID) {
        if (id in _checkList.value){
            _checkList.value = _checkList.value.filter { it != id }
        }else{
            _checkList.value = _checkList.value + id
        }
    }
}