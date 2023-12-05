package br.com.ufc.metafit.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import br.com.ufc.metafit.R;

public class MenuAdmActivity extends AppCompatActivity {
    CardView lista, caixa, camera, delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_adm);
        lista = findViewById(R.id.idvista);
        caixa = findViewById(R.id.idmapa);
        camera = findViewById(R.id.idcamera);
        delete = findViewById(R.id.idDelete);

        if (!isAuthenticated()) {
            redirectToLogin();
        }

        irLista();
        irMapa();
        registro();
        EliminaRegistro();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Realiza logout ao fechar o aplicativo
        logout();
    }

    private boolean isAuthenticated() {
        // Verifica se há uma sessão de autenticação válida
        SharedPreferences preferences = getSharedPreferences("auth", MODE_PRIVATE);
        return preferences.getBoolean("authenticated", false);
    }

    private void redirectToLogin() {
        // Se não estiver autenticado, redireciona para a tela de login
        Intent intent = new Intent(MenuAdmActivity.this, AdminActivity.class);
        startActivity(intent);
        finish(); // Encerra a atividade atual
    }

    private void irLista() {
        lista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MenuAdmActivity.this, ListaCaixasActivity.class);
                startActivity(i);
            }
        });
    }

    private void irMapa() {
        caixa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MenuAdmActivity.this, MapsActivity.class);
                startActivity(i);
            }
        });
    }

    private void EliminaRegistro() {
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MenuAdmActivity.this, EliminarProdutosActivity.class);
                startActivity(i);
            }
        });
    }

    private void registro() {
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MenuAdmActivity.this, RegistrarProdutosActivity.class);
                startActivity(i);
            }
        });
    }

    private void logout() {
        // Limpa a sessão ao fechar o aplicativo
        SharedPreferences preferences = getSharedPreferences("auth", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove("authenticated");
        editor.apply();
    }
}
