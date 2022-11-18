package com.example.report_campus.telas;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.report_campus.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class PrincipalActivity extends AppCompatActivity {

    private String usuarioID;
    private EditText edt_reporte, edt_titulo;
    private ImageButton bt_lista, bt_config, bt_sair, bt_enviar, bt_apagar;
    private FirebaseFirestore conexao = FirebaseFirestore.getInstance();

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
        bt_sair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(PrincipalActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        bt_enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    String reporte = edt_reporte.getText().toString();
                    String titulo = edt_titulo.getText().toString();
                    if (!reporte.isEmpty() || !titulo.isEmpty()) {
                        Map<String, Object> reportes = new HashMap<>();
                        reportes.put("Titulo", titulo);
                        reportes.put("Reporte", reporte);

                        usuarioID = FirebaseAuth.getInstance().getCurrentUser().getUid();
                        DocumentReference documentReference = conexao.collection("Usuário").document(usuarioID).collection("Reportes").document();
                        documentReference.set(reportes).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Log.d("db", "Sucesso ao salvar os reporte");
                                Snackbar snackbar = Snackbar.make(view, "Reporte enviado com sucesso", Snackbar.LENGTH_SHORT);
                                snackbar.setBackgroundTint(Color.WHITE);
                                snackbar.setTextColor(Color.BLACK);
                                snackbar.show();

                                edt_titulo.setText("");
                                edt_reporte.setText("");
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.d("db_error", "Erro ao salvar os dados" + e.toString());
                            }
                        });
                    } else {
                        Snackbar snackbar = Snackbar.make(view, "Preencha todos os campos", Snackbar.LENGTH_SHORT);
                        snackbar.setBackgroundTint(Color.WHITE);
                        snackbar.setTextColor(Color.BLACK);
                        snackbar.show();
                    }

        }
    });

        bt_apagar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                edt_reporte.setText("");
                edt_titulo.setText("");
            }
        });
    }

    private void iniciarComponentes(){
        edt_reporte = findViewById(R.id.edtReporte);
        edt_titulo = findViewById(R.id.edtTitulo);
        bt_lista = findViewById(R.id.btLista3);
        bt_config = findViewById(R.id.btConfig3);
        bt_sair = findViewById(R.id.btSair3);
        bt_enviar = findViewById(R.id.bt_enviar);
        bt_apagar = findViewById(R.id.btApagar);
    }


}