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
public class PerfilusuarioFragment extends Fragment {
    Button bUeditar,bUaceptar,bUcancel;
    EditText eUnombre,eUcel,eUcorreo;

    public PerfilusuarioFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View imagen1 = inflater.inflate(R.layout.fragment_perfilusuario, container, false);
        bUeditar = (Button) imagen1.findViewById(R.id.bUeditar);
        bUaceptar = (Button) imagen1.findViewById(R.id.bUaceptar);
        bUcancel = (Button) imagen1.findViewById(R.id.bUcancel);

        eUnombre= (EditText) imagen1.findViewById(R.id.eUnombre);
        eUcel= (EditText) imagen1.findViewById(R.id.eUcel);
        eUcorreo= (EditText) imagen1.findViewById(R.id.eUcorreo);

        eUnombre.setEnabled(false);
        eUcel.setEnabled(false);
        eUcorreo.setEnabled(false);

        bUeditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eUnombre.setEnabled(true);
                eUcel.setEnabled(true);
                eUcorreo.setEnabled(true);
                bUeditar.setVisibility(View.INVISIBLE);
                bUaceptar.setVisibility(View.VISIBLE);
                bUcancel.setVisibility(View.VISIBLE);
            }
        });

        bUcancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eUnombre.setEnabled(false);
                eUcel.setEnabled(false);
                eUcorreo.setEnabled(false);
                bUeditar.setVisibility(View.VISIBLE);
                bUaceptar.setVisibility(View.INVISIBLE);
                bUcancel.setVisibility(View.INVISIBLE);
            }
        });






        return imagen1;
    }

}
