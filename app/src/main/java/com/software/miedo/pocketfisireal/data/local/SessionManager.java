package com.software.miedo.pocketfisireal.data.local;

import android.content.Context;
import android.content.SharedPreferences;

import com.software.miedo.pocketfisireal.model.UserEntity;

public class SessionManager {
    private static final String PREFERENCE_NAME = "PocketFisiClient";

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private Context context;

    public static final String USER_NAME = "user_name";
    public static final String IS_LOGGED = "user_login";
    public static final String USER_CODE = "user_code";

    public SessionManager(Context context) {
        this.context = context;
        this.preferences = this.context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        this.editor = preferences.edit();
    }

    /**
     * Método para iniciar una sesón de usuario.
     * Guarda los datos en el dispositivo.
     *
     * @param user
     */
    public void initSession(UserEntity user) {
        editor.putBoolean(IS_LOGGED, true);
        editor.putString(USER_NAME, user.getName());
        editor.putString(USER_CODE, user.getCode());
        editor.commit();
    }


    /**
     * Método para cerrar una sesión de usuario.
     * Remueve los datos del usuario del dispositivo.
     */
    public void closeSession() {
        editor.putBoolean(IS_LOGGED, false);
        editor.remove(USER_NAME);
        editor.remove(USER_CODE);
        editor.commit();
    }

    public UserEntity getCurrentSession() {
        UserEntity retorno = new UserEntity();
        retorno.setCode(preferences.getString(USER_CODE, "codigo"));
        retorno.setName(preferences.getString(USER_NAME, "Nombre del alumno o profesor"));

        return retorno;
    }

    /**
     * Verifica si hay una sesión de usuario activa.
     * Retorna true si hay un usuario loggeado, de lo contrario false.
     *
     * @return
     */
    public boolean isLogged() {
        return preferences.getBoolean(IS_LOGGED, false);
    }


}
