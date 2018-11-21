package com.software.miedo.pocketfisireal.presentation.fragments.solicitud;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.software.miedo.pocketfisireal.R;

public class SelectionFragment extends Fragment {
    CheckBox cb_pc, cb_internet, cb_proyector, cb_otros;
    View item_pc, item_internet, item_proyector, item_otros;

    public static SelectionFragment newInstance() {
        return new SelectionFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.selection_problem, container, false);


        cb_pc = (CheckBox) view.findViewById(R.id.cb_pc);
        cb_internet = (CheckBox) view.findViewById(R.id.cb_internet);
        cb_otros = (CheckBox) view.findViewById(R.id.cb_otros);
        cb_proyector = (CheckBox) view.findViewById(R.id.cb_proyector);

        item_pc = view.findViewById(R.id.item_pc);
        item_internet = view.findViewById(R.id.item_internet);
        item_otros = view.findViewById(R.id.item_otros);
        item_proyector = view.findViewById(R.id.item_proyector);

        item_pc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cb_pc.setChecked(!cb_pc.isChecked());
            }
        });


        item_internet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cb_internet.setChecked(!cb_internet.isChecked());
            }
        });


        item_otros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cb_otros.setChecked(!cb_otros.isChecked());
            }
        });


        item_proyector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cb_proyector.setChecked(!cb_proyector.isChecked());
            }
        });

        return view;
    }

    public String getOptionsSelected() {
        String retorno = "";

        if (cb_pc.isChecked()) retorno += " Computadora ";
        if (cb_proyector.isChecked()) retorno += "Proyector ";
        if (cb_internet.isChecked()) retorno += "Internet ";
        if (cb_otros.isChecked()) retorno += "Otros ";

        return retorno;
    }


    public boolean isValid() {
        if (cb_internet.isChecked() || cb_otros.isChecked() || cb_pc.isChecked() || cb_proyector.isChecked()) {
            return true;
        }
        return false;
    }


}
