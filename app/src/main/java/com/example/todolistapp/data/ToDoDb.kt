package com.example.todolistapp.data

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [ToDo::class],
    version = 2,
//    exportSchema = false
//            autoMigrations = [
//        AutoMigration (from = 1, to = 2)
//    ]
)
abstract class ToDoDb: RoomDatabase() {

    abstract fun getToDoDao(): ToDoDao

}