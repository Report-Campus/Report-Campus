package com.example.report_campus;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class PrincipalActivity extends AppCompatActivity {

    private String usuarioID;
    int idReporte = 0;
    private EditText edt_reporte;
    private ImageButton bt_home, bt_lista, bt_config, bt_sair, bt_enviar;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

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
                    idReporte++;

                    Map<String, Object> usuarios = new HashMap<>();
                    usuarios.put(Integer.toString(idReporte), reporte);

                    usuarioID = FirebaseAuth.getInstance().getCurrentUser().getUid();

                    db.collection(usuarioID).add(usuarios).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                        }
                    })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.w(TAG, "Error adding document", e);
                                }
                            });
        }
    });
    }

    private void iniciarComponentes(){
        edt_reporte = findViewById(R.id.edtReporte);
        bt_home = findViewById(R.id.btHome3);
        bt_lista = findViewById(R.id.btLista3);
        bt_config = findViewById(R.id.btConfig3);
        bt_sair = findViewById(R.id.btSair3);
        bt_enviar = findViewById(R.id.bt_enviar);
    }


}