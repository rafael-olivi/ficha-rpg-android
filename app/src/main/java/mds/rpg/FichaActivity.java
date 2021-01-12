package mds.rpg;

import android.app.Activity;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import mds.rpg.database.DataBase;
import mds.rpg.dominio.RepositorioFicha;
import mds.rpg.dominio.RepositorioRegistro;
import mds.rpg.dominio.entidade.Ficha;
import mds.rpg.dominio.entidade.Registro;

public class FichaActivity extends AppCompatActivity {

    private static DataBase dataBase;
    private static SQLiteDatabase connection;
    private static RepositorioFicha repositorioFicha;
    public static RepositorioRegistro repositorioRegistro;
    private static Ficha ficha;
    public static Registro registro;
    private static FragmentManager fragmentManager;

    private ficha_data1 f1 = new ficha_data1();
    private ficha_data2 f2 = new ficha_data2();
    private ficha_data3 f3 = new ficha_data3();

    /*
    public static int pv_global;
    public static int mod_for;
    public static int mod_dest;
    public static int mod_const;
    public static int mod_int;
    public static int mod_sab;
    public static int mod_car;
    */
    private int ficha_id;
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ficha);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        try{
            dataBase = new DataBase(this);
            connection = dataBase.getWritableDatabase();

            repositorioFicha = new RepositorioFicha(connection);
            repositorioRegistro = new RepositorioRegistro(connection);

            registro = new Registro();

        }catch (SQLException ex){
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setMessage("Erro ao criar o Banco: " + ex.getMessage());
            dlg.setNeutralButton("OK", null);
            dlg.show();
        }

        //Carregar o id do da ficha
        Bundle bundle = getIntent().getExtras();

        //Caso o registro está sendo alterado
        if ((bundle != null) && (bundle.containsKey("FICHA"))){
            ficha = (Ficha)bundle.getSerializable("FICHA");
            bundle.putInt("ID", ficha.getId());
            ficha_id = ficha.getId();
            f1.setArguments(bundle);
            f2.setArguments(bundle);

        }// Caso o registro esta sendo criado
        else if ((bundle != null) && (bundle.containsKey("FICHA_ID"))){
            ficha = new Ficha();
            ficha_id = bundle.getInt("FICHA_ID");
        }

    }

    //Update da Ficha
    public void update(int mod_for, int mod_dest, int mod_const, int mod_int, int mod_sab, int mod_car){

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ficha, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()){
            case R.id.mini_acao1:

                ficha_data1.salvar(ficha_id);
                ficha_data2.salvar(ficha_id);

                if (registro.getId() == 0) {
                    repositorioRegistro.inserir(registro);
                } else {
                    repositorioRegistro.alterar(registro);
                }

                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        DialogInterface.OnClickListener dialogClick = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        ficha_data1.salvar(ficha_id);
                        ficha_data2.salvar(ficha_id);

                        if (registro.getId() == 0) {
                            repositorioRegistro.inserir(registro);
                        } else {
                            repositorioRegistro.alterar(registro);
                        }

                        finish();
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        finish();
                        break;
                }
            }
        };

        AlertDialog.Builder dlg = new AlertDialog.Builder(this);
        dlg.setMessage("Deseja Salvar?").setNegativeButton("Não", dialogClick).setPositiveButton("Sim", dialogClick).show();
    }

    /**
   Deleted placeHolder
   */


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
        //return the current tabs
            switch (position) {
                case 0:
                    //ficha_data1 f1 = new ficha_data1();
                    return f1;
                case 1:
                    //ficha_data2 f2 = new ficha_data2();
                    return f2;
                case 2:
                    //ficha_data3 f3 = new ficha_data3();
                    return f3;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
                case 2:
                    return "SECTION 3";
            }
            return null;
        }
    }

}
