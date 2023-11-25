package br.com.ufc.metafit.ui

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import br.com.ufc.metafit.R
import com.skydoves.elasticviews.ElasticCheckButton
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import io.github.muddz.styleabletoast.StyleableToast

class CaixaActivity : AppCompatActivity() {

    lateinit var databaseReference: DatabaseReference
    lateinit var consultarLatLong: ElasticCheckButton
    lateinit var btnGuardar: ElasticCheckButton
    lateinit var edtLat: EditText
    lateinit var edtLong: EditText
    lateinit var edtCodigo: EditText
    lateinit var edtBilhete: EditText
    lateinit var edtTelefone: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_caixa)
        databaseReference = FirebaseDatabase.getInstance().getReference()
        edtLat = findViewById(R.id.txtlat)
        edtLong = findViewById(R.id.txtlog)
        edtCodigo = findViewById(R.id.txtNo)
        edtBilhete = findViewById(R.id.txtBilhete)
        edtTelefone = findViewById(R.id.txtTelefone)
        consultarLatLong = findViewById(R.id.consultarLatLong)
        btnGuardar = findViewById(R.id.btnEnviar)
        getLocalizacao()
        getCarregarLocalizacao()
        guardarDatos()
    }

    private fun guardarDatos() {
        btnGuardar.setOnClickListener {
            val latitud = edtLat.text.toString().trim()
            val logitud = edtLong.text.toString().trim()
            val codigo = edtCodigo.text.toString().trim()
            val telefone = edtTelefone.text.toString().trim()
            val bilhete = edtBilhete.text.toString().trim()

            if (TextUtils.isEmpty(latitud) || TextUtils.isEmpty(logitud)) {
                StyleableToast.makeText(applicationContext,"Gere a localização primeiro",Toast.LENGTH_LONG,R.style.AllStyles).show()
            }
            else if(TextUtils.isEmpty(codigo)){
                StyleableToast.makeText(applicationContext,"Insira um código",Toast.LENGTH_LONG,R.style.AllStyles).show()
            }
            else if(TextUtils.isEmpty(bilhete)){
                StyleableToast.makeText(applicationContext,"Insira um bilhete",Toast.LENGTH_LONG,R.style.AllStyles).show()
            }
            else if(TextUtils.isEmpty(telefone)){
                StyleableToast.makeText(applicationContext,"Insira um telefone",Toast.LENGTH_LONG,R.style.AllStyles).show()
            }
            else{
                val destinos = Destinos(latitud.toDouble(), logitud.toDouble(), codigo.toDouble(), bilhete.toDouble(), telefone.toDouble())
                databaseReference.child("destinos").child(codigo).setValue(destinos)
                StyleableToast.makeText(applicationContext,"Dados enviados com sucesso",Toast.LENGTH_LONG,R.style.AllStyles).show()
                val i = Intent(this@CaixaActivity, MenuActivity::class.java)
                startActivity(i)
                finish()
            }
        }
    }

    private fun getCarregarLocalizacao() {
        consultarLatLong.setOnClickListener {
            val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
            val locationListener = object : LocationListener {
                override fun onLocationChanged(location: Location) {
                    edtLat.setText("" + location.latitude)
                    edtLong.setText("" + location.longitude)
                }

                override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}

                override fun onProviderEnabled(provider: String) {}

                override fun onProviderDisabled(provider: String) {}
            }
            val permissao = ContextCompat.checkSelfPermission(
                this@CaixaActivity,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
            locationManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER,
                0,
                0.toFloat(),
                locationListener
            )
            StyleableToast.makeText(applicationContext,"Localização Gerada",Toast.LENGTH_LONG,R.style.AllStyles).show()
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
