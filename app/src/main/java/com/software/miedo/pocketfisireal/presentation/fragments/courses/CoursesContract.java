package com.software.miedo.pocketfisireal.presentation.fragments.courses;

public class CoursesContract {
    interface Presenter {
        void loadData();
        void setView(View view);
    }

    interface View {
        void mostrarCarga();

        void mostrarData();

        void notificarData();

        void mostrarMensaje(String msj);
    }


}
