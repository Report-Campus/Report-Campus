package com.example.report_campus;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class PrincipalActivity extends AppCompatActivity {

    private EditText edtCaixaTexto;
    private ImageButton bt_home, bt_lista, bt_config, bt_sair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        iniciarComponentes();

        bt_lista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PrincipalActivity.this,ReportesActivity.class);
                startActivity(intent);
            }
        });
        bt_config.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PrincipalActivity.this,ConfiguracoesActivity.class);
                startActivity(intent);
            }
        });

    }

    private void iniciarComponentes(){
        edtCaixaTexto = findViewById(R.id.edtCaixaTexto);
        bt_home = findViewById(R.id.btHome3);
        bt_lista = findViewById(R.id.btLista3);
        bt_config = findViewById(R.id.btConfig3);
        bt_sair = findViewById(R.id.btSair3);
    }

}