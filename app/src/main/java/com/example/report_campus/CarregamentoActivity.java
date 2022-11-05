package com.example.report_campus;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

public class CarregamentoActivity extends AppCompatActivity {

    private ProgressBar prg_bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carregamento);

        prg_bar = findViewById(R.id.prgBar);
        prg_bar.getIndeterminateDrawable().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
        prg_bar.setVisibility(View.VISIBLE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SobreActivity();
            }
        }, 3000);

    }

     private void SobreActivity(){
        Intent intent = new Intent(CarregamentoActivity.this,PrincipalActivity.class);
        startActivity(intent);
        finish();
    }

}
