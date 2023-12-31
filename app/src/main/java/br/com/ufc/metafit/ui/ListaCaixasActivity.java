package br.com.ufc.metafit.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import br.com.ufc.metafit.R;

public class ListaCaixasActivity extends AppCompatActivity{


    RecyclerView recyclerView;
    public static List<Destinos> destinosList = new ArrayList<Destinos>();
    DestinosAdapter adapter;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_caixas);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        recyclerView = findViewById(R.id.reciclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        final List<Destinos> destinosList = new ArrayList<Destinos>();


        databaseReference.child("destinos").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                ListaCaixasActivity.destinosList.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Destinos destino = snapshot.getValue(Destinos.class);
                    if (destino != null) {
                        ListaCaixasActivity.destinosList.add(destino);
                    }
                }


                configurarAdapter();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
    }
    private void configurarAdapter() {
        DestinosAdapter adapter = new DestinosAdapter(this,ListaCaixasActivity.destinosList);
        recyclerView.setAdapter(adapter);
        adapter.setOnDeleteClickListener(new DestinosAdapter.OnDeleteClickListener() {
            @Override
            public void onDeleteClick(int position) {
                Destinos destinoRemovido = ListaCaixasActivity.destinosList.remove(position);

                adapter.notifyItemRemoved(position);

                databaseReference.child("destinos").child(destinoRemovido.getCodigo()).removeValue();
            }
        });
    }
}