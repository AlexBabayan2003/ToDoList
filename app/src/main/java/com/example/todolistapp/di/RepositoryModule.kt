package com.example.todolistapp.di

import com.example.todolistapp.data.ToDoRepository
import com.example.todolistapp.data.ToDoRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindToDoRepository(
        repo: ToDoRepositoryImpl
    ): ToDoRepository

}