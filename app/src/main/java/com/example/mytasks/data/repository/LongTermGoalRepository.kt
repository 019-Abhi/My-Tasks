package com.example.mytasks.data.repository

import com.example.mytasks.data.local.dao.LongTermGoalDao
import com.example.mytasks.data.local.entity.LongTermGoalEntity
import kotlinx.coroutines.flow.Flow

class LongTermGoalRepository(private val longTermGoalDao: LongTermGoalDao) {
    val allGoals: Flow<List<LongTermGoalEntity>> = longTermGoalDao.getAllGoals()

    suspend fun insertGoal(goal: LongTermGoalEntity) {
        longTermGoalDao.insertGoal(goal)
    }

    suspend fun updateGoal(goal: LongTermGoalEntity) {
        longTermGoalDao.updateGoal(goal)
    }

    suspend fun deleteGoal(goal: LongTermGoalEntity) {
        longTermGoalDao.deleteGoal(goal)
    }

    suspend fun getGoalByCategoryId(categoryId: Long): LongTermGoalEntity? {
        return longTermGoalDao.getGoalByCategoryId(categoryId)
    }
}
