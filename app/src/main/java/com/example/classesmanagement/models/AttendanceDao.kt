package com.example.classesmanagement.models


import androidx.room.*

@Dao
interface AttendanceDao {
    @Query("SELECT * FROM Attendance")
    fun getAll(): List<Attendance>

    @Query("SELECT * FROM Attendance WHERE aid IN (:aid)")
    fun loadAllByIds(aid: IntArray): List<Attendance>

    @Insert
    fun insertStudent(attendance: Attendance)

    @Update(entity = Attendance::class)
    fun updateStudent(attendance: Attendance)

    @Delete
    fun delete(student: Attendance)

    @Transaction
    @Query("SELECT * FROM Attendance where date_attendance >= :date0")
    fun getStudentsWithAttendance(date0: String): List<StudentWithAttendance>
}