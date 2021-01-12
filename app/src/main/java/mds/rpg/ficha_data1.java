package mds.rpg;

/**
 * Created by rboli on 15/12/2016.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.Image;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import android.view.*;

import android.net.Uri;

import mds.rpg.database.DataBase;
import mds.rpg.dominio.RepositorioRegistro;
import mds.rpg.dominio.entidade.Ficha;
import mds.rpg.dominio.entidade.Registro;
import mds.rpg.ficha_data2;

import static mds.rpg.FichaActivity.registro;

public class ficha_data1 extends Fragment implements View.OnClickListener, Spinner.OnItemSelectedListener,
        View.OnFocusChangeListener {

    private static DataBase dataBase;
    private static SQLiteDatabase connection;

    private static final int PICK_IMAGE = 100;
    Uri imageURI;

    private ArrayAdapter<Registro> adpRegistro;

    private static EditText edt_nome;
    private static EditText edt_nivel;
    private static EditText edt_exp;
    private static EditText edt_expObtido;
    private static EditText edt_forca;
    private static EditText edt_destreza;
    private static EditText edt_const;
    private static EditText edt_int;
    private static EditText edt_sab;
    private static EditText edt_car;
    private static EditText edt_modForca;
    private static EditText edt_modDestreza;
    private static EditText edt_modConst;
    private static EditText edt_modInt;
    private static EditText edt_modSab;
    private static EditText edt_modCar;

    private static Spinner spn_raca;
    private static Spinner spn_classe;

    private static ImageButton btn_addExp;

    private ArrayAdapter<String> adpTipoRaca;
    private ArrayAdapter<String> adpTipoClasse;

    private static ImageButton imgb_Foto;

    private static Ficha ficha;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.ficha_data1, container, false);

        dataBase = new DataBase(rootView.getContext());
        connection = dataBase.getWritableDatabase();

        //repositorioRegistro = new RepositorioRegistro(connection);

        edt_nome = (EditText) rootView.findViewById(R.id.edt_nome);
        edt_exp = (EditText) rootView.findViewById(R.id.edt_exp);
        edt_expObtido = (EditText) rootView.findViewById(R.id.edt_expObtido);
        edt_nivel = (EditText) rootView.findViewById(R.id.edt_nivel);
        edt_forca = (EditText) rootView.findViewById(R.id.edt_forca);
        edt_destreza = (EditText) rootView.findViewById(R.id.edt_destreza);
        edt_const = (EditText) rootView.findViewById(R.id.edt_const);
        edt_int = (EditText) rootView.findViewById(R.id.edt_int);
        edt_sab = (EditText) rootView.findViewById(R.id.edt_sab);
        edt_car = (EditText) rootView.findViewById(R.id.edt_car);
        edt_modForca = (EditText) rootView.findViewById(R.id.edt_modForca);
        edt_modDestreza = (EditText) rootView.findViewById(R.id.edt_modDes);
        edt_modConst = (EditText) rootView.findViewById(R.id.edt_modConst);
        edt_modInt = (EditText) rootView.findViewById(R.id.edt_modInt);
        edt_modSab = (EditText) rootView.findViewById(R.id.edt_modSab);
        edt_modCar = (EditText) rootView.findViewById(R.id.edt_modCar);


        spn_classe = (Spinner) rootView.findViewById(R.id.spn_classe);
        spn_classe.setOnItemSelectedListener(this);

        spn_raca = (Spinner) rootView.findViewById(R.id.spn_raca);
        spn_raca.setOnItemSelectedListener(this);

        btn_addExp = (ImageButton) rootView.findViewById(R.id.btn_addExp);
        btn_addExp.setOnClickListener(this);

        adpTipoClasse = new ArrayAdapter<>(rootView.getContext(), android.R.layout.simple_spinner_item);
        adpTipoClasse.setDropDownViewResource(android.R.layout.simple_spinner_item);

        adpTipoRaca = new ArrayAdapter<>(rootView.getContext(), android.R.layout.simple_spinner_item);
        adpTipoRaca.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        imgb_Foto = (ImageButton)rootView.findViewById(R.id.imgb_Foto);
        imgb_Foto.setOnClickListener(this);

        //Adicionando as Classes e Racas
        adpTipoClasse.add("Guerreiro");
        adpTipoClasse.add("Bárbaro");
        adpTipoClasse.add("Bardo");
        adpTipoClasse.add("Clérigo");
        adpTipoClasse.add("Druida");
        adpTipoClasse.add("Feiticeiro");
        adpTipoClasse.add("Ladino");
        adpTipoClasse.add("Monge");
        adpTipoClasse.add("Ranger");
        adpTipoClasse.add("Paladino");
        adpTipoClasse.add("Mago");

        adpTipoRaca.add("Humano");
        adpTipoRaca.add("Elfo");
        adpTipoRaca.add("Anão");
        adpTipoRaca.add("Gnomo");
        adpTipoRaca.add("Halfling");
        adpTipoRaca.add("Meio-Elfo");
        adpTipoRaca.add("Meio-Orc");
        //--------------

        //Configurando os edts
        spn_classe.setAdapter(adpTipoClasse);
        spn_raca.setAdapter(adpTipoRaca);
        edt_nivel.setText("1");
        edt_exp.setText("0");
        edt_nivel.setEnabled(false);
        edt_exp.setEnabled(false);
        edt_modForca.setEnabled(false);
        edt_modForca.setText("0");
        edt_modDestreza.setEnabled(false);
        edt_modDestreza.setText("0");
        edt_modConst.setEnabled(false);
        edt_modConst.setText("0");
        edt_modInt.setEnabled(false);
        edt_modInt.setText("0");
        edt_modSab.setEnabled(false);
        edt_modSab.setText("0");
        edt_modCar.setEnabled(false);
        edt_modCar.setText("0");

        //Configurar o modificador de habilidade
        edt_nome.setOnFocusChangeListener(this);
        edt_expObtido.setOnFocusChangeListener(this);
        edt_forca.setOnFocusChangeListener(this);
        edt_destreza.setOnFocusChangeListener(this);
        edt_const.setOnFocusChangeListener(this);
        edt_int.setOnFocusChangeListener(this);
        edt_sab.setOnFocusChangeListener(this);
        edt_car.setOnFocusChangeListener(this);


        Bundle bundle = getArguments();

        if (bundle != null) {
            preencherDados(bundle.getInt("ID"));
        }

        return rootView;
    }

    public static void update(){

    }


    public static void preencherDados(int ficha_id) {
        boolean achou = false;
        Cursor cursor = connection.query("REGISTRO", null, null, null, null, null, null);

        cursor.moveToFirst();

        while (!achou) {
            if (cursor.getInt(1) == ficha_id) {
                achou = true;
                edt_nome.setText(cursor.getString(2));
                spn_raca.setSelection(Integer.parseInt(cursor.getString(3)));
                spn_classe.setSelection(Integer.parseInt(cursor.getString(4)));
                edt_exp.setText(cursor.getString(5));
                edt_nivel.setText(cursor.getString(6));
                edt_forca.setText(cursor.getString(7));
                edt_destreza.setText(cursor.getString(8));
                edt_const.setText(cursor.getString(9));
                edt_int.setText(cursor.getString(10));
                edt_sab.setText(cursor.getString(11));
                edt_car.setText(cursor.getString(12));
                edt_modForca.setText(cursor.getString(13));
                edt_modDestreza.setText(cursor.getString(14));
                edt_modConst.setText(cursor.getString(15));
                edt_modInt.setText(cursor.getString(16));
                edt_modSab.setText(cursor.getString(17));
                edt_modCar.setText(cursor.getString(18));

                //Registrando o ID do registro
                registro.setId(cursor.getInt(0));
            } else cursor.moveToNext();
        }
    }

    public static void salvar(int ficha_id) {
        registro.setId_ficha(ficha_id);
        registro.setNome(edt_nome.getText().toString());
        registro.setClasse(String.valueOf(spn_classe.getSelectedItemPosition()));
        registro.setRaca(String.valueOf(spn_raca.getSelectedItemPosition()));
        registro.setNivel(edt_nivel.getText().toString());
        registro.setExp_total(edt_exp.getText().toString());
        registro.setForca(edt_forca.getText().toString());
        registro.setDestreza(edt_destreza.getText().toString());
        registro.setConstituicao(edt_const.getText().toString());
        registro.setInteligencia(edt_int.getText().toString());
        registro.setSabedoria(edt_sab.getText().toString());
        registro.setCarisma(edt_car.getText().toString());
        registro.setMod_forca(edt_modForca.getText().toString());
        registro.setMod_destreza(edt_modDestreza.getText().toString());
        registro.setMod_constituicao(edt_modConst.getText().toString());
        registro.setMod_inteligencia(edt_modInt.getText().toString());
        registro.setMod_sabedoria(edt_modSab.getText().toString());
        registro.setMod_carisma(edt_modCar.getText().toString());
    /*
        if (registro.getId() == 0) {
            repositorioRegistro.inserir(registro);
        } else {
            repositorioRegistro.alterar(registro);
        }
    */
    }

    public void tipoRaca(int pos) {
        String tipo = adpTipoRaca.getItem(pos);
        switch (tipo) {
            case "Humano":

                break;

            case "Anão":

                break;

            case "Elfo":

                break;

            case "Gnomo":

                break;

            case "Halfling":

                break;

            case "Meio-Elfo":

                break;

            case "Meio-Orc":

                break;

            default:
                break;
        }
    }

    public void tipoClasse(int pos) {
        String tipo = adpTipoClasse.getItem(pos);
        int pv = 0, nivel;
        String s_baseAtaque;
        int mod_const = Integer.valueOf(edt_modConst.getText().toString());
        switch (tipo) {
            case "Guerreiro":
                // PV
                pv = 10 + mod_const;
                ficha_data2.edt_pv.setText(String.valueOf(pv));
                ficha_data2.edt_pvAtual.setText(String.valueOf(pv));
                //Bonus Base de Ataque Bom
                nivel = Integer.valueOf(edt_nivel.getText().toString());
                s_baseAtaque = BBA_Bom(nivel);
                ficha_data2.edt_baseAtaque.setText(s_baseAtaque);
                break;

            case "Bárbaro":
                pv = 12 + mod_const;
                ficha_data2.edt_pv.setText(String.valueOf(pv));
                ficha_data2.edt_pvAtual.setText(String.valueOf(pv));
                //Bonus Base de Ataque Bom
                nivel = Integer.valueOf(edt_nivel.getText().toString());
                s_baseAtaque = BBA_Bom(nivel);
                ficha_data2.edt_baseAtaque.setText(s_baseAtaque);
                break;

            case "Bardo":
                pv = 6 + mod_const;
                ficha_data2.edt_pv.setText(String.valueOf(pv));
                ficha_data2.edt_pvAtual.setText(String.valueOf(pv));
                //Bonus Base de Ataque Medio
                nivel = Integer.valueOf(edt_nivel.getText().toString());
                s_baseAtaque = BBA_Medio(nivel);
                ficha_data2.edt_baseAtaque.setText(s_baseAtaque);
                break;

            case "Clérigo":
                pv = 8 + mod_const;
                ficha_data2.edt_pv.setText(String.valueOf(pv));
                ficha_data2.edt_pvAtual.setText(String.valueOf(pv));
                //Bonus Base de Ataque Medio
                nivel = Integer.valueOf(edt_nivel.getText().toString());
                s_baseAtaque = BBA_Medio(nivel);
                ficha_data2.edt_baseAtaque.setText(s_baseAtaque);
                break;

            case ("Druida"):
                pv = 8 + mod_const;
                ficha_data2.edt_pv.setText(String.valueOf(pv));
                ficha_data2.edt_pvAtual.setText(String.valueOf(pv));
                //Bonus Base de Ataque Medio
                nivel = Integer.valueOf(edt_nivel.getText().toString());
                s_baseAtaque = BBA_Medio(nivel);
                ficha_data2.edt_baseAtaque.setText(s_baseAtaque);
                break;

            case ("Feiticeiro"):
                pv = 4 + mod_const;
                ficha_data2.edt_pv.setText(String.valueOf(pv));
                ficha_data2.edt_pvAtual.setText(String.valueOf(pv));
                //Bonus Base de Ataque Ruim
                nivel = Integer.valueOf(edt_nivel.getText().toString());
                s_baseAtaque = BBA_Ruim(nivel);
                ficha_data2.edt_baseAtaque.setText(s_baseAtaque);
                break;

            case "Ladino":
                pv = 6 + mod_const;
                ficha_data2.edt_pv.setText(String.valueOf(pv));
                ficha_data2.edt_pvAtual.setText(String.valueOf(pv));
                //Bonus Base de Ataque Medio
                nivel = Integer.valueOf(edt_nivel.getText().toString());
                s_baseAtaque = BBA_Medio(nivel);
                ficha_data2.edt_baseAtaque.setText(s_baseAtaque);
                break;

            case "Monge":
                pv = 8 + mod_const;
                ficha_data2.edt_pv.setText(String.valueOf(pv));
                ficha_data2.edt_pvAtual.setText(String.valueOf(pv));
                //Bonus Base de Ataque Medio
                nivel = Integer.valueOf(edt_nivel.getText().toString());
                s_baseAtaque = BBA_Medio(nivel);
                ficha_data2.edt_baseAtaque.setText(s_baseAtaque);
                break;

            case "Ranger":
                pv = 8 + mod_const;
                ficha_data2.edt_pv.setText(String.valueOf(pv));
                ficha_data2.edt_pvAtual.setText(String.valueOf(pv));
                //Bonus Base de Ataque Bom
                nivel = Integer.valueOf(edt_nivel.getText().toString());
                s_baseAtaque = BBA_Bom(nivel);
                ficha_data2.edt_baseAtaque.setText(s_baseAtaque);
                break;

            case "Paladino":
                pv = 10 + mod_const;
                ficha_data2.edt_pv.setText(String.valueOf(pv));
                ficha_data2.edt_pvAtual.setText(String.valueOf(pv));
                //Bonus Base de Ataque Bom
                nivel = Integer.valueOf(edt_nivel.getText().toString());
                s_baseAtaque = BBA_Bom(nivel);
                ficha_data2.edt_baseAtaque.setText(s_baseAtaque);
                break;

            case "Mago":
                pv = 4 + mod_const;
                ficha_data2.edt_pv.setText(String.valueOf(pv));
                ficha_data2.edt_pvAtual.setText(String.valueOf(pv));
                //Bonus Base de Ataque Ruim
                nivel = Integer.valueOf(edt_nivel.getText().toString());
                s_baseAtaque = BBA_Ruim(nivel);
                ficha_data2.edt_baseAtaque.setText(s_baseAtaque);
                break;

            default:
                break;
        }
    }

    @Override
    public void onClick(View v) {
        int exp, nivel;

        switch (v.getId()) {
            case R.id.btn_addExp:
                exp = Integer.valueOf(edt_exp.getText().toString());
                exp += Integer.valueOf(edt_expObtido.getText().toString());
                edt_exp.setText(String.valueOf(exp));

                // A cada 1000 exp o nivel sobe em 1
                if ((exp % 1000) == 0) {
                    nivel = (exp / 1000) + 1;
                    edt_nivel.setText(String.valueOf(nivel));
                }

                edt_expObtido.setText("");
                break;

            case R.id.imgb_Foto:
                openGallery();
                break;

            default:
                break;
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()){
            case R.id.spn_raca:
                tipoRaca(position);
                break;

            case R.id.spn_classe:
                tipoClasse(position);
                break;

            default:
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void openGallery(){
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == Activity.RESULT_OK && requestCode == PICK_IMAGE){
            imageURI = data.getData();
            imgb_Foto.setImageURI(imageURI);
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        int value, mod_v, ca;
        switch (v.getId()){
            case (R.id.edt_forca):
                if (!edt_forca.getText().toString().isEmpty()) {
                    value = Integer.valueOf(edt_forca.getText().toString());
                    mod_v = (value/2) - 5;
                    edt_modForca.setText(String.valueOf(mod_v));
                }
                break;
            case (R.id.edt_destreza):
                if (!edt_destreza.getText().toString().isEmpty()) {
                    value = Integer.valueOf(edt_destreza.getText().toString());
                    mod_v = (value/2) - 5;
                    edt_modDestreza.setText(String.valueOf(mod_v));
                }
                break;
            case (R.id.edt_const):
                if (!edt_const.getText().toString().isEmpty()) {
                    value = Integer.valueOf(edt_const.getText().toString());
                    mod_v = (value/2) - 5;
                    edt_modConst.setText(String.valueOf(mod_v));
                }
                break;
            case (R.id.edt_int):
                if (!edt_int.getText().toString().isEmpty()) {
                    value = Integer.valueOf(edt_int.getText().toString());
                    mod_v = (value/2) - 5;
                    edt_modInt.setText(String.valueOf(mod_v));
                }
                break;
            case (R.id.edt_sab):
                if (!edt_sab.getText().toString().isEmpty()) {
                    value = Integer.valueOf(edt_sab.getText().toString());
                    mod_v = (value/2) - 5;
                    edt_modSab.setText(String.valueOf(mod_v));
                }
                break;
            case (R.id.edt_car):
                if (!edt_car.getText().toString().isEmpty()) {
                    value = Integer.valueOf(edt_car.getText().toString());
                    mod_v = (value/2) - 5;
                    edt_modCar.setText(String.valueOf(mod_v));
                }
                break;

                //Verificar Nivel
            case (R.id.edt_expObtido):

                break;
            default:
                break;
        }

        // Update
        tipoClasse(spn_classe.getSelectedItemPosition());

        ca = 10 + Integer.valueOf(edt_modDestreza.getText().toString());
        ficha_data2.edt_ca.setText(String.valueOf(ca));

        ficha_data2.edt_iniciativa.setText(edt_modDestreza.getText().toString());
    }

    //Funcao para calcular o Bonus Base de Ataque (BOM) de acordo com o Nivel
    public String BBA_Bom(int nivel){
        String s="";
        if(nivel % 5 == 0){
            s += String.valueOf(nivel);
            if (nivel >= 10){
                s += "/" + BBA_Bom(nivel-5);
            }
        }
        else if (nivel % 5 >= 1){
            s += String.valueOf(nivel);
            s += "/" + BBA_Bom(nivel-5);
        }
        return s;
    }

    //Funcao para calcular o Bonus Base de Ataque (MEDIO) de acordo com o Nivel
    public String BBA_Medio(int nivel){
        String s="";
        if(nivel == 1)
            s = "0";
        else if ((nivel == 2) || (nivel == 3))
            s += String.valueOf(nivel-1);
        else if(nivel % 7 == 0){
            if (nivel >= 14){
                s += String.valueOf(nivel - ((nivel / 4) + 1));
                s += "/" + BBA_Medio(nivel-7);
            }
            else if((nivel % 4 == 0) || (nivel % 4 == 1)){
                s +=  String.valueOf((nivel/4) * 3);
            }
            else if (nivel % 4 == 2 || (nivel % 4 == 3)){
                s += String.valueOf(nivel - ((nivel / 4) + 1));
            }

        }
        else if (nivel % 7 >= 1){
            if(nivel % 4 == 0){
                s +=  String.valueOf((nivel/4) * 3);
                s += "/" + BBA_Medio((nivel+1) -7);
            }
            else if (nivel % 4 == 1 ){
                s +=  String.valueOf((nivel/4) * 3);
                s += "/" + BBA_Medio((nivel) -7);
            }
            else if ((nivel % 4 == 2) || (nivel % 4 == 3)){
                s +=  String.valueOf(nivel - ((nivel/4) + 1));
                s += "/" + BBA_Medio(nivel-7);
            }
        }
        return s;
    }

    //Funcao para calcular o Bonus Base de Ataque (RUIM) de acordo com o Nivel
    public String BBA_Ruim(int nivel){
        String s = "";
        if(nivel == 1)
            s = "0";
        else if(nivel <= 11) {
            s += String.valueOf(nivel / 2);
        }
        else if(nivel > 11){
            s += String.valueOf(nivel / 2);
            s += "/" + BBA_Ruim(nivel-10);
        }
        return s;
    }
}


