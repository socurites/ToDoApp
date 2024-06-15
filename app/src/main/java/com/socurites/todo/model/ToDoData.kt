package com.socurites.todo.model

data class ToDoData(
    val key: Int,
    val text: String,
    val done: Boolean = false,
)