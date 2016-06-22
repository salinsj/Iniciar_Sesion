package com.prieto.william.logeo_gmail_v1;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class CitasusuarioFragment extends Fragment {

    ListView lst;
    Button bAtras;
    SQLiteDatabase db;
    String[] items1,items2,items3;
    int i=0;
    ArrayAdapter<String> adaptador;
    int indicador=0;

    public CitasusuarioFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View auxiliar= inflater.inflate(R.layout.fragment_citasusuario, container, false);

        bAtras=(Button)auxiliar.findViewById(R.id.bAtras);
        lst=(ListView)auxiliar.findViewById(R.id.lista_Usuario);

        TuCita tuCita=new TuCita(getContext());
        db=tuCita.getWritableDatabase();

        Cursor c = db.rawQuery("SELECT clases FROM Categorias", null);
        items1=new String[c.getCount()];
        if(c.moveToFirst())
            do {
                items1[i]=c.getString(0);
                i++;
            }while (c.moveToNext());
        adaptador=new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,items1);
        lst.setAdapter(adaptador);
        i=0;

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                indicador++;
                if (indicador==1) {
                    String categoria_emp = items1[position];
                    String[] campos = new String[]{"empresa"};
                    String[] args = new String[]{categoria_emp};

                    Cursor c = db.query("Empresas", campos, "categoria=?", args, null, null, null);
                    items2 = new String[c.getCount()];
                    if (c.moveToFirst())
                        do {
                            items2[i] = c.getString(0);
                            i++;
                        } while (c.moveToNext());
                    adaptador = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, items2);
                    lst.setAdapter(adaptador);
                    i = 0;
                }

                if (indicador==2)
                {
                    String nombre_empresa = items2[position];
                    String[] campos = new String[]{"fecha","hora"};
                    String[] args = new String[]{nombre_empresa};

                    Cursor c = db.query("CitasEmpresa", campos, "empresa=?", args, null, null, null);
                    items3 = new String[c.getCount()];
                    if (c.moveToFirst())
                        do {
                            items3[i] = c.getString(0)+" "+c.getString(1);
                            i++;
                        } while (c.moveToNext());
                    adaptador = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, items3);
                    lst.setAdapter(adaptador);
                    i = 0;
                }
            }
        });

        bAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (indicador == 1) {
                    adaptador = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, items1);
                    lst.setAdapter(adaptador);
                    indicador = 0;
                }
                if (indicador == 2) {
                    adaptador = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, items2);
                    lst.setAdapter(adaptador);
                    indicador=1;
                }
            }
        });

        return auxiliar;
    }
}
