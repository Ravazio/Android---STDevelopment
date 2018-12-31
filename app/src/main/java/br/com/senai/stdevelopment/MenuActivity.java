package br.com.senai.stdevelopment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MenuActivity extends AppCompatActivity {

    private ImageButton info;
    private ImageButton quem;
    private ImageButton contato;
    private ImageButton lista;
    private Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        logout = findViewById(R.id.logout);
        info = findViewById(R.id.btnInfo);
        quem = findViewById(R.id.btnQuem);
        contato = findViewById(R.id.btnContato);
        lista = findViewById(R.id.btnLista);

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this,InformativaActivity.class);
                startActivity(intent);

            }
        });

        quem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this,HistoriaActivity.class);
                startActivity(intent);

            }
        });

        contato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this,ContatoActivity.class);
                startActivity(intent);

            }
        });

        lista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this,ListaActivity.class);
                startActivity(intent);

            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
