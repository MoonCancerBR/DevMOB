package br.com.ufc.metafit.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.ufc.metafit.R
import androidx.appcompat.app.ActionBar
class MenuAdmActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_adm)
        supportActionBar?.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM)
        supportActionBar?.setCustomView(R.layout.text_titulo)

    }
}