package com.example.report_campus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText edtNome, edtEmail, edtSenha, edtNomeFaculdade, edtConfirmarSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtNome = findViewById(R.id.edtNome);
        edtEmail = findViewById(R.id.edtEmail);
        edtSenha = findViewById(R.id.edtSenha);
        edtNomeFaculdade = findViewById(R.id.edtNomeFaculdade);
        edtConfirmarSenha = findViewById(R.id.edtConfirmarSenha);

    }
}