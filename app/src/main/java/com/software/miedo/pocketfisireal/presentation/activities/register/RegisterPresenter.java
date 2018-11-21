package com.software.miedo.pocketfisireal.presentation.activities.register;

import android.content.Context;

import com.software.miedo.pocketfisireal.data.remote.PocketFisiAPI;
import com.software.miedo.pocketfisireal.data.remote.ServiceGenerator;
import com.software.miedo.pocketfisireal.model.CambiarContraEntity;
import com.software.miedo.pocketfisireal.model.InsertarUsuarioEntity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterPresenter implements RegisterContract.Presenter {

    RegisterContract.View view;
    Context context;

    public RegisterPresenter(Context context) {
        this.context = context;
    }

    @Override
    public void setView(RegisterContract.View view) {
        this.view = view;
    }

    @Override
    public void verificarExistencia(InsertarUsuarioEntity cuenta) {

        view.mostrarCarga("Comprobando cuenta");

        PocketFisiAPI api = ServiceGenerator.createServiceGson(PocketFisiAPI.class);
        Call<Boolean> call = api.insertarUsuario(cuenta);

        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful()) {
                    view.ocultarCarga();
                    if (response.body()) {
                        view.mostrarMensaje("Cuenta creada.");
                        view.mostrarConfirmacionNuevaContra();
                    } else {
                        view.mostrarMensaje("Usuario o contraseña incorrectos");
                        view.hacerFocus();
                    }

                }

            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                view.ocultarCarga();
                view.mostrarMensaje("Error en la petición :" + t.getMessage());
            }
        });

    }

    @Override
    public void cambiarContra(CambiarContraEntity cambio) {

        view.mostrarCarga("Cambiando contraseña");

        view.cambiarMensaje("Ingresa la contraseña de tu nueva cuenta de PocketFisi.");

        PocketFisiAPI api = ServiceGenerator.createServiceGson(PocketFisiAPI.class);
        Call<Boolean> call = api.modificarContra(cambio);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful()) {
                    view.ocultarCarga();
                    if (response.body()) {
                        view.mostrarMensaje("Contraseña cambiada.");
                        view.terminar();
                    } else {
                        view.mostrarMensaje("Algo salió mal");
                    }

                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {

                view.ocultarCarga();
                view.mostrarMensaje("Error en la petición de cambio." + t.getMessage());

            }
        });


    }
}
