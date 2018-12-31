package br.com.senai.stdevelopment;

import android.widget.EditText;
import android.widget.RatingBar;

import br.com.senai.stdevelopment.modelo.Mensagem;

public class ContatoHelper {
    public EditText mensagemDigitada;
    public Mensagem mensagem;

    public ContatoHelper(ContatoActivity activity){
        mensagemDigitada = activity.findViewById(R.id.edMensagem);
        mensagem = new Mensagem();
    }

    public Mensagem pegaMensagem() {
        mensagem.setMensagem(mensagemDigitada.getText().toString());

        return mensagem;
    }

    public void prencherFormulario(Mensagem mensagem) {
        mensagemDigitada.setText(mensagem.getMensagem());
        this.mensagem = mensagem;
    }
}
