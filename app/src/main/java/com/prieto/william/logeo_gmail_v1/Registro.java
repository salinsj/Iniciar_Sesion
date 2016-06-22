package com.prieto.william.logeo_gmail_v1;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registro extends AppCompatActivity {

    EditText etUsuarioR,etContraseñaR,etRepetirR,etVista,etCorreo;
    Button bEnviar,bcancelar;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        etUsuarioR=(EditText)findViewById(R.id.etUsuarioR);
        etContraseñaR=(EditText)findViewById(R.id.etContraseñaR);
        etRepetirR=(EditText)findViewById(R.id.etRepetirR);
        etCorreo=(EditText)findViewById(R.id.etemail);
        bEnviar=(Button)findViewById(R.id.bEnviar);
        bcancelar=(Button)findViewById(R.id.bcancelar);
        etVista=(EditText)findViewById(R.id.etVista);

       /* TuCita tuCita=new TuCita(Registro.this);
        db = tuCita.getWritableDatabase();

        Cursor c = db.rawQuery("SELECT clases FROM Categorias",null);
        if(c.moveToFirst())
            do{
                String codigo=c.getString(0);

                etVista.append(" "+codigo+" // ");
            }while (c.moveToNext());*/

        bEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etUsuarioR.getText().toString().equals("") == true || etContraseñaR.getText().toString().equals("") == true
                        || etRepetirR.getText().toString().equals("") == true || etCorreo.getText().toString().equals("") == true)
                    Toast.makeText(Registro.this, "Todos Los Campos Son Obligatorios", Toast.LENGTH_LONG).show();
                else if (etContraseñaR.getText().toString().equals(etRepetirR.getText().toString()) == false)
                    Toast.makeText(Registro.this, "Las contraseñan no coinciden", Toast.LENGTH_LONG).show();
                else {
                    String usuario = etUsuarioR.getText().toString();
                    String clave = etContraseñaR.getText().toString();
                    String correo = etCorreo.getText().toString();

                    TuCita tuCita = new TuCita(Registro.this);
                    db=tuCita.getWritableDatabase();

                    ContentValues nuevoRegistro = new ContentValues();
                    nuevoRegistro.put("usuario", usuario);
                    nuevoRegistro.put("clave", clave);
                    nuevoRegistro.put("correo", correo);
                    db.insert("UsuariosCliente", null, nuevoRegistro);


                    Toast.makeText(Registro.this, "Usuario Creado con exito", Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });

        bcancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
