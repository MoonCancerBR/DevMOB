package br.com.ufc.metafit.ui;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.cardview.widget.CardView;
import br.com.ufc.metafit.R;

//class MenuActivity : AppCompatActivity() {
//
//    private lateinit var caixa: CardView
//    private lateinit var admin: CardView
//    private lateinit var ofertas: CardView
//    private lateinit var condicoes: CardView
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_menu)
//
//        caixa = findViewById(R.id.idcard1)
//        admin = findViewById(R.id.idcard2)
//        ofertas = findViewById(R.id.idcard3)
//        condicoes = findViewById(R.id.idcard4)
//        caixa.setOnClickListener {
//            startActivity(Intent(this, CaixaActivity::class.java))
//        }
//
//        admin.setOnClickListener {
//            startActivity(Intent(this, AdminActivity::class.java))
//        }
//
//        ofertas.setOnClickListener {
//            startActivity(Intent(this, OfertaActivity::class.java))
//        }
//
//        condicoes.setOnClickListener {
//            startActivity(Intent(this, RulerActivity::class.java))
//        }
//    }
//}
public class MenuActivity extends AppCompatActivity {
    CardView caixa, admin, ofertas,condicoes;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        caixa = (CardView) findViewById(R.id.idcard1);
        admin = (CardView) findViewById(R.id.idcard2);
        ofertas = (CardView) findViewById(R.id.idcard3);
        condicoes = (CardView) findViewById(R.id.idcard4);

        getCaixa();
        getAdmin();
        getOferta();
        getCondicoes();

    }
    private void getCaixa(){
        caixa.setOnClickListener((v)->{
            Intent i = new Intent(MenuActivity.this, CaixaActivity.class);
            startActivity(i);
        });
    }

    private void getAdmin(){
        caixa.setOnClickListener((v)->{
            Intent i = new Intent(MenuActivity.this, AdminActivity.class);
            startActivity(i);
        });
    }

    private void getOferta(){
        caixa.setOnClickListener((v)->{
            Intent i = new Intent(MenuActivity.this, OfertaActivity.class);
            startActivity(i);
        });
    }

    private void getCondicoes(){
        caixa.setOnClickListener((v)->{
            Intent i = new Intent(MenuActivity.this, RulerActivity.class);
            startActivity(i);
        });
    }

}
