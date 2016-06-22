package com.prieto.william.logeo_gmail_v1;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class PerfilempresaFragment extends Fragment {

    Button bEaceptar,bEeditar,bEcancel;
    EditText eEnombre,eEservidor,eEcel,eEcorreo,eEubicacion;

    public PerfilempresaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View imagen2 =  inflater.inflate(R.layout.fragment_perfilempresa, container, false);
        bEaceptar = (Button) imagen2.findViewById(R.id.bEaceptar);
        bEeditar = (Button) imagen2.findViewById(R.id.bEeditar);
        bEcancel = (Button) imagen2.findViewById(R.id.bEcancel);

        eEnombre= (EditText) imagen2.findViewById(R.id.eEnombre);//nombre de la empresa
        eEservidor= (EditText) imagen2.findViewById(R.id.eEservidor);// nombre de quien presta el servicio
        eEcel= (EditText) imagen2.findViewById(R.id.eEcel);
        eEcorreo= (EditText) imagen2.findViewById(R.id.eEcorreo);
        eEubicacion= (EditText) imagen2.findViewById(R.id.eEubicacion);

        eEnombre.setEnabled(false);//nombre de la empresa
        eEservidor.setEnabled(false);// nombre de quien presta el servicio
        eEcel.setEnabled(false);
        eEcorreo.setEnabled(false);
        eEubicacion.setEnabled(false);

        bEeditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eEnombre.setEnabled(true);//nombre de la empresa
                eEservidor.setEnabled(true);// nombre de quien presta el servicio
                eEcel.setEnabled(true);
                eEcorreo.setEnabled(true);
                eEubicacion.setEnabled(true);
                bEeditar.setVisibility(View.INVISIBLE);
                bEaceptar.setVisibility(View.VISIBLE);
                bEcancel.setVisibility(View.VISIBLE);
            }
        });

        bEcancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eEnombre.setEnabled(false);//nombre de la empresa
                eEservidor.setEnabled(false);// nombre de quien presta el servicio
                eEcel.setEnabled(false);
                eEcorreo.setEnabled(false);
                eEubicacion.setEnabled(false);
                bEeditar.setVisibility(View.VISIBLE);
                bEaceptar.setVisibility(View.INVISIBLE);
                bEcancel.setVisibility(View.INVISIBLE);
            }
        });






        return imagen2;
    }


}
