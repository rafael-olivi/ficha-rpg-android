package mds.rpg.dominio;

import android.content.ContentValues;
import android.content.Context;
import android.database.*;
import android.database.sqlite.*;
import android.widget.*;

import mds.rpg.dominio.entidade.Ficha;

/**
 * Created by rboli on 17/12/2016.
 */

public class RepositorioFicha {

    private SQLiteDatabase connection;


    public RepositorioFicha(SQLiteDatabase connection){

        this.connection = connection;
    }


    public void inserir(Ficha ficha){
        ContentValues values = new ContentValues();
        values.put("NOME", ficha.getNome());

        connection.insertOrThrow("FICHA", null, values);
    }

    public void excluir(int id){
        connection.delete("REGISTRO", " id_ficha = ? ", new String[]{ String.valueOf(id)});
        connection.delete("FICHA", " id = ? ", new String[]{ String.valueOf(id)});
    }

    public ArrayAdapter<Ficha> buscaFichas(Context context){

        ArrayAdapter<Ficha> adpFichas = new ArrayAdapter<Ficha>(context, android.R.layout.simple_list_item_1);

        Cursor cursor = connection.query("FICHA", null, null, null, null, null, null);

        if(cursor.getCount() > 0) {

            cursor.moveToFirst();

            do {

                Ficha ficha = new Ficha();

                ficha.setId(cursor.getInt(0));
                ficha.setNome(cursor.getString(1));
                adpFichas.add(ficha);

            } while (cursor.moveToNext());
        }

        return adpFichas;

    }

    public int idFicha(){
        Cursor cursor = connection.query("FICHA", null, null, null, null, null, null);

        cursor.moveToLast();

        return cursor.getInt(0);
    }
}
