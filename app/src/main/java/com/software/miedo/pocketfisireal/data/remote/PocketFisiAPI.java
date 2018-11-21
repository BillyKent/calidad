package com.software.miedo.pocketfisireal.data.remote;

import com.software.miedo.pocketfisireal.model.CambiarContraEntity;
import com.software.miedo.pocketfisireal.model.Curso;
import com.software.miedo.pocketfisireal.model.InsertarUsuarioEntity;
import com.software.miedo.pocketfisireal.model.LoginRequestEntity;
import com.software.miedo.pocketfisireal.model.LoginResponseEntity;
import com.software.miedo.pocketfisireal.model.Mensaje;
import com.software.miedo.pocketfisireal.model.Noticia;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PocketFisiAPI {


    @GET("/cursos_mb/listar/{day}")
    public Call<List<Curso>> getCursosByDay(@Path("day") String day);

    @GET("/noticias/listar/")
    public Call<List<Noticia>> getNoticias();

    @POST("/usuario/login")
    public Call<LoginResponseEntity> loginUsuario(@Body LoginRequestEntity cuerpo);

    @GET("/delegado/{id}")
    public Call<String> verificarDelegadoById(@Path("id") String id);


    @POST("/mensaje/enviar")
    public Call<Boolean> enviarMensaje(@Body Mensaje mensaje);

    @POST("/usuario/insertar")
    public Call<Boolean> insertarUsuario(@Body InsertarUsuarioEntity insertar);

    @POST("/usuario/cambiopass")
    public Call<Boolean> modificarContra(@Body CambiarContraEntity cambio);

}
