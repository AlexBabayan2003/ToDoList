package com.example.todolistapp.ui.add_edit_todo

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.todolistapp.util.UiEvents

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddEditToDoScreen(
    onPopBackStack: () -> Unit,
    viewModel: AddEditToDoViewModel = hiltViewModel(),
) {
    val scaffoldState = remember { SnackbarHostState() }
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvents.PopBackStack -> onPopBackStack()
                is UiEvents.ShowSnackBar -> {
                    scaffoldState.showSnackbar(
                        message = event.message,
                        actionLabel = event.action,
                        duration = SnackbarDuration.Short
                    )
                }

                else -> Unit
            }
        }
    }
    Scaffold(snackbarHost = { SnackbarHost(hostState = scaffoldState) }, floatingActionButton = {
        FloatingActionButton(onClick = {
            viewModel.onEvent(AddEditToDoEvent.OnSaveToDoClick)
        }) {
            Icon(
                imageVector = Icons.Default.Check, contentDescription = "Save"
            )
        }
    }) {
        Column(
            modifier = Modifier
                .padding()
                .fillMaxSize()
        ) {
            TextField(value = viewModel.title, onValueChange = {
                viewModel.onEvent(AddEditToDoEvent.OnTitleChange(it))
            }, placeholder = {
                Text(text = "Title")
            }, modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(value = viewModel.description, onValueChange = {
                viewModel.onEvent(AddEditToDoEvent.OnDescriptionChange(it))
            }, placeholder = {
                Text(text = "Description")
            }, modifier = Modifier.fillMaxWidth(),
                singleLine = false,
                maxLines = 5
            )
        }
    }
}