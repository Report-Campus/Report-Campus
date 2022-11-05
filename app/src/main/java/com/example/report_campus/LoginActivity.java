package com.example.report_campus;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText edt_email, edt_senha;
    private ImageButton imgb_login, imgb_cadastro;
    private String[] mensagens = {"Login efetuado com sucesso"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        iniarComponentes();

        ConnectionDB conexao = new ConnectionDB();

        imgb_cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,CadastroActivity.class);
                startActivity(intent);
            }
        });

        imgb_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edt_email.getText().toString();
                String senha = edt_senha.getText().toString();

                if (email.isEmpty() || senha.isEmpty()){
                    Snackbar snackbar = Snackbar.make(v, "Preencha todos os campos", Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                } else {
                    autenticarUsuario(v, email, senha);
                }
            }
        });
    }

    private void iniarComponentes(){
        edt_email = findViewById(R.id.edtEmail2);
        edt_senha = findViewById(R.id.edtSenha2);
        imgb_login = findViewById(R.id.imgbLogin);
        imgb_cadastro = findViewById(R.id.imgbCadastro);
    }

    public void autenticarUsuario(View v, String email, String senha){

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    telaCarregamneto();
                } else {
                    String erro;
                    try {
                        throw task.getException();
                    } catch (Exception e) {
                        erro = "Erro ao logar o usuário";
                    }
                    Snackbar snackbar = Snackbar.make(v, erro, Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                }
            }
        });
    }

    private void telaCarregamneto(){
            Intent intent = new Intent(LoginActivity.this, CarregamentoActivity.class);
            startActivity(intent);
            finish();
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//
//        FirebaseUser usuarioAtual = FirebaseAuth.getInstance().getCurrentUser();
//
//        if (usuarioAtual != null){
//            telaCarregamneto();
//        }
//    }
}
