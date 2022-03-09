package com.example.classesmanagement.adapters


import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.classesmanagement.ClassRoomWithStudents
import com.example.classesmanagement.R
import com.example.classesmanagement.StudentDetail
import com.example.classesmanagement.models.Classe
import com.example.classesmanagement.models.Student

class ClasseAdapter(private val dataSet: List<Classe>) :
    RecyclerView.Adapter<ClasseAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewName: TextView
        val card: CardView
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

        viewHolder.textViewName.text = dataSet[position].name
        viewHolder.card.setOnClickListener { view ->
            goToClassRoom(dataSet[position].cid, view)
        }
    }
    private fun goToClassRoom(id: Int, v: View){
//        val student00: Student = Student(nom = "", prenom = "", email = "", password = "", tel = "", date_creation = "")
        val student00 = LinkedHashMap<String, String>()
        Log.d("TEST TEST", student00["nom"].toString())
        val intent = Intent(v.context, ClassRoomWithStudents()::class.java)
        intent.putExtra("id", id);
        v.context.startActivity(intent)
    }
    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}
