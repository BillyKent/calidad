package com.software.miedo.pocketfisireal.core;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.widget.ListView;

import com.software.miedo.pocketfisireal.R;
import com.software.miedo.pocketfisireal.model.Curso;
import com.software.miedo.pocketfisireal.presentation.fragments.courses.ListViewAdapter;
import com.software.miedo.pocketfisireal.presentation.fragments.courses.ModelItem;

import java.util.ArrayList;

public class CustomDialog {


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomDialog(Context context, Curso curso) {

        // Construct the data source
        ArrayList<ModelItem> items = new ArrayList<>();

        items.add(new ModelItem("Curso", curso.getCurso()));
        items.add(new ModelItem("Ciclo", curso.getCiclo() + ""));
        items.add(new ModelItem("Seccion", curso.getSeccion()));
        items.add(new ModelItem("Profesor", curso.getProfesor()));
        items.add(new ModelItem("Tipo", curso.getTipo()));
        items.add(new ModelItem("Salón", curso.getSalon()));
        items.add(new ModelItem("Hora de inicio", curso.getInicio() + ":00"));
        items.add(new ModelItem("Hora de salida", curso.getFin() + ":00"));
        items.add(new ModelItem("Estado", curso.getEstado()));


        // Create the adapter to convert the array to views
        ListViewAdapter adapter = new ListViewAdapter(context, items);
        // Attach the adapter to a ListView
        ListView listView = new ListView(context);
        listView.setAdapter(adapter);

        listView.setDivider(null);
        listView.setPadding(0, 20, 0, 30);


        final Dialog dialog = new AlertDialog.Builder(context)
                .setTitle("Datos del curso")
                .setView(listView)
                .setCancelable(true)
                .setIcon(R.drawable.ic_info)
                .create();

        /*dialog.setTitle("Soy un dialogo");
        dialog.setCancelable(true);

        //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.course_detail_item);*/

        dialog.show();


    }
}
