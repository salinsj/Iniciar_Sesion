package com.prieto.william.logeo_gmail_v1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ValidacionEmp extends AppCompatActivity {
    Button bEnviarvalida, bcancelarvalida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validacion_emp);
        bEnviarvalida=(Button )findViewById(R.id.bEnviarvalida);
        bcancelarvalida=(Button )findViewById(R.id.bcancelarvalida);

        bEnviarvalida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ValidacionEmp.this, "Empresa", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(ValidacionEmp.this,Empresa.class);
                startActivity(intent);
                finish();
            }
        });

        bcancelarvalida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ValidacionEmp.this, "cliente", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(ValidacionEmp.this,Principal.class);
                startActivity(intent);
                finish();
            }
        });
        //-----------------boton para salir de la aplicacion
//-----------------boton para salir de la aplicacion

    }
    /*
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
            Toast.makeText(ValidacionEmp.this, "Empresa", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this,Empresa.class);
            startActivity(intent);
        }
        if(id==R.id.mCliente){
            Toast.makeText(ValidacionEmp.this, "cliente", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(ValidacionEmp.this,Principal.class);
            startActivity(intent);
        }
        if(id==R.id.mCerrarSesion){
            Toast.makeText(ValidacionEmp.this, "validar", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(ValidacionEmp.this,ValidacionEmp.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
*/

}
