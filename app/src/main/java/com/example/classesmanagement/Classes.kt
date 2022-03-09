package com.example.classesmanagement

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.room.Database
import androidx.room.RoomDatabase
import com.google.android.material.floatingactionbutton.FloatingActionButton
import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.classesmanagement.adapters.ClasseAdapter
import com.example.classesmanagement.models.AppDatabase
import com.example.classesmanagement.models.Classe
import com.example.classesmanagement.models.ClasseDao
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
 * Use the [Classes.newInstance] factory method to
 * create an instance of this fragment.
 */
class Classes : Fragment() {

    @Database(entities = [Classe::class], version = 1)
    abstract class AppDatabase : RoomDatabase() {
        abstract fun ClasseDao(): ClasseDao
    }



    private var param1: String? = null
    private var param2: String? = null
    private var recyclerview: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,

    ): View? {
         // Inflate the layout for this fragment
     var rootView: View = inflater.inflate(R.layout.fragment_classes, container, false)
        val fab = rootView.findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            goToClassDetail()
        }
        recyclerview = rootView.findViewById(R.id.recyclerview)
        getClasses()
        return rootView
    }
    private fun goToClassDetail(){
        val intent = Intent(activity, ClassDetail()::class.java)
        activity?.startActivity(intent)
//        val transaction = activity?.supportFragmentManager?.beginTransaction()
//        transaction?.replace(R.id.nav_fragment, ClassDetail())
//        transaction?.disallowAddToBackStack()
//        transaction?.commit()
    }
    private fun getClasses() {

        val db = AppDatabase?.getInstance(requireContext())


        GlobalScope.launch {

            if (db != null && recyclerview != null) {

                var classList: List<Classe> =  db.ClasseDao().getAll()
                Log.d("TEST TEST", classList.size.toString())
                val adapter =  ClasseAdapter(classList)
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
         * @return A new instance of fragment Classes.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Classes().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }

    }
}