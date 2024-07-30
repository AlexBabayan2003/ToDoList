package com.example.todolistapp.todo_list

import com.example.todolistapp.data.ToDo

sealed class ToDoListEvent {
    data class OnDeleteToDoClick(val toDo: ToDo) : ToDoListEvent()
    data class OnDoneChange(val toDo: ToDo, val isDone: Boolean) : ToDoListEvent()
    data object OnUndoDeleteClick : ToDoListEvent()
    data class OnToDoClick(val toDo: ToDo) : ToDoListEvent()
    data object OnAddToDoClick : ToDoListEvent()
}