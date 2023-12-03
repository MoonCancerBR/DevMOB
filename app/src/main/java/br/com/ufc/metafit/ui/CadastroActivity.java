package br.com.ufc.metafit.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.skydoves.elasticviews.ElasticCheckButton;

import br.com.ufc.metafit.R;

public class CadastroActivity extends AppCompatActivity {

    User user;
    FirebaseAuth autenticacao;
    EditText Nome, Email, Senha1, Senha2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        inicializar();

        ElasticCheckButton btnCadastrar = findViewById(R.id.btnCadastrar);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validarCampos(view);
            }
        });
    }

    private void inicializar() {
        Nome = findViewById(R.id.nomeCadastro);
        Email = findViewById(R.id.emailCadastro);
        Senha1 = findViewById(R.id.senhaCadastro);
        Senha2 = findViewById(R.id.confirmarSenhaCadastro);
    }

    private void validarCampos(View v) {
        String nome = Nome.getText().toString().trim();
        String email = Email.getText().toString().trim();
        String senha1 = Senha1.getText().toString().trim();
        String senha2 = Senha2.getText().toString().trim();

        if (nome.isEmpty() && email.isEmpty() && senha1.isEmpty() && senha2.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
        } else if (nome.isEmpty()) {
            Toast.makeText(this, "Insira um nome", Toast.LENGTH_SHORT).show();
        } else if (email.isEmpty()) {
            Toast.makeText(this, "Insira um email", Toast.LENGTH_SHORT).show();
        } else if (senha1.isEmpty() || senha2.isEmpty()) {
            Toast.makeText(this, "Insira e confirme sua senha", Toast.LENGTH_SHORT).show();
        } else if (!senha1.equals(senha2)) {
            Toast.makeText(this, "As senhas não coincidem", Toast.LENGTH_SHORT).show();
        } else {
            // All fields are filled, and passwords match. Proceed with user registration.
            user = new User();
            user.setNome(nome);
            user.setEmail(email);
            user.setSenha(senha1);
            cadastrarUsuario();
        }
    }

    private void cadastrarUsuario() {
        autenticacao = Configuracoes.Firebaseautenticacao();
        autenticacao.createUserWithEmailAndPassword(user.getEmail(),user.getSenha()).addOnCompleteListener(this,new OnCompleteListener<AuthResult>(){
            @Override
            public void onComplete(@NonNull Task<AuthResult>task){
                if(task.isSuccessful()){
                    Toast.makeText(CadastroActivity.this, "Sucesso ao Cadastrar", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(CadastroActivity.this, "Erro na verificação", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
