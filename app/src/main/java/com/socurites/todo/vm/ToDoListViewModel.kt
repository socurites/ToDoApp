package com.socurites.todo.vm

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.socurites.todo.ToDoData

class ToDoViewModel: ViewModel() {
    val text = mutableStateOf("")
    val toDoList = mutableStateListOf<ToDoData>()

    val onSubmit: (String) -> Unit = {
        val key = (toDoList.lastOrNull()?.key ?: 0) + 1
        toDoList.add(ToDoData(key, it, false))
        text.value = ""
    }

    val onToggle: (Int, Boolean) -> Unit = { key, checked ->
        val indexOfFirst = toDoList.indexOfFirst { it.key == key }
        toDoList[indexOfFirst] = toDoList[indexOfFirst].copy(done = checked)
    }

    val onDelete: (Int) -> Unit = {key ->
        val indexOfFirst = toDoList.indexOfFirst { it.key == key }
        toDoList.removeAt(indexOfFirst)
    }

    val onEdit: (Int, String) -> Unit = { key, text ->
        val indexOfFirst = toDoList.indexOfFirst { it.key == key }
        toDoList[indexOfFirst] = toDoList[indexOfFirst].copy(text = text)
    }
}