package mds.rpg.dominio;

import android.content.ContentValues;
import android.content.Context;
import android.database.*;
import android.database.sqlite.*;
import android.widget.*;

import mds.rpg.dominio.entidade.Registro;

/**
 * Created by rboli on 19/12/2016.
 */

public class RepositorioRegistro {

    private SQLiteDatabase connection;

    public RepositorioRegistro(SQLiteDatabase connection){

        this.connection = connection;
    }

    private ContentValues preencheContentValues(Registro registro){
        ContentValues values = new ContentValues();

        values.put("id_Ficha", registro.getId_ficha());
        values.put("NOME", registro.getNome());
        values.put("RAÇA", registro.getRaca());
        values.put("CLASSE", registro.getClasse());
        values.put("NIVEL", registro.getNivel());
        values.put("EXP_TOTAL", registro.getExp_total());
        values.put("FORCA", registro.getForca());
        values.put("DESTREZA", registro.getDestreza());
        values.put("CONSTITUICAO", registro.getConstituicao());
        values.put("INTELIGENCIA", registro.getInteligencia());
        values.put("SABEDORIA", registro.getSabedoria());
        values.put("CARISMA", registro.getCarisma());
        values.put("MOD_FORCA", registro.getMod_forca());
        values.put("MOD_DESTREZA", registro.getMod_destreza());
        values.put("MOD_CONSTITUICAO", registro.getMod_constituicao());
        values.put("MOD_INTELIGENCIA", registro.getMod_inteligencia());
        values.put("MOD_SABEDORIA", registro.getMod_sabedoria());
        values.put("MOD_CARISMA", registro.getMod_carisma());
        values.put("PV", registro.getPv());
        values.put("PV_ATUAL", registro.getPv_Atual());

        return values;
    }

    public void inserir(Registro registro){

        ContentValues values = preencheContentValues(registro);
        connection.insertOrThrow("REGISTRO", null, values);
    }

    public void alterar(Registro registro){

        ContentValues values = preencheContentValues(registro);
        connection.update("REGISTRO", values, " id_Ficha = ? ", new String[]{ String.valueOf(registro.getId_ficha()) });
    }

}
