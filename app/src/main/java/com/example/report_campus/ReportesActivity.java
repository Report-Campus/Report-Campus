package com.example.report_campus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;

public class ReportesActivity extends AppCompatActivity {

    private ImageButton bt_home, bt_lista, bt_config, bt_sair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportes);

        iniciarComponentes();

        bt_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReportesActivity.this,PrincipalActivity.class);
                startActivity(intent);
                finish();
            }
        });
        bt_config.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReportesActivity.this,ConfiguracoesActivity.class);
                startActivity(intent);
                finish();
            }
        });
        bt_sair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(ReportesActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void iniciarComponentes(){
        bt_home = findViewById(R.id.btHome4);
        bt_lista = findViewById(R.id.btLista4);
        bt_config = findViewById(R.id.btConfig4);
        bt_sair = findViewById(R.id.btSair4);
    }
}