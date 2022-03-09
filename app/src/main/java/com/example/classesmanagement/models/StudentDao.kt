
package com.example.classesmanagement.models


import androidx.room.*

@Dao
interface StudentDao {
    @Query("SELECT * FROM Student")
    fun getAll(): List<Student>

    @Query("SELECT * FROM Student WHERE sid IN (:cid)")
    fun loadAllByIds(cid: IntArray): List<Student>

    @Query("SELECT * FROM Student WHERE nom LIKE :name0")
    fun findName(name0: String): Student

    @Insert
    fun insertStudent(student: Student)

    @Update(entity = Student::class)
    fun updateStudent(student: Student)

    @Delete
    fun delete(student: Student)
}