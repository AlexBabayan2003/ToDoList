package com.example.todolistapp.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.todolistapp.data.ToDoDao
import com.example.todolistapp.data.ToDoDb
import com.example.todolistapp.data.ToDoRepository
import com.example.todolistapp.data.ToDoRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object AppModule {

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): ToDoDb = Room.databaseBuilder(
        context, ToDoDb::class.java, "todo_db"
    ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideToDoDao(db: ToDoDb): ToDoDao = db.getToDoDao()
}
