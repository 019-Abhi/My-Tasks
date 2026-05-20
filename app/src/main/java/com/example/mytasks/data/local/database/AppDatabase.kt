package com.example.mytasks.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.mytasks.data.local.dao.CategoryDao
import com.example.mytasks.data.local.dao.LongTermGoalDao
import com.example.mytasks.data.local.dao.TaskDao
import com.example.mytasks.data.local.entity.CategoryEntity
import com.example.mytasks.data.local.entity.LongTermGoalEntity
import com.example.mytasks.data.local.entity.TaskEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [TaskEntity::class, LongTermGoalEntity::class, CategoryEntity::class],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
    abstract fun longTermGoalDao(): LongTermGoalDao
    abstract fun categoryDao(): CategoryDao

    companion object {
        const val DATABASE_NAME = "my_tasks_db"

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DATABASE_NAME
                )
                .fallbackToDestructiveMigration()
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        // Pre-populate default categories using raw SQL to avoid recursion
                        db.execSQL("INSERT INTO categories (name, type) VALUES ('Groceries', 'ST')")
                        db.execSQL("INSERT INTO categories (name, type) VALUES ('Study', 'ST')")
                        db.execSQL("INSERT INTO categories (name, type) VALUES ('Work', 'ST')")
                        db.execSQL("INSERT INTO categories (name, type) VALUES ('Personal', 'ST')")
                        
                        db.execSQL("INSERT INTO categories (name, type) VALUES ('Coding', 'LT')")
                        db.execSQL("INSERT INTO categories (name, type) VALUES ('Fitness', 'LT')")
                        db.execSQL("INSERT INTO categories (name, type) VALUES ('Music', 'LT')")
                    }
                })
                .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
