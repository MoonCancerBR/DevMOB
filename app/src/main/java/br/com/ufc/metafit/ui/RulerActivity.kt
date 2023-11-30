package br.com.ufc.metafit.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import br.com.ufc.metafit.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class RulerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ruler)

        //edições abaixo

        val database = FirebaseDatabase.getInstance()

        val myref1: DatabaseReference = database.getReference("informacao").child("titulo1")
        val myref2: DatabaseReference = database.getReference("informacao").child("paragrafo1")
        val myref3: DatabaseReference = database.getReference("informacao").child("titulo2")
        val myref4: DatabaseReference = database.getReference("informacao").child("paragrafo2")
        val myref5: DatabaseReference = database.getReference("informacao").child("titulo3")
        val myref6: DatabaseReference = database.getReference("informacao").child("paragrafo3")
        val myref7: DatabaseReference = database.getReference("informacao").child("titulo4")
        val myref8: DatabaseReference = database.getReference("informacao").child("paragrafo4")

        val textView1: TextView = findViewById(R.id.titulo1)
        val textView2: TextView = findViewById(R.id.paragrafo1)
        val textView3: TextView = findViewById(R.id.titulo2)
        val textView4: TextView = findViewById(R.id.paragrafo2)
        val textView5: TextView = findViewById(R.id.titulo3)
        val textView6: TextView = findViewById(R.id.paragrafo3)
        val textView7: TextView = findViewById(R.id.titulo4)
        val textView8: TextView = findViewById(R.id.paragrafo4)

        myref1.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                textView1.text = dataSnapshot.getValue(String::class.java)
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })

        myref2.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                textView2.text = dataSnapshot.getValue(String::class.java)
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })

        myref3.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                textView3.text = dataSnapshot.getValue(String::class.java)
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })

        myref4.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                textView4.text = dataSnapshot.getValue(String::class.java)
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })

        myref5.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                textView5.text = dataSnapshot.getValue(String::class.java)
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })

        myref6.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                textView6.text = dataSnapshot.getValue(String::class.java)
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })

        myref7.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                textView7.text = dataSnapshot.getValue(String::class.java)
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })

        myref8.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                textView8.text = dataSnapshot.getValue(String::class.java)
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
    }
}