package br.com.ufc.metafit.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.ufc.metafit.R
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import android.view.ViewGroup
class ListaCaixasActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: FirebaseRecyclerAdapter<Destinos, DestinosAdapter.ViewHolder>
    lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_caixas)

        databaseReference = FirebaseDatabase.getInstance().getReference()
        recyclerView = findViewById(R.id.reciclerView)

        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = linearLayoutManager

        val options = FirebaseRecyclerOptions.Builder<Destinos>()
            .setQuery(databaseReference.child("destinos"), Destinos::class.java)
            .build()

        adapter = object : FirebaseRecyclerAdapter<Destinos, DestinosAdapter.ViewHolder>(options) {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DestinosAdapter.ViewHolder {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.destinos, parent, false)
                return DestinosAdapter.ViewHolder(view)
            }

            override fun onBindViewHolder(holder: DestinosAdapter.ViewHolder, position: Int, model: Destinos) {
                holder.codigo.text = model.codigo.toString()
                holder.telefone.text = model.telefone.toString()

                holder.eliminar.setOnClickListener {
                    // Lógica para remover o item do Firebase
                    getRef(position).removeValue()
                }
            }
        }

        recyclerView.adapter = adapter
    }

    override fun onStart() {
        super.onStart()
        adapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter.stopListening()
    }
}
