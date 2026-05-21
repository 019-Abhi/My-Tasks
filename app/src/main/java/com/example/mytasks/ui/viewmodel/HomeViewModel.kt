package com.example.mytasks.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mytasks.data.local.entity.CategoryEntity
import com.example.mytasks.data.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class HomeViewModel(private val repository: CategoryRepository) : ViewModel() {

    val shortTermCategories: Flow<List<CategoryEntity>> =
        repository.getCategoriesByType("ST").map { allCategories ->
            // Sort by starred first, then by name (or id)
            val starred = allCategories.filter { it.isStarred }
            val unstarred = allCategories.filter { !it.isStarred }
            
            val combined = (starred + unstarred).take(6)
            combined
        }
}

class HomeViewModelFactory(private val repository: CategoryRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
