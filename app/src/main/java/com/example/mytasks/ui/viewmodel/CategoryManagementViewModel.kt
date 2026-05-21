package com.example.mytasks.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.mytasks.data.local.entity.CategoryEntity
import com.example.mytasks.data.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class CategoryManagementViewModel(private val repository: CategoryRepository) : ViewModel() {

    fun getCategoriesByType(type: String): Flow<List<CategoryEntity>> =
        repository.getCategoriesByType(type)

    fun addCategory(name: String, type: String) {
        viewModelScope.launch {
            repository.insertCategory(CategoryEntity(name = name, type = type))
        }
    }

    fun renameCategory(category: CategoryEntity, newName: String) {
        viewModelScope.launch {
            repository.updateCategory(category.copy(name = newName))
        }
    }

    fun toggleStar(category: CategoryEntity) {
        viewModelScope.launch {
            if (!category.isStarred) {
                // Check if we already have 6 starred categories of this type
                val starredCount = repository.getCategoriesByType(category.type).first()
                    .count { it.isStarred }
                if (starredCount >= 6) {
                    // Maybe show a message to the user? For now, just don't star.
                    return@launch
                }
            }
            repository.updateCategory(category.copy(isStarred = !category.isStarred))
        }
    }

    fun deleteCategory(category: CategoryEntity) {
        viewModelScope.launch {
            repository.deleteCategory(category)
        }
    }
}

class CategoryManagementViewModelFactory(private val repository: CategoryRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CategoryManagementViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CategoryManagementViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
