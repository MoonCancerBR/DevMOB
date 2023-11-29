package br.com.ufc.metafit.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.ufc.metafit.R
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.skydoves.elasticviews.ElasticCheckButton

import io.github.muddz.styleabletoast.StyleableToast

class RecuperaActivity : AppCompatActivity() {
    private lateinit var gmail: TextInputEditText
    private lateinit var recuperar: ElasticCheckButton

    private lateinit var correio: String
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recupera)

        gmail = findViewById(R.id.gmail)
        recuperar = findViewById(R.id.recuperar)

        auth = FirebaseAuth.getInstance()


        getRecuperar()
    }

    private fun getRecuperar() {
        recuperar.setOnClickListener {
            correio = gmail.text.toString().trim()
            if (correio.isNotEmpty()) {

                getEnviarCorreio()
            } else {
                StyleableToast.makeText(
                    applicationContext,
                    "Não se pode enviar",
                    Toast.LENGTH_LONG,
                    R.style.ColoredBackground
                ).show()
            }
        }
    }

    private fun getEnviarCorreio() {
        auth.setLanguageCode("pt")
        auth.sendPasswordResetEmail(correio).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                StyleableToast.makeText(
                    applicationContext,
                    "Revise seu email para restaurar a senha",
                    Toast.LENGTH_LONG,
                    R.style.ColoredBackground
                ).show()
                val i = Intent(this@RecuperaActivity, AdminActivity::class.java)
                startActivity(i)
                finish()
            } else {
                StyleableToast.makeText(
                    applicationContext,
                    "Email não cadastrado",
                    Toast.LENGTH_LONG,
                    R.style.ColoredBackground
                ).show()
            }
        }
    }
}
