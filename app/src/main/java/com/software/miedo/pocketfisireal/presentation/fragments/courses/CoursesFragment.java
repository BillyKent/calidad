package com.software.miedo.pocketfisireal.presentation.fragments.courses;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.software.miedo.pocketfisireal.R;

public class CoursesFragment extends Fragment implements CoursesContract.View {

    private CoursesViewModel mViewModel;
    CoursesRecyclerAdapter mAdapter;
    private RecyclerView recycler;
    private TextView tv_mensaje;
    private View progressBar;

    public static CoursesFragment newInstance() {
        return new CoursesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycler_fragment, container, false);

        mViewModel = ViewModelProviders.of(getActivity()).get(CoursesViewModel.class);
        mViewModel.setView(this);

        progressBar = view.findViewById(R.id.recyclerview_fragment_barra);
        tv_mensaje = (TextView) view.findViewById(R.id.recyclerview_fragment_mensaje);

        // obtenemos el recyvlerview
        recycler = (RecyclerView) view.findViewById(R.id.recyclerview_fragment_recycler);
        // aplicamos el layout manager
        LinearLayoutManager llm = new LinearLayoutManager(this.getContext());
        recycler.setLayoutManager(llm);
        // creamos la linea divisoria
        DividerItemDecoration decoration = new DividerItemDecoration(recycler.getContext(), llm.getOrientation());
        recycler.addItemDecoration(decoration);

        mAdapter = new CoursesRecyclerAdapter(mViewModel.getData(), getContext());
        recycler.setAdapter(mAdapter);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        actualizar();

    }

    private void actualizar() {
        /*if (mViewModel.getData().isEmpty()) {
            mostrarCarga();
            mViewModel.loadData();
        }*/
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
