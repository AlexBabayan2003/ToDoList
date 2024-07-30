package com.example.todolistapp.todo_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolistapp.data.ToDo
import com.example.todolistapp.data.ToDoRepository
import com.example.todolistapp.util.Routes
import com.example.todolistapp.util.UiEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ToDoListViewModel @Inject constructor(
    private val repository: ToDoRepository,
) : ViewModel() {

    val todos = repository.getToDos()

    private val _uiEvent = Channel<UiEvents>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private var deletedToDo: ToDo? = null

    fun onEvent(event: ToDoListEvent) {
        when (event) {
            is ToDoListEvent.OnToDoClick -> {
                sendUiEvent(UiEvents.Navigate(Routes.ADD_EDIT_TODO + "?todoId=${event.toDo.id}"))
            }

            is ToDoListEvent.OnDeleteToDoClick -> {
                viewModelScope.launch {
                    deletedToDo = event.toDo
                    repository.deleteToDo(event.toDo)
                    sendUiEvent(
                        UiEvents.ShowSnackBar(
                            message = "ToDo deleted",
                            action = "Undo",
                        )
                    )
                }
            }

            ToDoListEvent.OnAddToDoClick -> {
                sendUiEvent(UiEvents.Navigate(Routes.ADD_EDIT_TODO))
            }

            is ToDoListEvent.OnDoneChange -> {
                viewModelScope.launch {
                    repository.insertToDo(
                        event.toDo.copy(
                            isDone = event.isDone
                        )
                    )
                }
            }

            ToDoListEvent.OnUndoDeleteClick -> {
                deletedToDo?.let { todo ->
                    viewModelScope.launch {
                        repository.insertToDo(todo)
                    }
                }

            }

        }
    }

    private fun sendUiEvent(event: UiEvents) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}