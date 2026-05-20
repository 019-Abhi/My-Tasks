package com.example.mytasks

import android.app.Application
import com.example.mytasks.data.local.database.AppDatabase
import com.example.mytasks.data.repository.CategoryRepository
import com.example.mytasks.data.repository.LongTermGoalRepository
import com.example.mytasks.data.repository.TaskRepository

class MyTasksApplication : Application() {

    private val database by lazy { AppDatabase.getDatabase(this) }

    val taskRepository by lazy { TaskRepository(database.taskDao()) }
    val longTermGoalRepository by lazy { LongTermGoalRepository(database.longTermGoalDao()) }
    val categoryRepository by lazy {
        CategoryRepository(
            database.categoryDao(),
            database.taskDao(),
            database.longTermGoalDao()
        )
    }
}
