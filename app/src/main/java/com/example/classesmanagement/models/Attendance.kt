package com.example.classesmanagement.models

import androidx.room.*
import java.util.*

@Entity(tableName = "Attendance")
data class Attendance(
    @PrimaryKey(autoGenerate = true) val aid: Int = 0,
    @ColumnInfo(name = "date_attendance") val date_attendance: String?,
    @ColumnInfo(name = "student_id") val student_id: Int?,
    @ColumnInfo(name = "status") val status: String?,

    ) {

}

data class StudentWithAttendance(
    @Embedded val attendance: Attendance,
    @Relation(
        parentColumn = "aid",
        entityColumn = "sid"
    )
    val studentList: List<Student>
)