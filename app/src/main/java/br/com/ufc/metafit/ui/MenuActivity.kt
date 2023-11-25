package br.com.ufc.metafit.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.cardview.widget.CardView
import br.com.ufc.metafit.R

class MenuActivity : AppCompatActivity() {

    private lateinit var caixa: CardView
    private lateinit var admin: CardView
    private lateinit var ofertas: CardView
    private lateinit var condicoes: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.customView = layoutInflater.inflate(R.layout.text_titulo, null)

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
}
