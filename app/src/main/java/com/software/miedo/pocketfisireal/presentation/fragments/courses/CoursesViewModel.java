package com.software.miedo.pocketfisireal.presentation.fragments.courses;

import android.arch.lifecycle.ViewModel;

import com.software.miedo.pocketfisireal.data.remote.PocketFisiAPI;
import com.software.miedo.pocketfisireal.data.remote.ServiceGenerator;
import com.software.miedo.pocketfisireal.model.Curso;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CoursesViewModel extends ViewModel implements CoursesContract.Presenter {

    private ArrayList<Curso> mData = new ArrayList<>();

    private CoursesContract.View mView;

    private boolean loaded = false;

    public CoursesViewModel() {
    }

    public ArrayList<Curso> getData() {
        return mData;
    }

    private String getDayAsString() {
        int dia = new GregorianCalendar().get(Calendar.DAY_OF_WEEK);
        return analizar(dia);
    }

    private String analizar(int dia) {
        switch (dia) {
            case Calendar.MONDAY:
                return "lunes";
            case Calendar.TUESDAY:
                return "martes";
            case Calendar.WEDNESDAY:
                return "miercoles";
            case Calendar.THURSDAY:
                return "jueves";
            case Calendar.FRIDAY:
                return "viernes";
            case Calendar.SATURDAY:
                return "sabado";
            case Calendar.SUNDAY:
                return "domingo";
        }

        return "lunes";
    }

    public void loadData() {

        if (loaded) return;

        mData.clear();
        mView.mostrarCarga();

        PocketFisiAPI api = ServiceGenerator.createServiceGson(PocketFisiAPI.class);
        Call<List<Curso>> call = api.getCursosByDay(getDayAsString());

        call.enqueue(new Callback<List<Curso>>() {
            @Override
            public void onResponse(Call<List<Curso>> call, Response<List<Curso>> response) {
                if (response.isSuccessful()) {
                    mData.addAll(response.body());
                    if (mData.isEmpty()) {
                        mView.mostrarMensaje("No se encontraron cursos.");
                    } else {
                        mView.notificarData();
                        loaded = true;
                    }

                } else {
                    mView.mostrarMensaje("Algo salio mal.");
                }
            }

            @Override
            public void onFailure(Call<List<Curso>> call, Throwable t) {
                mView.mostrarMensaje("Error en la petición de datos.");
            }
        });
    }

    @Override
    public void setView(CoursesContract.View view) {
        mView = view;
    }

    public void reloadData() {
        loaded = false;
        loadData();
    }
}
