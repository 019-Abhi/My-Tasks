package com.example.mytasks.data.repository

import com.example.mytasks.data.local.dao.CategoryDao
import com.example.mytasks.data.local.dao.LongTermGoalDao
import com.example.mytasks.data.local.dao.TaskDao
import com.example.mytasks.data.local.entity.CategoryEntity
import kotlinx.coroutines.flow.Flow

class CategoryRepository(
    private val categoryDao: CategoryDao,
    private val taskDao: TaskDao,
    private val longTermGoalDao: LongTermGoalDao
) {
    fun getCategoriesByType(type: String): Flow<List<CategoryEntity>> =
        categoryDao.getCategoriesByType(type)

    suspend fun insertCategory(category: CategoryEntity) {
        categoryDao.insertCategory(category)
    }

    suspend fun updateCategory(category: CategoryEntity) {
        categoryDao.updateCategory(category)
    }

    suspend fun deleteCategory(category: CategoryEntity) {
        // Handle associated tasks/goals
        if (category.type == "ST") {
            taskDao.deleteTasksByCategoryId(category.id)
        } else {
            longTermGoalDao.deleteGoalsByCategoryId(category.id)
        }
        categoryDao.deleteCategory(category)
    }

    suspend fun getCategoryById(id: Long): CategoryEntity? =
        categoryDao.getCategoryById(id)
}
