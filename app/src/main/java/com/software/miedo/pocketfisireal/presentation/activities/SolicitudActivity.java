package com.software.miedo.pocketfisireal.presentation.activities;

import android.app.ProgressDialog;
import android.os.Build;
import android.os.Handler;
import android.support.transition.Fade;
import android.support.transition.TransitionInflater;
import android.support.transition.TransitionSet;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.software.miedo.pocketfisireal.R;
import com.software.miedo.pocketfisireal.data.remote.PocketFisiAPI;
import com.software.miedo.pocketfisireal.data.remote.ServiceGenerator;
import com.software.miedo.pocketfisireal.model.Mensaje;
import com.software.miedo.pocketfisireal.presentation.fragments.solicitud.DescripcionFragment;
import com.software.miedo.pocketfisireal.presentation.fragments.solicitud.SelectionFragment;
import com.software.miedo.pocketfisireal.utils.KeyboardUtils;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SolicitudActivity extends AppCompatActivity {
    private static final long MOVE_DEFAULT_TIME = 700;
    private static final long FADE_DEFAULT_TIME = 300;

    private FragmentManager mFragmentManager;

    SelectionFragment selectionFragment;
    DescripcionFragment descripcionFragment;

    private Handler mDelayedTransactionHandler = new Handler();
    private Runnable mRunnable = this::performTransition;

    ProgressDialog progressDialog;


    String nombreProfesor;
    String nombreCurso;
    String salon;
    String descripcion;
    String problemas;
    String id_usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame_fragment);

        mFragmentManager = getSupportFragmentManager();

        nombreProfesor = getIntent().getStringExtra("profesor");
        nombreCurso = getIntent().getStringExtra("curso");
        salon = getIntent().getStringExtra("salon");
        id_usuario = getIntent().getStringExtra("codigo");


        selectionFragment = SelectionFragment.newInstance();
        descripcionFragment = DescripcionFragment.newInstance();

        loadInitialFragment();
        //mDelayedTransactionHandler.postDelayed(mRunnable, 1000);


        // Configuramos el dialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Enviando solicitud");
        progressDialog.setMessage("Por favor espere");

        progressDialog.setCancelable(false);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_solicitud, menu);

        enviar = menu.findItem(R.id.solicitud_enviar);
        return true;
    }

    MenuItem enviar;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.solicitud_continuar:

                if (selectionFragment.isValid()) {
                    problemas = selectionFragment.getOptionsSelected();
                    item.setVisible(false);
                    enviar.setVisible(true);
                    performTransition();
                } else {
                    Toast.makeText(this, "Debes seleccionar al menos uno", Toast.LENGTH_SHORT).show();
                }

                return true;

            case R.id.solicitud_enviar:

                descripcion = descripcionFragment.getText();
                Toast.makeText(this, "problemas :" + problemas, Toast.LENGTH_SHORT).show();
                enviarMensaje();

                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void loadInitialFragment() {

        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_container, selectionFragment);
        fragmentTransaction.commit();
    }

    private void performTransition() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            if (isDestroyed()) {
                return;
            }
        }
        Fragment previousFragment = mFragmentManager.findFragmentById(R.id.frame_container);
        Fragment nextFragment = descripcionFragment;

        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();

        // 1. Exit for Previous Fragment
        Fade exitFade = new Fade();
        exitFade.setDuration(FADE_DEFAULT_TIME);
        previousFragment.setExitTransition(exitFade);

        // 2. Shared Elements Transition
        TransitionSet enterTransitionSet = new TransitionSet();
        enterTransitionSet.addTransition(TransitionInflater.from(this).inflateTransition(android.R.transition.move));
        enterTransitionSet.setDuration(MOVE_DEFAULT_TIME);
        enterTransitionSet.setStartDelay(FADE_DEFAULT_TIME);
        nextFragment.setSharedElementEnterTransition(enterTransitionSet);

        // 3. Enter Transition for New Fragment
        Fade enterFade = new Fade();
        enterFade.setStartDelay(MOVE_DEFAULT_TIME + FADE_DEFAULT_TIME);
        enterFade.setDuration(FADE_DEFAULT_TIME);
        nextFragment.setEnterTransition(enterFade);

        //fragmentTransaction.addSharedElement(logo, logo.getTransitionName());
        fragmentTransaction.replace(R.id.frame_container, nextFragment);
        fragmentTransaction.commitAllowingStateLoss();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDelayedTransactionHandler.removeCallbacks(mRunnable);
    }

    public void enviarMensaje() {

        KeyboardUtils.hideSoftInput(this);
        progressDialog.show();

        PocketFisiAPI api = ServiceGenerator.createServiceGson(PocketFisiAPI.class);

        Mensaje mensaje = new Mensaje();

        mensaje.setId_usuario(this.id_usuario);
        mensaje.setId_perfil("07");
        mensaje.setMensaje(this.descripcion);
        mensaje.setAsunto("[" + this.problemas + "] [" + this.nombreCurso + "] [salón: " + this.salon + "]");

        Call<Boolean> call = api.enviarMensaje(mensaje);

        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful()) {
                    if (progressDialog.isShowing()) {
                        progressDialog.hide();
                        Toast.makeText(SolicitudActivity.this, "Enviado", Toast.LENGTH_SHORT).show();
                    }

                    finish();
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {

                if (progressDialog.isShowing()) {
                    progressDialog.hide();
                    Toast.makeText(SolicitudActivity.this, "Ocurrió un error.", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }


}