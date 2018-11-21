package com.software.miedo.pocketfisireal.presentation.activities.register;

import com.software.miedo.pocketfisireal.model.CambiarContraEntity;
import com.software.miedo.pocketfisireal.model.InsertarUsuarioEntity;

public class RegisterContract {
    interface View {
        void mostrarMensaje(String texto);

        void mostrarCarga(String textoCarga);

        void mostrarConfirmacionNuevaContra();

        InsertarUsuarioEntity getDatos();

        CambiarContraEntity getCambio();

        void ocultarCarga();

        void mostrarFormularioCambioContra();

        void mostrarFormularioLogin();

        void hacerFocus();

        void terminar();

        void cambiarMensaje(String msj);
    }

    interface Presenter {

        void setView(View view);

        void verificarExistencia(InsertarUsuarioEntity cuenta);

        void cambiarContra(CambiarContraEntity cambio);


    }
}
