package br.com.ufc.metafit.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import br.com.ufc.metafit.R;

public class MenuAdmActivity extends AppCompatActivity {
    CardView lista, caixa
            //edição abaixo
    ,camera, delete;
           //edição acima

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_adm);
        lista = findViewById(R.id.idvista);
        caixa = findViewById(R.id.idmapa);
        //edição abaixo?//
        camera = findViewById(R.id.idcamera);
        delete = findViewById(R.id.idDelete);
        //edição acima?//

        irLista();
        irMapa();
        getLocalizacao();

        //edição abaixo?//
        registro();
        EliminaRegistro();
        //edição acima?//

    }
//edição abaixo

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
//edição acima
    private void irLista() {
        lista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(MenuAdmActivity.this, ListaCaixasActivity.class);
                startActivity(i);
            }
        });
    }

    private void irMapa(){
        caixa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MenuAdmActivity.this, MapsActivity.class);
                startActivity(i);
            }
        });
    }
//Possivel erro abaixo
    private void getLocalizacao() {
        int permissao = ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
        );
        if (permissao == PackageManager.PERMISSION_DENIED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
            )
            ) {
                // Explicar ao usuário por que a permissão é necessária (opcional)
            } else {
                // Solicitar permissão
                ActivityCompat.requestPermissions(
                        this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        1
                );
            }
        }
    }

}
