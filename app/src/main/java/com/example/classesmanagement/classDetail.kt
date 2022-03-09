package com.example.classesmanagement

import android.os.Bundle
import android.view.View
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import com.example.classesmanagement.databinding.ActivityClassDetailBinding
import com.example.classesmanagement.models.AppDatabase
import com.example.classesmanagement.models.Classe
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

class ClassDetail : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityClassDetailBinding
    lateinit var nomText: TextInputEditText
    lateinit var gradeText: TextInputEditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //actionbar

        binding = ActivityClassDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        nomText = findViewById(R.id.nom)
        gradeText = findViewById(R.id.grade)
        binding.fab.setOnClickListener { view ->

            onSaveButtonclick(view)
        }
        //actionbar
        val actionbar = supportActionBar
        //set actionbar title
        actionbar!!.title = "Class"
        //set back button
        actionbar!!.setDisplayHomeAsUpEnabled(true)

    }
    private fun onSaveButtonclick(view0: View) {
        val db = AppDatabase.getInstance(this)

        if (nomText.text.toString() == "" && gradeText.text.toString() == "") {
            Snackbar.make(view0, "Please fill in the info", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        else {
            val class0 = Classe(name = nomText.text.toString(), grade = gradeText.text.toString(), date =Date().toString())

            GlobalScope.launch {
                db?.ClasseDao()?.insertClass(class0)

            }
        }
    }
//    override fun onSupportNavigateUp(): Boolean {
//        val navController = findNavController(R.id.nav_host_fragment_content_class_detail)
//        return navController.navigateUp(appBarConfiguration)
//                || super.onSupportNavigateUp()
//    }
}