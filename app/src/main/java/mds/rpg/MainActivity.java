package mds.rpg;

import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import android.view.*;
import android.content.*;

import android.database.sqlite.*;
import android.database.*;

import mds.rpg.database.DataBase;
import mds.rpg.dominio.RepositorioFicha;
import mds.rpg.dominio.RepositorioRegistro;
import mds.rpg.dominio.entidade.Ficha;
import mds.rpg.dominio.entidade.Registro;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener,
        AdapterView.OnItemLongClickListener {

    private DataBase dataBase;
    private SQLiteDatabase connection;
    private ArrayAdapter<Ficha> adpFichas;

    private Ficha ficha;
    private Registro registro;

    private RepositorioFicha repositorioFicha;
    private RepositorioRegistro repositorioRegistro;

    private Button btn_new;
    private EditText edt_nomeFicha;
    private ListView listV1;

    private int ficha_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_new = (Button)findViewById(R.id.btn_new);
        btn_new.setOnClickListener(this);

        edt_nomeFicha = (EditText)findViewById(R.id.edt_nomeFicha);

        listV1 = (ListView)findViewById(R.id.listV1);
        listV1.setOnItemClickListener(this);
        listV1.setOnItemLongClickListener(this);

        ficha = new Ficha();
        try {

            dataBase = new DataBase(this);
            connection = dataBase.getWritableDatabase();

            repositorioFicha = new RepositorioFicha(connection);


            adpFichas = repositorioFicha.buscaFichas(this);
            listV1.setAdapter(adpFichas);

            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setMessage("Conexão criada com sucesso!");
            dlg.setNeutralButton("OK", null);
            dlg.show();

        }catch (SQLException ex){
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setMessage("Erro ao criar o Banco: " + ex.getMessage());
            dlg.setNeutralButton("OK", null);
            dlg.show();

        }

    }

    private void inserir(){
        try {
            ficha.setNome(edt_nomeFicha.getText().toString());

            repositorioFicha.inserir(ficha);
            ficha_id = repositorioFicha.idFicha();

        }catch (Exception ex){
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setMessage("Erro ao salvar os dados: " + ex.getMessage());
            dlg.setNeutralButton("OK", null);
            dlg.show();

        }
    }

    private void excluir(int pos){
        try {
            Ficha ficha = adpFichas.getItem(pos);
            repositorioFicha.excluir(ficha.getId());
        }catch (Exception ex){
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setMessage("Erro ao excluir os dados: " + ex.getMessage());
            dlg.setNeutralButton("OK", null);
            dlg.show();

        }
    }

    //Evento de click no botão
    @Override
    public void onClick(View v) {
        if (edt_nomeFicha.getText().toString().isEmpty()){
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setMessage("Necessário escolher o nome da nova ficha! ");
            dlg.setNeutralButton("OK", null);
            dlg.show();
        }
        else {
            inserir();
            Intent it = new Intent(this, FichaActivity.class);

            it.putExtra("FICHA_ID", ficha_id);

            startActivityForResult(it, 0);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        adpFichas = repositorioFicha.buscaFichas(this);
        listV1.setAdapter(adpFichas);
        edt_nomeFicha.setText("");
    }

    //Evento de Click na lista de fichas
    @Override
    public void onItemClick(AdapterView av, View v, int pos, long id){

        Ficha ficha = adpFichas.getItem(pos);
        Intent it = new Intent(this, FichaActivity.class);
        //System.out.println(ficha.getId());
        it.putExtra("FICHA", ficha);

        startActivityForResult(it, 0);
    }


    @Override
    public boolean onItemLongClick(AdapterView<?> parent, final View view, final int position, long id) {
        DialogInterface.OnClickListener dialogClick = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        excluir(position);
                        adpFichas = repositorioFicha.buscaFichas(view.getContext());
                        listV1.setAdapter(adpFichas);
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        break;
                }
            }
        };

        AlertDialog.Builder dlg = new AlertDialog.Builder(this);
        dlg.setMessage("Excluir a Ficha?").setNegativeButton("Não", dialogClick).setPositiveButton("Sim", dialogClick).show();
        return true;
    }
}
