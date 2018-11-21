package com.software.miedo.pocketfisireal.presentation.fragments.news;

import android.arch.lifecycle.ViewModel;

import com.software.miedo.pocketfisireal.data.remote.PocketFisiAPI;
import com.software.miedo.pocketfisireal.data.remote.ServiceGenerator;
import com.software.miedo.pocketfisireal.model.Noticia;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsViewModel extends ViewModel implements NewsContract.Presenter {

    private ArrayList<Noticia> mData = new ArrayList<>();

    boolean loaded = false;

    private NewsContract.View mView;


    public NewsViewModel() {

    }

    public void loadData() {

        if (loaded) return;

        mData.clear();
        mView.mostrarCarga();

        PocketFisiAPI api = ServiceGenerator.createServiceGson(PocketFisiAPI.class);
        Call<List<Noticia>> call = api.getNoticias();
        call.enqueue(new Callback<List<Noticia>>() {
            @Override
            public void onResponse(Call<List<Noticia>> call, Response<List<Noticia>> response) {

                if (response.isSuccessful()) {
                    mData.addAll(response.body());

                    if (mData.isEmpty()) {
                        mView.mostrarMensaje("No se encontraron noticias.");
                    } else {
                        mView.notificarData();
                        loaded = true;
                    }

                } else {
                    mView.mostrarMensaje("Algo salio mal.");
                }


            }

            @Override
            public void onFailure(Call<List<Noticia>> call, Throwable t) {
                mView.mostrarMensaje("Ocurrió un error al obtener la data." + t.getMessage());

            }
        });

    }

    @Override
    public void setView(NewsContract.View view) {
        this.mView = view;

    }

    public ArrayList<Noticia> getData() {
        return mData;
    }

    public void reloadData() {
        loaded = false;
        loadData();
    }
}
