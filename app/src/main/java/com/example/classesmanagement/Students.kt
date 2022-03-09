package com.example.classesmanagement

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat.recreate
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.classesmanagement.adapters.StudentAdapter
import com.example.classesmanagement.models.AppDatabase
import com.example.classesmanagement.models.Student
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Students.newInstance] factory method to
 * create an instance of this fragment.
 */
class Students : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var recyclerview: RecyclerView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var rootView: View = inflater.inflate(R.layout.fragment_students, container, false)
        val fab = rootView.findViewById<FloatingActionButton>(R.id.addStudent)
        fab.setOnClickListener {
            Log.d("TEST TEST", "goToStudentDetail goToStudentDetail goToStudentDetail")

            goToStudentDetail()
        }
        recyclerview = rootView.findViewById(R.id.recyclerview)
        getStudents()
        return rootView
    }
    private fun goToStudentDetail(){
//        val student00: Student = Student(nom = "", prenom = "", email = "", password = "", tel = "", date_creation = "")
        val student00 = LinkedHashMap<String, String>()
        student00["id"] = "-1"
        student00["nom"] = ""
        student00["prenom"] = ""
        student00["email"] = ""
        student00["password"] = ""
        student00["tel"] = ""
        student00["date_creation"] = ""
        val intent = Intent(activity, StudentDetail()::class.java)
        intent.putExtra("student", student00);
        activity?.startActivity(intent)
        Log.d("///////////////activity", "activity activity activity")

    }

    private fun getStudents() {

        val db = AppDatabase?.getInstance(requireContext())


        GlobalScope.launch {

            if (db != null && recyclerview != null) {

                var studentList: List<Student> =  db.StudentDao().getAll()
                Log.d("TEST TEST", studentList.size.toString())
                val adapter =  StudentAdapter(studentList)
                withContext(Dispatchers.Main) {
                    recyclerview?.layoutManager = LinearLayoutManager(requireContext())
                    recyclerview?.adapter =adapter
                }

            }


        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Students.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Students().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

}
