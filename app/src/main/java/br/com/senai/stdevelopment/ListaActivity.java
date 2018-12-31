package br.com.senai.stdevelopment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.com.senai.stdevelopment.modelo.Mensagem;
import br.com.senai.stdevelopment.modelo.MensagemDAO;

public class ListaActivity extends AppCompatActivity {

    private ListView listaDeMensagens;
    private ImageButton menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        listaDeMensagens = findViewById(R.id.Lista);

        carregarLista();

        menu = findViewById(R.id.btnMenu);

        menu.bringToFront();

        listaDeMensagens.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Mensagem mensagem = (Mensagem) listaDeMensagens.getItemAtPosition(position);

                Intent intent = new Intent(ListaActivity.this, ContatoActivity.class);
                intent.putExtra("mensagem", mensagem);
                startActivity(intent);
            }
        });

        menu.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListaActivity.this,MenuActivity.class);
                startActivity(intent);
                finish();
            }
        });
        registerForContextMenu(listaDeMensagens);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem deletar = menu.add("Deletar");

        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Mensagem mensagem = (Mensagem) listaDeMensagens.getItemAtPosition(info.position);

                MensagemDAO dao = new MensagemDAO(ListaActivity.this);
                dao.deletar(mensagem);
                dao.close();
                carregarLista();
                Toast.makeText(getApplicationContext(),"Mensagem removida!", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    private void carregarLista() {
        MensagemDAO dao = new MensagemDAO(this);

        List<Mensagem> mensagens = dao.buscaMensagem();
        ArrayAdapter<Mensagem> adaptador = new ArrayAdapter<Mensagem>(this,android.R.layout.simple_list_item_1,mensagens);
        listaDeMensagens.setAdapter(adaptador);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregarLista();
    }
}

