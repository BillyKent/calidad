package com.software.miedo.pocketfisireal.presentation.fragments.support.Identified;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.software.miedo.pocketfisireal.R;
import com.software.miedo.pocketfisireal.model.UserEntity;
import com.software.miedo.pocketfisireal.presentation.activities.MainActivity;
import com.software.miedo.pocketfisireal.presentation.activities.SolicitudActivity;
import com.software.miedo.pocketfisireal.presentation.fragments.support.SupportLoginFragment;

public class SupportIdentifiedFragment extends Fragment implements IdentifiedContract.View {

    private TextView tv_nombre_alumno, tv_codigo_alumno, tv_aula,
            tv_nombre_curso, tv_nombre_profesor, form_mensaje;

    private View form_solicitud, form_carga;

    private Button bt_logout, bt_solicitar;

    IdentifiedPresenter mPressenter;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.support_identified_fragment, container, false);
    }


    @Override
    public void onResume() {
        super.onResume();
        mPressenter.actualizarPantalla();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        tv_nombre_alumno = view.findViewById(R.id.tv_nombre_alumno_soporte);
        tv_codigo_alumno = view.findViewById(R.id.tv_codigo_alumno_soporte);
        tv_nombre_curso = view.findViewById(R.id.tv_nombre_curso_soporte);
        tv_nombre_profesor = view.findViewById(R.id.tv_nombre_profesor_soporte);
        tv_aula = view.findViewById(R.id.tv_aula);

        form_carga = view.findViewById(R.id.progress_circular_soporte);
        form_mensaje = view.findViewById(R.id.tv_mensaje);
        form_solicitud = view.findViewById(R.id.form_solicitar);

        bt_logout = view.findViewById(R.id.bt_logout);
        bt_logout.setOnClickListener(mOnclick);
        bt_solicitar = view.findViewById(R.id.bt_solicitar);
        bt_solicitar.setOnClickListener(mOnclickSolicitud);

        mPressenter = new IdentifiedPresenter(this, getContext());
    }


    @Override
    public void mostrarCarga() {
        form_carga.setVisibility(View.VISIBLE);

        form_solicitud.setVisibility(View.GONE);
        form_mensaje.setVisibility(View.GONE);
    }

    @Override
    public void mostrarMensaje(String msj) {
        form_mensaje.setText(msj);
        form_mensaje.setVisibility(View.VISIBLE);

        form_carga.setVisibility(View.GONE);
        form_solicitud.setVisibility(View.GONE);
    }

    @Override
    public void mostrarFormularioRegistro() {
        form_solicitud.setVisibility(View.VISIBLE);

        form_carga.setVisibility(View.GONE);
        form_mensaje.setVisibility(View.GONE);

    }

    @Override
    public void mostrarDatosCurso(String profesor, String salon, String curso) {

        tv_aula.setText(salon);
        tv_nombre_profesor.setText(profesor);
        tv_nombre_curso.setText(curso);
    }

    @Override
    public void mostrarDatosUsuario(UserEntity user) {
        tv_nombre_alumno.setText(user.getName());
        tv_codigo_alumno.setText(user.getCode());

    }

    View.OnClickListener mOnclickSolicitud = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if (form_solicitud.getVisibility() == View.VISIBLE) {
                /*Intent intent = new Intent(getContext(), SolicitudActivity.class);
                intent.putExtra("profesor", tv_nombre_profesor.getText().toString());
                intent.putExtra("curso", tv_nombre_curso.getText().toString());

                intent.putExtra("salon", "107");
                startActivity(intent);*/

                mPressenter.irASolicitud();

            }


        }
    };


    View.OnClickListener mOnclick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ((MainActivity) SupportIdentifiedFragment.this.getActivity()).closeSession();

            ((MainActivity) SupportIdentifiedFragment.this.getActivity()).refrescar.setVisible(false);

        }
    };

    public void reload() {
        if (mPressenter == null) {
            mPressenter = new IdentifiedPresenter(this, getContext());
        }

        mPressenter.actualizarPantalla();
    }
}





