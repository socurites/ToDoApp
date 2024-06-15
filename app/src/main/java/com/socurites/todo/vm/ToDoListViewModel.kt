package com.socurites.todo.vm

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.socurites.todo.model.ToDoData

class ToDoViewModel: ViewModel() {
    private val _text = MutableLiveData("")
    val setText: (String) -> Unit = {
        _text.value = it
    }
    val text: LiveData<String> = _text

    val toDoList = mutableStateListOf<ToDoData>()

    val onSubmit: (String) -> Unit = {
        val key = (toDoList.lastOrNull()?.key ?: 0) + 1
        toDoList.add(ToDoData(key, it, false))
        _text.value = ""
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