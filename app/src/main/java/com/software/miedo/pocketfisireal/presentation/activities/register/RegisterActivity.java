package com.software.miedo.pocketfisireal.presentation.activities.register;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.software.miedo.pocketfisireal.R;
import com.software.miedo.pocketfisireal.model.CambiarContraEntity;
import com.software.miedo.pocketfisireal.model.InsertarUsuarioEntity;
import com.software.miedo.pocketfisireal.model.UserEntity;

public class RegisterActivity extends AppCompatActivity implements RegisterContract.View {

    ProgressDialog progressDialog;
    RegisterContract.Presenter presenter;

    TextInputEditText et_username, et_password, et_newpassword;
    Button bt_registro;
    View nueva_contra;
    TextView tv_mensaje_registro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        presenter = new RegisterPresenter(this);
        presenter.setView(this);

        tv_mensaje_registro = (TextView) findViewById(R.id.tv_mensaje_registro);

        et_username = (TextInputEditText) findViewById(R.id.et_username);
        et_password = (TextInputEditText) findViewById(R.id.et_password);
        et_newpassword = (TextInputEditText) findViewById(R.id.et_newpassword);
        bt_registro = (Button) findViewById(R.id.bt_registro);

        nueva_contra = findViewById(R.id.nueva_contra);

        bt_registro.setOnClickListener(listener);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Por favor espere");
        progressDialog.setCancelable(false);
    }

    @Override
    public void mostrarMensaje(String texto) {
        Toast.makeText(this, texto, Toast.LENGTH_LONG).show();
    }

    @Override
    public void mostrarCarga(String textoCarga) {
        progressDialog.setTitle(textoCarga);
        progressDialog.show();
    }

    @Override
    public void mostrarConfirmacionNuevaContra() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("¿Deseas cambiar la contraseña de tu nueva cuenta?")
                .setTitle("Cuenta encontrada")
                .setPositiveButton("Sí", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();
    }


    @Override
    public InsertarUsuarioEntity getDatos() {
        InsertarUsuarioEntity retorno = new InsertarUsuarioEntity();

        retorno.setMobile("123456789");
        retorno.setUsername(et_username.getText().toString());
        retorno.setPassword(et_password.getText().toString());
        return retorno;
    }

    @Override
    public CambiarContraEntity getCambio() {
        CambiarContraEntity retorno = new CambiarContraEntity();
        retorno.setUsuario(et_username.getText().toString());
        retorno.setOldpass(et_password.getText().toString());
        retorno.setNewpass(et_newpassword.getText().toString());

        return retorno;
    }

    @Override
    public void ocultarCarga() {
        progressDialog.hide();
    }

    @Override
    public void mostrarFormularioCambioContra() {

        et_username.setEnabled(false);
        et_password.setEnabled(false);

        nueva_contra.setVisibility(View.VISIBLE);
        bt_registro.setText("Crear cuenta");

    }

    @Override
    public void mostrarFormularioLogin() {

    }

    @Override
    public void hacerFocus() {
        et_username.requestFocus();
    }

    @Override
    public void terminar() {
        finish();
    }

    @Override
    public void cambiarMensaje(String msj) {
        tv_mensaje_registro.setText(msj);
    }


    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (nueva_contra.getVisibility() == View.GONE) {
                presenter.verificarExistencia(getDatos());
            } else {
                presenter.cambiarContra(getCambio());
            }
        }
    };

    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case DialogInterface.BUTTON_POSITIVE:
                    mostrarFormularioCambioContra();
                    break;

                case DialogInterface.BUTTON_NEGATIVE:

                    mostrarMensaje("Cuenta creada");
                    finish();
                    break;
            }
        }
    };


}
