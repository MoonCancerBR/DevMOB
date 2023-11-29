package br.com.ufc.metafit.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import android.view.WindowManager
import br.com.ufc.metafit.R
import android.content.pm.ActivityInfo

class MainActivity : AppCompatActivity() {
    private val DURA_SPLASH = 6000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        getSplash()
    }

    private fun getSplash() {
        Handler().postDelayed({
            val intent = Intent(this@MainActivity, MenuActivity::class.java)
            startActivity(intent)
            finish()
        }, DURA_SPLASH)
    }
}
