package com.example.classesmanagement.models

import androidx.room.*

@Dao
interface ClassRoom_Student_Dao {
    @Query("SELECT * FROM ClassRoom_Student")
    fun getAll(): List<ClassRoom_Student>

    @Query("SELECT * FROM ClassRoom_Student WHERE class_room_id==:class_room_id0 and student_id==:student_id0")
    fun loadId(class_room_id0: Int,student_id0: Int ): List<ClassRoom_Student>

    @Insert
    fun insert(classRoom_Student: ClassRoom_Student)

    @Delete
    fun delete(classRoom_Student: ClassRoom_Student)

    @Transaction
    @Query("SELECT * FROM ClassRoom_Student where class_room_id == :id")
    fun getClassWithStudents(id: Int): List<StudentWithclass>
}