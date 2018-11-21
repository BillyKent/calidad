package com.software.miedo.pocketfisireal.presentation.fragments.news;

public interface NewsContract {
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
