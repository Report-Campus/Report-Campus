package com.example.report_campus.telas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.report_campus.R;
import com.example.report_campus.model.Reporte;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class ReportesActivity extends AppCompatActivity {

    private ImageButton bt_home, bt_lista, bt_config, bt_sair;
    private RecyclerView rv;
    private FirebaseFirestore db;
    private FirestoreRecyclerAdapter adapter;
    private String usuarioID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportes);

        usuarioID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        db = FirebaseFirestore.getInstance();
        iniciarComponentes();

        Query query = db.collection("Usuário").document(usuarioID).collection("Reportes");
        FirestoreRecyclerOptions<Reporte> options = new FirestoreRecyclerOptions.Builder<Reporte>()
                .setQuery(query, Reporte.class)
                        .build();

        adapter = new FirestoreRecyclerAdapter<Reporte, ProductsViewHolder>(options) {

            @NonNull
            @Override
            public ProductsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.reporte_item, parent, false);
                return new ProductsViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull ProductsViewHolder holder, int position, @NonNull Reporte model) {
                holder.txt_Titulo.setText(model.getTitulo());
                holder.txt_Reporte.setText(model.getReporte());
            }
        };

        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);

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
        rv = findViewById(R.id.listReporte);
    }

    private class ProductsViewHolder extends RecyclerView.ViewHolder{

        TextView txt_Titulo, txt_Reporte;

        public ProductsViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_Titulo = itemView.findViewById(R.id.txtTitulo);
            txt_Reporte = itemView.findViewById(R.id.txtReporte);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }
}