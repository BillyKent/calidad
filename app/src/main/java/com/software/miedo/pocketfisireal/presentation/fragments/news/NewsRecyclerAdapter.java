package com.software.miedo.pocketfisireal.presentation.fragments.news;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.software.miedo.pocketfisireal.R;
import com.software.miedo.pocketfisireal.data.remote.ServiceGenerator;
import com.software.miedo.pocketfisireal.model.Noticia;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class NewsRecyclerAdapter extends RecyclerView.Adapter<NewsRecyclerAdapter.NoticiaViewHolder> {

    List<Noticia> mData;

    private Context mContext;

    public NewsRecyclerAdapter(List<Noticia> mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public NoticiaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_item, parent, false);
        return new NoticiaViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoticiaViewHolder holder, int position) {
        Noticia noticia = mData.get(position);
        holder.tv_titulo.setText(noticia.getTitle());
        holder.tv_descripcion.setText(noticia.getDescription());
        holder.tv_fecha.setText(noticia.getStart());
        holder.tv_categoria.setText(noticia.getCategory());

        holder.card_noticia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (noticia.getUrl().equals("")) {
                    Toast.makeText(mContext, "No hay enlace disponible para esta noticia", Toast.LENGTH_SHORT).show();
                    return;
                }


                Uri noticiaUri = Uri.parse(noticia.getUrl());

                // Crea una intent a la url de la noticia
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, noticiaUri);

                // Envia la intent para lanzar una nueva activity
                mContext.startActivity(websiteIntent);
            }
        });

        Picasso.get().load(ServiceGenerator.IMAGE_URL_BASE + noticia.getId_news()).into(holder.iv_foto);
    }

    private String getCategoryById(int category) {
        switch (category) {
            case 1:
                return "Taller";
            case 2:
                return "Conferencia";
            case 3:
                return "Evento";
            case 4:
                return "Matrícula";
            case 5:
                return "Actividad";
        }

        return "ERROR";

    }


    SimpleDateFormat ft =
            new SimpleDateFormat("EEEEE dd 'de' MMMMM, yyyy");

    private String formatFecha(Date fecha) {
        return ft.format(fecha);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class NoticiaViewHolder extends RecyclerView.ViewHolder {

        public ImageView iv_foto;
        public TextView tv_titulo, tv_categoria, tv_descripcion, tv_fecha;
        public CardView card_noticia;


        public NoticiaViewHolder(View itemView) {
            super(itemView);
            card_noticia = (CardView) itemView.findViewById(R.id.card_noticia);

            iv_foto = (ImageView) itemView.findViewById(R.id.iv_foto);
            tv_categoria = (TextView) itemView.findViewById(R.id.tv_categoria);
            tv_titulo = (TextView) itemView.findViewById(R.id.tv_titulo);
            tv_descripcion = (TextView) itemView.findViewById(R.id.tv_descripcion);
            tv_fecha = (TextView) itemView.findViewById(R.id.tv_fecha);

        }
    }
}
