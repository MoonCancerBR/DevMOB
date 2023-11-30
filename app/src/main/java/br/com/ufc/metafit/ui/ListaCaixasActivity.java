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
    //FirebaseRecyclerAdapter<Destinos,DestinosAdapter.ViewHolder> adapter;
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

        //final List<Destinos> destinosList = new ArrayList<Destinos>();

        // Aqui você adiciona o código para buscar e preencher os dados do Firebase
        databaseReference.child("destinos").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                ListaCaixasActivity.destinosList.clear(); // Limpa a lista antes de preenchê-la novamente

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Destinos destino = snapshot.getValue(Destinos.class);
                    if (destino != null) {
                        ListaCaixasActivity.destinosList.add(destino);
                    }
                }

                // Aqui, a lista destinosLista está preenchida com os dados do Firebase
                configurarAdapter(); // Configura o FirebaseRecyclerAdapter após preencher a lista
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
    }
    private void configurarAdapter() {
        DestinosAdapter adapter = new DestinosAdapter(this,ListaCaixasActivity.destinosList);
        recyclerView.setAdapter(adapter);
        //listaCaixa
        adapter.setOnDeleteClickListener(new DestinosAdapter.OnDeleteClickListener() {
            @Override
            public void onDeleteClick(int position) {
                // Remova o item da lista
                Destinos destinoRemovido = ListaCaixasActivity.destinosList.remove(position);

                // Atualize o RecyclerView
                adapter.notifyItemRemoved(position);

                // Remova do Firebase, substitua "chaveDoDestino" pela chave do item no Firebase
                databaseReference.child("destinos").child(destinoRemovido.getCodigo()).removeValue();
            }
        });
    }
}