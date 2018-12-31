package br.com.senai.stdevelopment;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.nio.file.Files;

public class LoginActivity extends AppCompatActivity {

    private Button enviar;
    private EditText usuario;
    private EditText senha;
    private AlertDialog.Builder dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        enviar = findViewById(R.id.btnEnviar);
        usuario = findViewById(R.id.edUsuario);
        senha = findViewById(R.id.edSenha);

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            try{
                if(usuario.getText().length() == 0 || senha.getText().length() == 0) {
                    Toast.makeText(getApplicationContext(),"Insira as informações!", Toast.LENGTH_SHORT).show();
                }else{
                    //Criar Dialog
                    dialog = new AlertDialog.Builder(LoginActivity.this);

                    //Configurando o titulo
                    dialog.setTitle("Login realizado!");

                    //Configurando a mensagem
                    dialog.setMessage("Bem-vindo, " + usuario.getText().toString() + ".");

                    //Botão Sim
                    dialog.setPositiveButton("Entrar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(LoginActivity.this,MenuActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    });
                    dialog.create();
                    dialog.show();
                }
            }catch(Exception e){
                Toast.makeText(getApplicationContext(),"Inválido!", Toast.LENGTH_LONG).show();
            }
            }
        });
    }
}