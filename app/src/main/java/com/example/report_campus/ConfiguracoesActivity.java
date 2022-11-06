package com.example.report_campus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;

public class ConfiguracoesActivity extends AppCompatActivity {

    private ImageButton bt_home, bt_lista, bt_config, bt_sair, bt_editCadastro, bt_suporte, bt_sobreNos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracoes);

        iniciarComponentes();

        bt_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConfiguracoesActivity.this,PrincipalActivity.class);
                startActivity(intent);
                finish();
            }
        });
        bt_lista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConfiguracoesActivity.this,ReportesActivity.class);
                startActivity(intent);
                finish();
            }
        });
        bt_editCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConfiguracoesActivity.this,EditCadastroActivity.class);
                startActivity(intent);
                finish();
            }
        });
        bt_suporte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConfiguracoesActivity.this,SuporteActivity.class);
                startActivity(intent);
                finish();
            }
        });
        bt_sobreNos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConfiguracoesActivity.this,SobreActivity.class);
                startActivity(intent);
                finish();
            }
        });
        bt_sair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(ConfiguracoesActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void iniciarComponentes(){
        bt_home = findViewById(R.id.btHome1);
        bt_lista = findViewById(R.id.btLista1);
        bt_config = findViewById(R.id.btConfig1);
        bt_sair = findViewById(R.id.btSair1);
        bt_editCadastro = findViewById(R.id.btEditCadastro);
        bt_suporte = findViewById(R.id.btSuporte);
        bt_sobreNos = findViewById(R.id.btSobreNos);
    }
}