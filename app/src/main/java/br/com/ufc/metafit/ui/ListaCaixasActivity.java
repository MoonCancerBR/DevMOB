package br.com.ufc.metafit.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import br.com.ufc.metafit.R;

public class ListaCaixasActivity extends AppCompatActivity{


    RecyclerView recyclerView;
    List<Destinos> destinosList;
    FirebaseRecyclerAdapter<Destinos,DestinosAdapter.ViewHolder> adapter;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_caixas);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        recyclerView = (RecyclerView) findViewById(R.id.reciclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        FirebaseRecyclerOptions<Destinos> options =
                new FirebaseRecyclerOptions.Builder<Destinos>()
                        .setQuery(databaseReference.child("destinos"), Destinos.class)
                        .build();

        adapter = new FirebaseRecyclerAdapter<Destinos, DestinosAdapter.ViewHolder>(options) {
            @NonNull
            @Override
            public DestinosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.destinos, parent, false);
                return new DestinosAdapter.ViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull DestinosAdapter.ViewHolder viewHolder, int position, @NonNull Destinos destinos) {
                viewHolder.codigo.setText(String.valueOf(destinos.getCodigo()));
                viewHolder.telefone.setText(String.valueOf(destinos.getTelefone()));
                viewHolder.eliminar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int adapterPosition = viewHolder.getAbsoluteAdapterPosition();
                        if (adapterPosition != RecyclerView.NO_POSITION) {
                            adapter.getRef(adapterPosition).removeValue();
                        }
                    }
                });
            }
        };
        recyclerView.setAdapter(adapter);
    }
}