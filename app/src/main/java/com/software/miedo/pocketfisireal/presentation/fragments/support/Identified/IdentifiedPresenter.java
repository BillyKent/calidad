package com.software.miedo.pocketfisireal.presentation.fragments.support.Identified;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.software.miedo.pocketfisireal.data.local.SessionManager;
import com.software.miedo.pocketfisireal.data.remote.PocketFisiAPI;
import com.software.miedo.pocketfisireal.data.remote.ServiceGenerator;
import com.software.miedo.pocketfisireal.model.UserEntity;
import com.software.miedo.pocketfisireal.presentation.activities.SolicitudActivity;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IdentifiedPresenter implements IdentifiedContract.Presenter {

    private static final String TAG = IdentifiedPresenter.class.getSimpleName();

    private IdentifiedContract.View vista;
    private SessionManager sessionManager;
    private Context mContext;

    private String profesor, curso, salon;

    public IdentifiedPresenter(IdentifiedContract.View vista, Context context) {
        this.vista = vista;
        this.sessionManager = new SessionManager(context);
        this.mContext = context;
    }


    @Override
    public void mostrarInfoUsuario() {
        vista.mostrarDatosUsuario(sessionManager.getCurrentSession());
    }


    @Override
    public void actualizarPantalla() {
        mostrarInfoUsuario();

        vista.mostrarCarga();

        UserEntity userEntity = sessionManager.getCurrentSession();

        PocketFisiAPI api = ServiceGenerator.createServiceScalar(PocketFisiAPI.class);
        Call<String> call = api.verificarDelegadoById(userEntity.getCode());

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                if (response.isSuccessful()) {

                    try {
                        JSONObject rpta = new JSONObject(response.body());

                        IdentifiedPresenter.this.profesor = null;
                        IdentifiedPresenter.this.curso = null;
                        IdentifiedPresenter.this.salon = null;

                        if (!rpta.getBoolean("delegado")) {
                            vista.mostrarMensaje("No puedes solicitar soporte debido a que no eres delegado," +
                                    " si crees que se trata de un error actualiza.");
                        } else {
                            if (!rpta.getBoolean("haycurso")) {
                                vista.mostrarMensaje("Los cursos de los que eres delegado están fuera de horario," +
                                        " si crees que se trata de un error actualiza.");
                            } else {
                                if (rpta.has("curso")) {
                                    JSONObject curso = rpta.getJSONObject("curso");

                                    IdentifiedPresenter.this.profesor = curso.getString("profesor");
                                    IdentifiedPresenter.this.curso = curso.getString("nombre");
                                    IdentifiedPresenter.this.salon = curso.getString("salon");

                                    if (salon.equals("null")) salon = "salón desconocido";

                                    vista.mostrarDatosCurso(
                                            IdentifiedPresenter.this.profesor,
                                            "[ "+IdentifiedPresenter.this.salon+" ]",
                                            IdentifiedPresenter.this.curso);

                                    vista.mostrarFormularioRegistro();

                                } else {
                                    vista.mostrarMensaje("Ocurrió un error.");

                                }
                            }
                        }
                    } catch (JSONException e) {
                        vista.mostrarMensaje("Error al analizar la respuesta del servidor.");
                        Log.e(TAG, e.getMessage());
                    }

                } else {

                    vista.mostrarMensaje("Error " + response.code());

                }


            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.i("JIJIXD", t.getMessage());
                vista.mostrarMensaje("Error al realizar la consulta al servidor.");
            }
        });
    }

    @Override
    public void irASolicitud() {

        if (curso != null && profesor != null && salon != null) {
            UserEntity userEntity = sessionManager.getCurrentSession();

            Intent intent = new Intent(mContext, SolicitudActivity.class);
            intent.putExtra("profesor", profesor);
            intent.putExtra("curso", curso);
            intent.putExtra("codigo", userEntity.getCode());

            intent.putExtra("salon", salon);

            mContext.startActivity(intent);
        }

    }

}
