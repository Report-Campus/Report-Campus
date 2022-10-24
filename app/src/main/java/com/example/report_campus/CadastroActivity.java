package com.example.report_campus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class CadastroActivity extends AppCompatActivity {

    private EditText edt_Nome, edt_Email, edt_Senha, edt_NomeFaculdade, edt_ConfirmarSenha;
    private ImageButton bt_Cadastrar;
    String[] mensagens = {"Preencha todos os campos", "Cadastro realizado com sucesso", "A senha e a confirmação da senha estão diferentes", "Erro no cadastro"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

//        getActionBar().hide();
        iniciarComponente();

        bt_Cadastrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String nome = edt_Nome.getText().toString();
                String email = edt_Email.getText().toString();
                String faculdade = edt_NomeFaculdade.getText().toString();
                String senha = edt_Senha.getText().toString();
                String confirmarSenha = edt_ConfirmarSenha.getText().toString();

                if(nome.isEmpty() || email.isEmpty() || faculdade.isEmpty() || senha.isEmpty() || confirmarSenha.isEmpty()){
                    Snackbar snackbar = Snackbar.make(v, mensagens[0], Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                } else if(!confirmarSenha.equals(senha) && !senha.equals(confirmarSenha)){
                    Snackbar snackbar = Snackbar.make(v, mensagens[2], Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                } else {
                    cadastrarUsuario(v);
                }
            }
        });
    }

    private void cadastrarUsuario(View v){

        String email = edt_Email.getText().toString();
        String senha = edt_Senha.getText().toString();

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Snackbar snackbar = Snackbar.make(v, mensagens[1], Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                } else {
                    Snackbar snackbar = Snackbar.make(v, mensagens[3], Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                }
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
    }

}