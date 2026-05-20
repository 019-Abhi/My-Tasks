package com.example.mytasks.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.mytasks.data.local.entity.TaskEntity
import com.example.mytasks.data.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ShortTermViewModel(private val repository: TaskRepository) : ViewModel() {

    fun getTasksByCategory(categoryId: Long): Flow<List<TaskEntity>> =
        repository.getTasksByCategory(categoryId)

    fun addTask(description: String, deadline: Long?, categoryId: Long) {
        viewModelScope.launch {
            repository.insertTask(
                TaskEntity(
                    description = description,
                    deadline = deadline,
                    categoryId = categoryId
                )
            )
        }
    }

    fun completeTask(task: TaskEntity) {
        viewModelScope.launch {
            repository.deleteTask(task)
        }
    }
}

class ShortTermViewModelFactory(private val repository: TaskRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ShortTermViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ShortTermViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
