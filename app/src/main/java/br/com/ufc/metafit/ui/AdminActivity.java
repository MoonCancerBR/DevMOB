package br.com.ufc.metafit.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.skydoves.elasticviews.ElasticCheckButton;

import br.com.ufc.metafit.R;
import io.github.muddz.styleabletoast.StyleableToast;

public class AdminActivity extends AppCompatActivity {
    ElasticCheckButton recuperar, btnEnviar;
    TextInputEditText gmail, password;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        mAuth = FirebaseAuth.getInstance();
        recuperar = findViewById(R.id.recuperar);
        btnEnviar = findViewById(R.id.btnEnviar);

        gmail = findViewById(R.id.gmail);
        password = findViewById(R.id.password);

        getRecuperar();
        loginAdmin();
    }

    private void limpar() {
        gmail.setText("");
        password.setText("");
    }

    private void loginAdmin() {
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userE = gmail.getText().toString().trim();
                String passE = password.getText().toString().trim();
                if (TextUtils.isEmpty(userE)) {
                    StyleableToast.makeText(getApplicationContext(), "Insira um usuário", Toast.LENGTH_LONG, R.style.AllStyles).show();
                } else if (TextUtils.isEmpty(passE)) {
                    StyleableToast.makeText(getApplicationContext(), "Insira uma senha", Toast.LENGTH_LONG, R.style.AllStyles).show();
                } else {
                    mAuth.signInWithEmailAndPassword(userE, passE).addOnCompleteListener(AdminActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                StyleableToast.makeText(getApplicationContext(), "Credenciais Incorretas", Toast.LENGTH_LONG, R.style.AllStyles).show();
                            } else {
                                Intent i = new Intent(AdminActivity.this, MenuAdmActivity.class);
                                startActivity(i);
                            }
                        }
                    });
                    limpar();
                }
            }
        });
    }

    private void getRecuperar() {
        recuperar.setOnClickListener((v) -> {
            Intent i = new Intent(AdminActivity.this, RecuperaActivity.class);
            startActivity(i);
        });
    }
}
