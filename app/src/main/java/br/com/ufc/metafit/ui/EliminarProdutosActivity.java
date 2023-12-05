package br.com.ufc.metafit.ui;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

import br.com.ufc.metafit.R;
import br.com.ufc.metafit.ui.Produto;

public class EliminarProdutosActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private ListView listView;
    private ArrayList<Produto> produtosList;
    private ArrayAdapter<Produto> adapter;
    private ImageView imagePreview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_produtos);

        mDatabase = FirebaseDatabase.getInstance().getReference("produtos");
        listView = findViewById(R.id.listView);
        produtosList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice, produtosList);
        listView.setAdapter(adapter);

        // Adicione a propriedade de seleção única à ListView
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        // Adicione esta linha para referenciar o ImageView no layout
        imagePreview = findViewById(R.id.imagePreview);

        loadProdutos();

        // Defina um listener de clique para a lista
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Produto produto = produtosList.get(position);
            showImagePreview(produto.getImageUrl());
        });

        // Encontre e defina um listener de clique para o botão de exclusão
        Button btnExcluirProduto = findViewById(R.id.btnExcluirProduto);
        btnExcluirProduto.setOnClickListener(v -> {
            // Verifique se um item da lista está selecionado
            if (listView.getCheckedItemPosition() != ListView.INVALID_POSITION) {
                Produto produto = produtosList.get(listView.getCheckedItemPosition());
                deleteProduto(produto);
            } else {
                Toast.makeText(EliminarProdutosActivity.this, "Selecione um produto para excluir", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadProdutos() {
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                produtosList.clear();
                for (DataSnapshot produtoSnapshot : snapshot.getChildren()) {
                    String nomeProduto = produtoSnapshot.getKey();
                    String imageUrl = produtoSnapshot.child("imageUrl").getValue(String.class);
                    Produto produto = new Produto(nomeProduto, imageUrl);
                    produtosList.add(produto);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(EliminarProdutosActivity.this, "Erro ao carregar produtos", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showImagePreview(String imageUrl) {
        // Carregar a imagem usando Glide
        Glide.with(this)
                .load(imageUrl)
                .into(imagePreview);

        // Tornar a ImageView visível
        imagePreview.setVisibility(View.VISIBLE);
    }

    private void deleteProduto(Produto produto) {
        mDatabase.child(produto.getNome()).removeValue()
                .addOnSuccessListener(aVoid -> deleteImageFromStorage(produto))
                .addOnFailureListener(e -> Toast.makeText(EliminarProdutosActivity.this, "Erro ao deletar produto", Toast.LENGTH_SHORT).show());
    }

    private void deleteImageFromStorage(Produto produto) {
        // Lógica para excluir a imagem do Storage
        StorageReference storageRef = FirebaseStorage.getInstance().getReferenceFromUrl(produto.getImageUrl());
        storageRef.delete().addOnSuccessListener(aVoid -> {
            Toast.makeText(EliminarProdutosActivity.this, "Imagem deletada com sucesso", Toast.LENGTH_SHORT).show();
            // Após excluir a imagem, torne a ImageView invisível novamente
            imagePreview.setVisibility(View.GONE);
        }).addOnFailureListener(e -> {
            Toast.makeText(EliminarProdutosActivity.this, "Erro ao deletar imagem", Toast.LENGTH_SHORT).show();
        });
    }
}
