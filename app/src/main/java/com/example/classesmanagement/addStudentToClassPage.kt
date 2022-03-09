package com.example.classesmanagement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.classesmanagement.adapters.AddStudentAdapter
import com.example.classesmanagement.adapters.StudentAdapter
import com.example.classesmanagement.models.AppDatabase
import com.example.classesmanagement.models.Student
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class addStudentToClassPage : AppCompatActivity() {
    var id: Int = -1
    private var recyclerview: RecyclerView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student_to_class_page)
        id = intent.getIntExtra("id", -1)
        recyclerview = findViewById(R.id.recyclerview)
        getStudents()

    }
    private fun getStudents() {

        val db = AppDatabase?.getInstance(this)


        GlobalScope.launch {

            if (db != null && recyclerview != null) {

                var studentList: List<Student> =  db.StudentDao().getAll()
                Log.d("TEST TEST", studentList.size.toString())
                val adapter =  AddStudentAdapter(studentList, id)
                withContext(Dispatchers.Main) {
                    recyclerview?.layoutManager = LinearLayoutManager(applicationContext)
                    recyclerview?.adapter =adapter
                }

            }


        }

    }
}