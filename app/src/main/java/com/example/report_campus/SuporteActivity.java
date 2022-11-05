package com.example.report_campus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class SuporteActivity extends AppCompatActivity {

    private ImageButton bt_home, bt_lista, bt_voltar, bt_sair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suporte);

        iniciarComponentes();

        bt_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SuporteActivity.this,PrincipalActivity.class);
                startActivity(intent);
                finish();
            }
        });
        bt_lista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SuporteActivity.this,ReportesActivity.class);
                startActivity(intent);
                finish();
            }
        });
        bt_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SuporteActivity.this,ConfiguracoesActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void iniciarComponentes(){
        bt_home = findViewById(R.id.btHome2);
        bt_lista = findViewById(R.id.btLista2);
        bt_voltar = findViewById(R.id.btVoltar3);
        bt_sair = findViewById(R.id.btSair2);
    }
}