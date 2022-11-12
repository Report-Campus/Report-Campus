package com.example.report_campus.telas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.report_campus.R;
import com.google.firebase.auth.FirebaseAuth;

public class SobreActivity extends AppCompatActivity {

    private ImageButton bt_home, bt_lista, bt_voltar, bt_sair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobre);

        iniciarComponentes();

        bt_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SobreActivity.this,PrincipalActivity.class);
                startActivity(intent);
                finish();
            }
        });
        bt_lista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SobreActivity.this,ReportesActivity.class);
                startActivity(intent);
            }
        });
        bt_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SobreActivity.this,ConfiguracoesActivity.class);
                startActivity(intent);
                finish();
            }
        });
        bt_sair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(SobreActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void iniciarComponentes(){
        bt_home = findViewById(R.id.btHome5);
        bt_lista = findViewById(R.id.btLista5);
        bt_voltar = findViewById(R.id.btVoltar5);
        bt_sair = findViewById(R.id.btSair5);
    }
}