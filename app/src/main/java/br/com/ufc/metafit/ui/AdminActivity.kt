package br.com.ufc.metafit.ui

import android.content.Intent
import com.skydoves.elasticviews.ElasticCheckButton
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AlertDialog
import br.com.ufc.metafit.R
import com.google.firebase.auth.FirebaseAuth
import io.github.muddz.styleabletoast.StyleableToast
import com.google.android.material.textfield.TextInputEditText

class AdminActivity : AppCompatActivity() {

    private lateinit var recuperarsenha: ElasticCheckButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)
        supportActionBar?.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM)
        supportActionBar?.setCustomView(R.layout.text_titulo)

        recuperarsenha = findViewById(R.id.recuperarsenha) as ElasticCheckButton
        getRecuperar()
    }

    private fun getRecuperar() {
        recuperarsenha.setOnClickListener {
            val i = Intent(this@AdminActivity, RecuperaActivity::class.java)
            startActivity(i)
        }
    }
}
