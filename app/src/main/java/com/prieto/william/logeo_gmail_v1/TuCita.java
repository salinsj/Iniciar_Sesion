package com.prieto.william.logeo_gmail_v1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by daniel on 21/06/2016.
 */
public class TuCita extends SQLiteOpenHelper {

    private String Tabla_Usuario_Cliente = "CREATE TABLE UsuariosCliente(nombre text,usuario text,clave text,correo text,celular text)";

    private String Tabla_Categorias = "CREATE TABLE Categorias(clases text)";

    private String Tabla_Categoria_Empresa ="CREATE TABLE Empresas(codigo text,correo text,empresa text," +
            "categoria text, telefono text, ciudad text, direccion text)";

    private String Tabla_Citas_Disponibles ="CREATE TABLE CitasEmpresa(codigo text,administrador text,empresa text,fecha text,hora text," +
            "estado text,correoadmin text,cliente text,correocliente text,celular text)";


    String Categorias_Pre = "INSERT INTO Categorias(clases)" +
           " VALUES('Deportes'),('Educacion'),('Recreacion'),('Comidas'),('Salud'),('Gobierno')";

    String Empresas_Pre ="INSERT INTO Empresas(empresa, categoria) VALUES" +
            "('Liga Antioqueña de karate','Deportes'),('Unidad Deportiva de Belen','Deportes'),('Colegio Mayor de Antioquia','Educacion')," +
            "('Universidad Metropolitana','Educacion'),('Escuela de Buxeo','Deportes')";

    String Ciras_Pre ="INSERT INTO CitasEmpresa(empresa, hora, fecha) VALUES" +
            "('Unidad Deportiva de Belen','8:00','12/08/2016'),('Unidad Deportiva de Belen','8:30','16/12/2016')," +
            "('Unidad Deportiva de Belen','15:45','23/10/2016'),('Unidad Deportiva de Belen','14:00','19/05/2016')";



    public TuCita(Context context) {
        super(context, "BDtuCita", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(Tabla_Usuario_Cliente);
        db.execSQL(Tabla_Categorias);
        db.execSQL(Tabla_Categoria_Empresa);
        db.execSQL(Categorias_Pre);
        db.execSQL(Empresas_Pre);
        db.execSQL(Tabla_Citas_Disponibles);
        db.execSQL(Ciras_Pre);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

