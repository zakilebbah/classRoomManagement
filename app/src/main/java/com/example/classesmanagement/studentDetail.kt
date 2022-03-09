package com.example.classesmanagement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.Button
import com.example.classesmanagement.databinding.ActivityClassDetailBinding
import com.example.classesmanagement.models.AppDatabase
import com.example.classesmanagement.models.Student
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*
import android.app.Activity

import android.content.Intent




class StudentDetail : AppCompatActivity() {
    lateinit var nom: TextInputEditText
    lateinit var prenom: TextInputEditText
    lateinit var email: TextInputEditText
    lateinit var tel: TextInputEditText
    var id: Int = -1
    private lateinit var binding: ActivityClassDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_detail)
        nom = findViewById(R.id.nom)
        prenom = findViewById(R.id.prenom)
        email = findViewById(R.id.email)
        tel = findViewById(R.id.tel)

        var saveButton: Button = findViewById(R.id.saveButton)
        saveButton.setOnClickListener {view ->
            onSaveButtonclick(view)
        }
        val param_student = intent.getSerializableExtra("student") as HashMap<String, String>
        initStudent(param_student)

    }
    private fun onSaveButtonclick(view0: View) {
        val db = AppDatabase.getInstance(this)

        if (nom.text.toString() == "" && prenom.text.toString() == "") {
            Snackbar.make(view0, "Please fill in the info", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        else {
            GlobalScope.launch {
                if (id != -1) {
                    val student0 = Student(sid = id, nom = nom.text.toString(),
                        prenom = prenom.text.toString(), email = email.text.toString(), password = "",
                        tel = tel.text.toString() ,date_creation = Date().toString())
                    db?.StudentDao()?.updateStudent(student0)
                }
                else {
                    val student0 = Student(nom = nom.text.toString(),
                        prenom = prenom.text.toString(), email = email.text.toString(), password = "",
                        tel = tel.text.toString() ,date_creation = Date().toString())
                    db?.StudentDao()?.insertStudent(student0)
                }
            }
        }
        val returnIntent = Intent()
        setResult(RESULT_OK, returnIntent) // or RESULT_CANCELED if user did not make any changes
        finish()
    }
    fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)

    private fun initStudent(map: HashMap<String, String>) {
        if (map != null) {
            id = map["id"].toString().toInt()
            nom.text = map["nom"]?.toEditable()
            prenom.text = map["prenom"]?.toEditable()
            tel.text = map["tel"]?.toEditable()
            email.text = map["email"]?.toEditable()
        }

    }
}