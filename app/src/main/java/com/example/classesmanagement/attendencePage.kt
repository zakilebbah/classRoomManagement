package com.example.classesmanagement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.classesmanagement.models.AppDatabase
import com.example.classesmanagement.models.StudentWithAttendance
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

class attendencePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_attendence_page)
        getAttendence()

    }
    private fun getAttendence() {

        val db = AppDatabase?.getInstance(this)


        GlobalScope.launch {

            if (db != null) {

                var attendeanceList: List<StudentWithAttendance> =  db.AttendanceDao().getStudentsWithAttendance(
                    Date().toString())
                Log.d("TEST TEST", attendeanceList.size.toString())


            }


        }

    }
}