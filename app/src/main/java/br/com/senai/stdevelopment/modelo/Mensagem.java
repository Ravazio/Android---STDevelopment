package br.com.senai.stdevelopment.modelo;


import java.io.Serializable;

public class Mensagem implements Serializable{
    private Long id;
    private String mensagem;

    @Override
    public String toString() {
        return getId() + "-" + getMensagem();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
