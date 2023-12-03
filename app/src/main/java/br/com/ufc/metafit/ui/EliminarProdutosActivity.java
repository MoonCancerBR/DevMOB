package br.com.ufc.metafit.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import br.com.ufc.metafit.R;

public class EliminarProdutosActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private ListView listView;
    private ArrayList<String> produtoNames;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_produtos);

        mDatabase = FirebaseDatabase.getInstance().getReference("produtos");
        listView = findViewById(R.id.listView);
        produtoNames = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, produtoNames);
        listView.setAdapter(adapter);

        loadProdutos();

        // Defina um listener de clique para a lista
        listView.setOnItemClickListener((parent, view, position, id) -> {
            String nomeProduto = produtoNames.get(position);
            deleteProduto(nomeProduto);
        });
    }
    private void loadProdutos() {
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                produtoNames.clear();
                for (DataSnapshot produtoSnapshot : snapshot.getChildren()) {
                    String nomeProduto = produtoSnapshot.getKey();
                    produtoNames.add(nomeProduto);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(EliminarProdutosActivity.this, "Erro ao carregar produtos", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void deleteProduto(String nomeProduto) {
        mDatabase.child(nomeProduto).removeValue()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(EliminarProdutosActivity.this, "Produto deletado com sucesso", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(EliminarProdutosActivity.this, "Erro ao deletar produto", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
