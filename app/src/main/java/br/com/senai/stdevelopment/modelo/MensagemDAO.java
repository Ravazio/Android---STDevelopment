package br.com.senai.stdevelopment.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import java.util.ArrayList;
import java.util.List;

public class MensagemDAO extends SQLiteOpenHelper {
    public MensagemDAO(Context context) {
        super(context, "Lista", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table Mensagem(id integer primary key, mensagem text);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "drop table if exists Mensagem";
        db.execSQL(sql);
    }

    public void inserir(Mensagem mensagem){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = getContentValues(mensagem);

        db.insert("Mensagem",null,dados);
    }

    @NonNull
    private ContentValues getContentValues(Mensagem mensagem) {
        ContentValues dados = new ContentValues();
        dados.put("mensagem", mensagem.getMensagem());
        return dados;
    }

    public List<Mensagem> buscaMensagem() {
        String sql = "select * from Mensagem";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql,null);
        List<Mensagem> mensagens = new ArrayList<Mensagem>();
        while(c.moveToNext()){
            Mensagem mensagem = new Mensagem();
            mensagem.setId(c.getLong(c.getColumnIndex("id")));
            mensagem.setMensagem(c.getString(c.getColumnIndex("mensagem")));
            mensagens.add(mensagem);
        }
        return mensagens;
    }

    public void deletar(Mensagem mensagem){
        SQLiteDatabase db = getWritableDatabase();
        String[] parametros = {String.valueOf(mensagem.getId())};
        db.delete("Mensagem","id = ?", parametros);
    }

    public void alterar(Mensagem mensagem){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados =  getContentValues(mensagem);
        String[] parametros = {mensagem.getId().toString()};
        db.update("Mensagem", dados, "id = ?", parametros);
    }
}