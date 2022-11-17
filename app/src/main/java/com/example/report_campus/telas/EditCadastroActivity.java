package com.example.report_campus.telas;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.report_campus.banco.ConnectionDB;
import com.example.report_campus.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class EditCadastroActivity extends AppCompatActivity {

    private EditText edt_nome, edt_email, edt_faculdade;
    private ImageButton bt_home, bt_lista, bt_voltar, bt_sair, bt_salvar;
    private FirebaseFirestore conexao = FirebaseFirestore.getInstance();
    private String usuarioID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editcadastro);

        iniciarComponentes();

        bt_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditCadastroActivity.this,PrincipalActivity.class);
                startActivity(intent);
                finish();
            }
        });
        bt_lista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditCadastroActivity.this,ReportesActivity.class);
                startActivity(intent);
                finish();
            }
        });
        bt_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditCadastroActivity.this,ConfiguracoesActivity.class);
                startActivity(intent);
                finish();
            }
        });
        bt_sair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(EditCadastroActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        bt_salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nome = edt_nome.getText().toString();
                String faculdade = edt_faculdade.getText().toString();

                ConnectionDB conexao = new ConnectionDB();

                if(nome.isEmpty() || faculdade.isEmpty()){
                    Snackbar snackbar = Snackbar.make(view, "Preencha todos os campos", Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                } else {
                    conexao.salvarDadosUsuario(usuarioID, nome, faculdade);
                    Snackbar snackbar = Snackbar.make(view, "Dados salvos com sucesso", Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();

        usuarioID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DocumentReference document = conexao.collection("Usuário").document(usuarioID);
        document.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if (value != null){
                    edt_nome.setText(value.getString("nome"));
                    edt_faculdade.setText(value.getString("faculdade"));
                    edt_email.setText(email);
                }
            }
        });
    }

    private void iniciarComponentes(){
        bt_home = findViewById(R.id.btHome);
        bt_lista = findViewById(R.id.btLista);
        bt_voltar = findViewById(R.id.btVoltar2);
        bt_sair = findViewById(R.id.btSair);
        edt_nome = findViewById(R.id.edtNome3);
        edt_email = findViewById(R.id.edtEmail3);
        edt_faculdade = findViewById(R.id.edtNomeFaculdade2);
        bt_salvar = findViewById(R.id.btSalvar);
    }
}