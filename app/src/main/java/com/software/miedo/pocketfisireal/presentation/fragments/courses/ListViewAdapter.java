package com.software.miedo.pocketfisireal.presentation.fragments.courses;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.software.miedo.pocketfisireal.R;

import java.util.ArrayList;

public class ListViewAdapter extends ArrayAdapter<ModelItem> {

    public ListViewAdapter(Context context, ArrayList<ModelItem> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ModelItem item = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.course_detail_item, parent, false);
        }
        // Lookup view for data population
        TextView tvClave = (TextView) convertView.findViewById(R.id.tv_clave);
        TextView tvValor = (TextView) convertView.findViewById(R.id.tv_valor);
        // Populate the data into the template view using the data object
        tvClave.setText(item.clave);
        tvValor.setText(item.valor);
        // Return the completed view to render on screen
        return convertView;

    }
}
