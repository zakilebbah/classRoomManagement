
package com.example.classesmanagement.adapters


import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.classesmanagement.R
import com.example.classesmanagement.StudentDetail
import com.example.classesmanagement.models.Student

class StudentAdapter(private val dataSet: List<Student>) :
    RecyclerView.Adapter<StudentAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val card: CardView
        val textViewName: TextView

        init {
            // Define click listener for the ViewHolder's View.
            textViewName = view.findViewById(R.id.name)
            card= view.findViewById(R.id.card)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.card_view_design, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element

        viewHolder.textViewName.text = dataSet[position].nom + " " +dataSet[position].prenom
        viewHolder.card.setOnClickListener { view ->
            goToStudentDetail(dataSet[position], view)
        }
    }
    private fun goToStudentDetail(student0 :Student, v: View){
//        val student00: Student = Student(nom = "", prenom = "", email = "", password = "", tel = "", date_creation = "")
        val student00 = LinkedHashMap<String, String>()
        student00["id"] = student0.sid.toString()
        student00["nom"] = student0.nom.toString()
        student00["prenom"] = student0.prenom.toString()
        student00["email"] = student0.email.toString()
        student00["password"] = student0.password.toString()
        student00["tel"] = student0.tel.toString()
        student00["date_creation"] = student0.date_creation.toString()
        Log.d("TEST TEST", student00["nom"].toString())
        val intent = Intent(v.context, StudentDetail()::class.java)
        intent.putExtra("student", student00);
        v.context.startActivity(intent)
    }
    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}
