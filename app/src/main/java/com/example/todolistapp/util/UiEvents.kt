package com.example.todolistapp.util

sealed class UiEvents {
    data object PopBackStack : UiEvents()
    data class Navigate(val route: String) : UiEvents()
    data class ShowSnackBar(
        val message: String,
        val action: String? = null,
    ) : UiEvents()

}