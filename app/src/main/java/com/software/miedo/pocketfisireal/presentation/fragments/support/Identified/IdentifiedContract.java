package com.software.miedo.pocketfisireal.presentation.fragments.support.Identified;

import android.view.View;

import com.software.miedo.pocketfisireal.model.UserEntity;

public class IdentifiedContract {
    interface View {

        void mostrarCarga();

        void mostrarMensaje(String msj);

        void mostrarFormularioRegistro();

        void mostrarDatosCurso(String profesor, String salon,String curso);

        void mostrarDatosUsuario(UserEntity user);


    }

    interface Presenter {

        void mostrarInfoUsuario();

        void actualizarPantalla();

        void irASolicitud();

    }

}
