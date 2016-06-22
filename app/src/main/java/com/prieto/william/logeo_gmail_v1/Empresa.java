package com.prieto.william.logeo_gmail_v1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class Empresa extends AppCompatActivity {
    private ViewPager mViewPager;
    private ActionBar actionBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empresa);

        //-----------------boton para salir de la aplicacion


//-------------------------------------------------------------
//-------------------------------------------------------------
        //creando el objeto viewpager
        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.pagere);
        mViewPager.setAdapter(pagerAdapter);

//activando el action bar
        actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
//se crea un tab listener que se llama cuando se cambia de tab
        ActionBar.TabListener tabListener = new ActionBar.TabListener() {
            @Override
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
                mViewPager.setCurrentItem(tab.getPosition());// se activa el fragmen seleccionado
            }

            @Override
            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

            }

            @Override
            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

            }
        };

//se agregan los tab autilizar
        // ActionBar.Tab tab = actionBar.newTab().setTabListener(tabListener).setIcon(R.drawable.turi1);
        // actionBar.addTab(tab);


        ActionBar.Tab tab = actionBar.newTab().setText("Perfil").setTabListener(tabListener);
        actionBar.addTab(tab);

        tab=actionBar.newTab().setText("Agenda").setTabListener(tabListener);
        actionBar.addTab(tab);

        tab=actionBar.newTab().setText("Citas").setTabListener(tabListener);
        actionBar.addTab(tab);

        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            public void onPageSelected(int position){
                getSupportActionBar().setSelectedNavigationItem(position);
            }

        });



//-------------------------------------------------------------
        actionBar.setTitle("Empresa");
    }

    //---------------------------------------------------------
    public class PagerAdapter extends FragmentPagerAdapter {
        public PagerAdapter(android.support.v4.app.FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position){
                case 0: return new PerfilempresaFragment();
                case 1: return new CitasusuarioFragment();
                case 2: return new AgendaempresaFragment();
                default: return null;
            }

        }

        @Override
        public int getCount() {
            return 3;
        }
    }
//---------------------------------------------------------



    // para crear el menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        // return super.onCreateOptionsMenu(menu);
        return true;
    }
    //para seleccionar la opcion
    //para seleccionar la opcion
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //return super.onOptionsItemSelected(item);
        int id=item.getItemId();

        if(id==R.id.mEmpresa){
           /* Toast.makeText(Empresa.this, "Empresa", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(Empresa.this,Empresa.class);
            startActivity(intent);*/
        }
        if(id==R.id.mCliente){
            Toast.makeText(Empresa.this, "cliente", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(Empresa.this,Principal.class);
            startActivity(intent);
            finish();
        }
        if(id==R.id.mCerrarSesion){
            Intent ant = new Intent();
            ant.putExtra("bandera",true);
            setResult(RESULT_OK, ant);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
