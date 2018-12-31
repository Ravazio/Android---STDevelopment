package br.com.senai.stdevelopment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class InformativaActivity extends AppCompatActivity {

    private ImageButton menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informativa);

        menu = findViewById(R.id.btnMenu);

       menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InformativaActivity.this,MenuActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
