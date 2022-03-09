package com.example.classesmanagement.models

import androidx.room.Entity
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "Student")
data class Student(
    @PrimaryKey(autoGenerate = true) val sid: Int = 0,
    @ColumnInfo(name = "nom") val nom: String?,
    @ColumnInfo(name = "prenom") val prenom: String?,
    @ColumnInfo(name = "date_creation") val date_creation: String?,
    @ColumnInfo(name = "email") val email: String?,
    @ColumnInfo(name = "tel") val tel: String?,
    @ColumnInfo(name = "password") val password: String?,

//    @ColumnInfo(name = "last_name") val lastName: String?
) {

}
