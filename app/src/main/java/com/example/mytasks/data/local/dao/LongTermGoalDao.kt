package com.example.mytasks.data.local.dao

import androidx.room.*
import com.example.mytasks.data.local.entity.LongTermGoalEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LongTermGoalDao {
    @Query("SELECT * FROM long_term_goals")
    fun getAllGoals(): Flow<List<LongTermGoalEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGoal(goal: LongTermGoalEntity)

    @Update
    suspend fun updateGoal(goal: LongTermGoalEntity)

    @Delete
    suspend fun deleteGoal(goal: LongTermGoalEntity)

    @Query("SELECT * FROM long_term_goals WHERE categoryId = :categoryId")
    suspend fun getGoalByCategoryId(categoryId: Long): LongTermGoalEntity?

    @Query("DELETE FROM long_term_goals WHERE categoryId = :categoryId")
    suspend fun deleteGoalsByCategoryId(categoryId: Long)
}
