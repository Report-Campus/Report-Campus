package com.example.report_campus.telas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.report_campus.banco.ConnectionDB;
import com.example.report_campus.R;
import com.google.android.material.snackbar.Snackbar;

public class CadastroActivity extends AppCompatActivity {

    private EditText edt_Nome, edt_Email, edt_Senha, edt_NomeFaculdade, edt_ConfirmarSenha;
    private ImageButton bt_Cadastrar, bt_voltar;
    private String[] mensagens = {"Preencha todos os campos", "As senhas não coincidem"};
    private String usuarioID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        iniciarComponente();

        bt_Cadastrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String nome = edt_Nome.getText().toString();
                String email = edt_Email.getText().toString();
                String faculdade = edt_NomeFaculdade.getText().toString();
                String senha = edt_Senha.getText().toString();
                String confirmarSenha = edt_ConfirmarSenha.getText().toString();

                ConnectionDB conexao = new ConnectionDB();

                if(nome.isEmpty() || email.isEmpty() || faculdade.isEmpty() || senha.isEmpty() || confirmarSenha.isEmpty()){
                    Snackbar snackbar = Snackbar.make(v, mensagens[0], Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                } else if (!confirmarSenha.equals(senha)){
                    Snackbar snackbar = Snackbar.make(v, mensagens[1], Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                } else {
                    conexao.cadastrarUsuario(v, email, senha, nome, faculdade, usuarioID);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            telaLogin();
                        }
                    }, 3000);
                }
            }
        });
        bt_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                telaLogin();
            }
        });
    }

    private void iniciarComponente(){
        edt_Nome = findViewById(R.id.edtNome);
        edt_Email = findViewById(R.id.edtEmail);
        edt_Senha = findViewById(R.id.edtSenha);
        edt_ConfirmarSenha = findViewById(R.id.edtConfirmarSenha);
        edt_NomeFaculdade = findViewById(R.id.edtNomeFaculdade);
        bt_Cadastrar = findViewById(R.id.btCadastrar);
        bt_voltar = findViewById(R.id.btVoltar);
    }

    private void telaLogin(){
        Intent intent = new Intent(CadastroActivity.this,LoginActivity.class);
        startActivity(intent);
        finish();
    }



}