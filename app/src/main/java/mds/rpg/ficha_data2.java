package mds.rpg;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import mds.rpg.database.DataBase;
import mds.rpg.dominio.RepositorioRegistro;
import mds.rpg.dominio.entidade.Registro;

import static mds.rpg.FichaActivity.registro;

/**
 * Created by rboli on 15/12/2016.
 */

public class ficha_data2 extends Fragment implements View.OnClickListener {

    private static DataBase dataBase;
    private static SQLiteDatabase connection;

    //private static RepositorioRegistro repositorioRegistro;
    //private static Registro registro = new Registro();

    public static EditText edt_pv;
    public static EditText edt_pvAtual;
    private static EditText edt_dano;
    public static EditText edt_ca;
    public static EditText edt_iniciativa;
    public static EditText edt_baseAtaque;
    public static EditText edt_fortitude;
    public static EditText edt_reflexo;
    public static EditText edt_vontade;


    private static ImageButton btn_addDano;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.ficha_data2, container, false);

        dataBase = new DataBase(rootView.getContext());
        connection = dataBase.getWritableDatabase();

        //repositorioRegistro = new RepositorioRegistro(connection);

        edt_pv = (EditText)rootView.findViewById(R.id.edt_pv);
        edt_pvAtual = (EditText)rootView.findViewById(R.id.edt_pvAtual);
        edt_dano = (EditText)rootView.findViewById(R.id.edt_dano);
        edt_ca = (EditText)rootView.findViewById(R.id.edt_ca);
        edt_iniciativa = (EditText)rootView.findViewById(R.id.edt_iniciativa);
        edt_baseAtaque = (EditText)rootView.findViewById(R.id.edt_baseAtaque);
        edt_fortitude = (EditText)rootView.findViewById(R.id.edt_fortitude);
        edt_reflexo = (EditText)rootView.findViewById(R.id.edt_reflexo);
        edt_vontade = (EditText)rootView.findViewById(R.id.edt_vontade);

        btn_addDano = (ImageButton)rootView.findViewById(R.id.btn_addDano);
        btn_addDano.setOnClickListener(this);

        //Set Enable
        edt_pv.setEnabled(false);
        edt_pvAtual.setEnabled(false);
        edt_pvAtual.setText(edt_pv.getText());
        edt_ca.setEnabled(false);
        edt_ca.setText("10");
        edt_iniciativa.setEnabled(false);
        edt_baseAtaque.setEnabled(false);
        edt_fortitude.setEnabled(false);
        edt_reflexo.setEnabled(false);
        edt_vontade.setEnabled(false);

        Bundle bundle = getArguments();

        if (bundle != null) {
            preencherDados(bundle.getInt("ID"));
        }


        return rootView;
    }

    public static void preencherDados(int ficha_id) {
        boolean achou = false;
        Cursor cursor = connection.query("REGISTRO", null, null, null, null, null, null);

        cursor.moveToFirst();

        while (!achou) {
            if (cursor.getInt(1) == ficha_id) {
                achou = true;
                edt_pv.setText(cursor.getString(19));
                edt_pvAtual.setText(cursor.getString(20));
                //Registrando o ID do registro
                registro.setId(cursor.getInt(0));
            } else cursor.moveToNext();
        }
    }


    public static void salvar(int ficha_id) {
        registro.setId_ficha(ficha_id);
        registro.setPv(edt_pv.getText().toString());
        registro.setPv_Atual(edt_pvAtual.getText().toString());

    /*
        if (registro.getId() == 0) {
            repositorioRegistro.inserir(registro);
        } else {
            repositorioRegistro.alterar(registro);
        }
    */
    }


    @Override
    public void onClick(View v) {
        float dano, pvAtual;

        switch (v.getId()) {
            case R.id.btn_addDano:
                dano = Float.valueOf(edt_dano.getText().toString());
                pvAtual = Float.valueOf(edt_pvAtual.getText().toString());
                pvAtual += dano;
                edt_pvAtual.setText(String.valueOf(String.format("%.0f",pvAtual)));
                break;

            default:
                break;
        }
    }
}
