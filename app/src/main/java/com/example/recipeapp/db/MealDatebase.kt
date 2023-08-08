package com.example.recipeapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.recipeapp.pojo.Meal

@Database(entities = [Meal::class], version = 1)
@TypeConverters(MealTypeConverter::class)
abstract class MealDatebase: RoomDatabase() {
    abstract fun mealDao(): MealDao

    companion object {
        @Volatile
        var INSTANCE: MealDatebase? = null

        @Synchronized
        fun getInstance(context: Context): MealDatebase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context,
                    MealDatebase::class.java,
                    "mealDB"
                ).fallbackToDestructiveMigration()
                    .build()
            }
            return INSTANCE as MealDatebase
        }
    }
}