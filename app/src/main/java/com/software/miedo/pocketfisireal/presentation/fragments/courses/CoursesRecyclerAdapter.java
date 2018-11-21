package com.software.miedo.pocketfisireal.presentation.fragments.courses;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.software.miedo.pocketfisireal.R;
import com.software.miedo.pocketfisireal.core.CustomDialog;
import com.software.miedo.pocketfisireal.model.Curso;

import java.util.ArrayList;

public class CoursesRecyclerAdapter extends RecyclerView.Adapter<CoursesRecyclerAdapter.CursoViewHolder> {

    private ArrayList<Curso> mData;
    private Context mContext;

    public CoursesRecyclerAdapter(ArrayList<Curso> mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public CursoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.courses_item, parent, false);


        return new CursoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CursoViewHolder holder, int position) {
        Curso curso = mData.get(position);


        holder.tv_nombre_curso.setText(curso.getCurso());
        holder.tv_nombre_profesor.setText(curso.getProfesor());
        holder.tv_numero_ciclo.setText(curso.getCiclo() + "");
        holder.tv_numero_seccion.setText(curso.getSeccion() + " - " + curso.getTipo());
        holder.tv_horario_curso.setText(curso.getInicio() + ":00 - " + curso.getFin() + ":00");
        holder.iv_estado_curso.setImageResource(getResource(curso.getEstado()));

        GradientDrawable gd = (GradientDrawable) holder.tv_numero_ciclo.getBackground();
        int colorCirculo = getColorCirculo(curso.getCiclo());
        gd.setColor(colorCirculo);

        holder.itemXD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    new CustomDialog(CoursesRecyclerAdapter.this.mContext, curso);
                }
            }
        });

    }

    private int getColorCirculo(Integer ciclo) {
        int magnitudeColorResourceId;
        // aproximamos la magnitud al entero inferior más próximo.
        switch (ciclo) {
            case 1:
                magnitudeColorResourceId = R.color.ciclo1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.ciclo2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.ciclo3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.ciclo4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.ciclo5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.ciclo6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.ciclo7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.ciclo8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.ciclo9;
                break;
            default:
                magnitudeColorResourceId = R.color.ciclo10;
                break;
        }
        return ContextCompat.getColor(mContext, magnitudeColorResourceId);

    }

    private int getResource(String estado) {
        if (estado.equals("No iniciado")) {
            return R.drawable.ic_clock_waiting;
        } else if (estado.equals("Iniciado")) {
            return R.drawable.ic_clock_inprogress;
        } else if (estado.equals("Cancelado")) {
            return R.drawable.ic_clock_cancelled;
        }

        return R.drawable.ic_clock_error;

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class CursoViewHolder extends RecyclerView.ViewHolder {

        TextView tv_nombre_curso, tv_nombre_profesor, tv_numero_ciclo,
                tv_numero_seccion, tv_horario_curso;

        ImageView iv_estado_curso;

        View itemXD;

        public CursoViewHolder(View itemView) {
            super(itemView);
            itemXD = itemView;
            this.tv_nombre_curso = (TextView) itemView.findViewById(R.id.tv_nombre_curso);
            this.tv_nombre_profesor = (TextView) itemView.findViewById(R.id.tv_nombre_profesor);
            this.tv_numero_ciclo = (TextView) itemView.findViewById(R.id.tv_numero_ciclo);
            this.tv_numero_seccion = (TextView) itemView.findViewById(R.id.tv_seccion);
            this.iv_estado_curso = (ImageView) itemView.findViewById(R.id.iv_estado_curso);
            this.tv_horario_curso = (TextView) itemView.findViewById(R.id.tv_horario_curso);
        }
    }
}
