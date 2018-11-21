package com.software.miedo.pocketfisireal.presentation.fragments.support;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.software.miedo.pocketfisireal.model.UserEntity;
import com.software.miedo.pocketfisireal.presentation.activities.LoginActivity;
import com.software.miedo.pocketfisireal.R;
import com.software.miedo.pocketfisireal.presentation.activities.MainActivity;
import com.software.miedo.pocketfisireal.presentation.activities.register.RegisterActivity;

public class SupportLoginFragment extends Fragment {

    private static final int LOGIN_SUCCESSFULLY = 0;

    TextView tv_crear_cuenta;

    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.support_login_fragment, container, false);


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        tv_crear_cuenta = (TextView) getView().findViewById(R.id.tv_crear_cuenta);
        tv_crear_cuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SupportLoginFragment.this.getContext(), RegisterActivity.class);
                startActivityForResult(intent, LOGIN_SUCCESSFULLY);

            }
        });

        Button buttonLogin = (Button) getView().findViewById(R.id.bt_login);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //((SupportFragment)getParentFragment()).setContent(true)
                Intent intent = new Intent(SupportLoginFragment.this.getContext(), LoginActivity.class);

                startActivityForResult(intent, LOGIN_SUCCESSFULLY);

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == LOGIN_SUCCESSFULLY) {
            switch (resultCode) {
                case Activity.RESULT_OK:
                    String userName = data.getStringExtra("user");
                    String userCode = data.getStringExtra("code");
                    UserEntity usuarioRegistrado = new UserEntity(userName, userCode);

                    Toast.makeText(this.getContext(), "Bienvenido " + userName, Toast.LENGTH_SHORT).show();
                    ((MainActivity) getActivity()).initSession(usuarioRegistrado);
                    ((MainActivity) getActivity()).refrescar.setVisible(true);
                    break;
                case Activity.RESULT_CANCELED:
                    //Toast.makeText(this.getContext(), "usuario no identificado", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }

}













