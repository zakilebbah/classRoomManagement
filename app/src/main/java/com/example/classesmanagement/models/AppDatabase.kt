package com.example.classesmanagement.models

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Classe::class, Student::class, Attendance::class, ClassRoom_Student::class], version = 1, exportSchema = true,)
abstract class AppDatabase : RoomDatabase() {
    abstract fun ClasseDao(): ClasseDao
    abstract fun StudentDao(): StudentDao
    abstract fun AttendanceDao(): AttendanceDao
    abstract fun ClassRoom_Student_Dao(): ClassRoom_Student_Dao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        AppDatabase::class.java, "school").allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }

}

