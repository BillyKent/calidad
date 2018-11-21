package com.software.miedo.pocketfisireal.presentation.fragments.solicitud;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.software.miedo.pocketfisireal.R;

public class DescripcionFragment extends Fragment {

    TextInputEditText editText;



    public static DescripcionFragment newInstance() {
        return new DescripcionFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.description_problem, container, false);
        editText = (TextInputEditText) v.findViewById(R.id.descripcionXD);


        return v;
    }

    public String getText() {
        if (editText != null) {
            return editText.getText().toString();
        } else {
            return "AAAAA";
        }
    }


}
