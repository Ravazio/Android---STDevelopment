package br.com.senai.stdevelopment;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import br.com.senai.stdevelopment.modelo.Mensagem;
import br.com.senai.stdevelopment.modelo.MensagemDAO;

public class ContatoActivity extends AppCompatActivity {

    private ImageButton menu;
    private EditText mensagemw;
    private Button enviar;
    private AlertDialog.Builder dialog;
    private Button site;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contato);

        enviar = findViewById(R.id.btnEnviar);
        menu = findViewById(R.id.btnMenu);
        mensagemw = findViewById(R.id.edMensagem);

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContatoActivity.this, MenuActivity.class);
                startActivity(intent);
                finish();
            }
        });

        final ContatoHelper helper = new ContatoHelper(this);

        Intent extras = getIntent();
        final Mensagem mensagem = (Mensagem) getIntent().getSerializableExtra("mensagem");
        if (mensagem != null) {
            helper.prencherFormulario(mensagem);
        }

        site = findViewById(R.id.site);

        site.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Criar Dialog
                dialog = new AlertDialog.Builder(ContatoActivity.this);

                //Configurando o titulo
                dialog.setTitle("Entrando no site...");

                //Configurando a mensagem
                dialog.setMessage("Você permite abrir o Google Chrome para visitar o site?");

                //Botão Sim
                dialog.setNegativeButton("Fechar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                //Botão Sim
                dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Uri uri = Uri.parse("http://www.st.com/en/development-tools.html");
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                    }
                });
                dialog.create();
                dialog.show();
            }
        });

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    if(mensagemw.getText().length() == 0){
                        Toast.makeText(ContatoActivity.this, "Insira uma mensagem!", Toast.LENGTH_SHORT).show();
                    }else {
                        Mensagem mensagem = helper.pegaMensagem();
                        MensagemDAO dao = new MensagemDAO(ContatoActivity.this);
                        if (mensagem.getId() != null) {
                            dao.alterar(mensagem);
                        } else {
                            dao.inserir(mensagem);
                        }
                        dao.close();
                        //Criar Dialog
                        dialog = new AlertDialog.Builder(ContatoActivity.this);

                        //Configurando o titulo
                        dialog.setTitle("Mensagem salva!");

                        //Configurando a mensagem
                        dialog.setMessage("A sua mensagem foi enviada com sucesso!");

                        //Botão Sim
                        dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(ContatoActivity.this, ListaActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });
                        dialog.create();
                        dialog.show();
                    }
                }catch(Exception e){
                    Toast.makeText(getApplicationContext(),"Invalido!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}



