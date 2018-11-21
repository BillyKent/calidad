package com.software.miedo.pocketfisireal.presentation.fragments.news;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.software.miedo.pocketfisireal.R;

public class NewsFragment extends Fragment implements NewsContract.View {

    private NewsViewModel mViewModel;

    private NewsRecyclerAdapter mAdapter;

    private RecyclerView recycler;
    private TextView tv_mensaje;
    private View progressBar;

    public static NewsFragment newInstance() {
        return new NewsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.recycler_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(getActivity()).get(NewsViewModel.class);
        mViewModel.setView(this);


        progressBar = getView().findViewById(R.id.recyclerview_fragment_barra);
        tv_mensaje = (TextView) getView().findViewById(R.id.recyclerview_fragment_mensaje);

        recycler = (RecyclerView) getView().findViewById(R.id.recyclerview_fragment_recycler);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));

        mAdapter = new NewsRecyclerAdapter(mViewModel.getData(), getContext());
        recycler.setAdapter(mAdapter);

        mViewModel.loadData();
    }


    @Override
    public void mostrarCarga() {
        progressBar.setVisibility(View.VISIBLE);
        recycler.setVisibility(View.GONE);
        tv_mensaje.setVisibility(View.GONE);
    }

    @Override
    public void mostrarData() {

        recycler.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        tv_mensaje.setVisibility(View.GONE);
    }

    @Override
    public void notificarData() {
        mostrarData();
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void mostrarMensaje(String msj) {
        tv_mensaje.setText(msj);
        tv_mensaje.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        recycler.setVisibility(View.GONE);
    }

    public void reload() {
        mViewModel.reloadData();
    }
}
