package br.com.ufc.metafit.ui

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import br.com.ufc.metafit.R
import io.github.muddz.styleabletoast.StyleableToast

class MenuActivity : AppCompatActivity() {

    private lateinit var caixa: CardView
    private lateinit var admin: CardView
    private lateinit var ofertas: CardView
    private lateinit var condicoes: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        caixa = findViewById(R.id.idcard1)
        admin = findViewById(R.id.idcard2)
        ofertas = findViewById(R.id.idcard3)
        condicoes = findViewById(R.id.idcard4)

        caixa.setOnClickListener {
            startActivity(Intent(this, CaixaActivity::class.java))
        }

        admin.setOnClickListener {
            startActivity(Intent(this, AdminActivity::class.java))
        }

        ofertas.setOnClickListener {
            startActivity(Intent(this, OfertaActivity::class.java))
        }

        condicoes.setOnClickListener {
            startActivity(Intent(this, RulerActivity::class.java))
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.phone -> {
                val permissao = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
                if (permissao != PackageManager.PERMISSION_GRANTED) {
                    StyleableToast.makeText(
                        applicationContext,
                        "Sem permissões de chamada",
                        Toast.LENGTH_LONG,
                        R.style.AllStyles
                    ).show()
                    ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE), 255)
                } else {
                    val numero = "85992997887"
                    val inicio = "tel:$numero"
                    val i = Intent(Intent.ACTION_CALL)
                    i.data = Uri.parse(inicio)
                    startActivity(i)
                }

                return true
            }
            R.id.zap -> {
                val numero = "5585992997887"
                val uri = Uri.parse("https://api.whatsapp.com/send?phone=$numero")
                val i = Intent(Intent.ACTION_VIEW, uri)
                startActivity(i)
                return true
            }
            R.id.termos -> {
                val uri = Uri.parse("https://politicasadefinir.000webhostapp.com/")
                val i = Intent(Intent.ACTION_VIEW, uri)
                startActivity(i)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}
