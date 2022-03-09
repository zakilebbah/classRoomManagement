package com.example.classesmanagement

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.classesmanagement.adapters.StudentAdapter
import com.example.classesmanagement.models.*
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Collections.addAll

class ClassRoomWithStudents : AppCompatActivity() {
    var id: Int = -1
    private var recyclerview: RecyclerView? = null
    private var button: Button? = null
    lateinit var nom: TextInputEditText
    lateinit var grade: TextInputEditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_class_room_with_students)
        id = intent.getIntExtra("id", -1)
        recyclerview = findViewById(R.id.recyclerview)
        button = findViewById(R.id.addStudent)
        nom =  findViewById(R.id.nom)
        grade = findViewById(R.id.grade)
        getStudents()
        initClassRoom()
        button?.setOnClickListener { view ->
            addStudent()
        }
//        addStudent()
    }
    private fun addStudent() {
//        Log.d("TEST TEST ID",id.toString())
        val intent = Intent(this, addStudentToClassPage()::class.java)
        intent.putExtra("id", id);
        this?.startActivity(intent)
    }
    private fun getStudents() {
        val db = AppDatabase?.getInstance(this)
        GlobalScope.launch {
            if (db != null) {
                var studentList0: List<StudentWithclass> =  db.ClassRoom_Student_Dao().getClassWithStudents(id)
                Log.d("TEST TEST size size si",studentList0.size.toString())
                if (studentList0.isNotEmpty()) {

                    var students: List<Student> = listOf()
                    for (st in studentList0) {
                        students += st.student
                    }
                    Log.d("TEST TEST student stud",students.size.toString())
                    val adapter =  StudentAdapter(students)
                    withContext(Dispatchers.Main) {
                        recyclerview?.layoutManager = LinearLayoutManager(applicationContext)
                        recyclerview?.adapter =adapter
                    }
                }


            }


        }

    }
    fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)

    private fun initClassRoom() {
        if (id != -1) {
            val db = AppDatabase?.getInstance(this)
            if (db != null) {
               var  classe: Classe = db.ClasseDao().loadById(id)
                nom.text = classe.name.toString().toEditable()
                grade.text = classe.grade.toString().toEditable()
            }
        }
    }
}




