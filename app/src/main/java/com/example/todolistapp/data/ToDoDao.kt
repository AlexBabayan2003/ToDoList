package com.example.todolistapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ToDoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertToDo(toDo: ToDo)

    @Delete
    suspend fun deleteToDo(toDo: ToDo)

    @Query("SELECT * FROM ToDo WHERE id = :id")
    suspend fun getToDoById(id: Int): ToDo?

    @Query("SELECT * FROM ToDo")
    fun getToDos(): Flow<List<ToDo>>

}