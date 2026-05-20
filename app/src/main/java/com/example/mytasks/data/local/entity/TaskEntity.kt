package com.example.mytasks.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val description: String,
    val deadline: Long? = null,
    val categoryId: Long,
    val isCompleted: Boolean = false
)
