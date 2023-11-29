package br.com.ufc.metafit.ui

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import br.com.ufc.metafit.R

class MenuAdmActivity : AppCompatActivity() {
    private lateinit var caixa: CardView
    private lateinit var lista: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_adm)

        lista = findViewById(R.id.idvista)
        caixa = findViewById(R.id.idmapa)

        irLista()
        irMapa()
        getLocalizacao()
    }

    private fun irLista() {
        lista.setOnClickListener {
            val i = Intent(this@MenuAdmActivity, ListaCaixasActivity::class.java)
            startActivity(i)
        }
    }

    private fun irMapa() {
        caixa.setOnClickListener {
            val i = Intent(this@MenuAdmActivity, MapsActivity::class.java)
            startActivity(i)
    }
}
    private fun getLocalizacao() {
        val permissao = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
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
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    1
                )
            }
        }
    }
}
