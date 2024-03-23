package com.example.examensupletorio.Adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.examensupletorio.MainActivity;
import com.example.examensupletorio.Modelo.Pais;
import com.example.examensupletorio.R;


import java.util.List;

public class PaisAdapter extends RecyclerView.Adapter<PaisAdapter.PaisViewHolder> {

    private static List<Pais> listaPaises;
    private Context context;


    public PaisAdapter(Context context, List<Pais> listaPaises) {
        this.listaPaises = listaPaises;
        this.context = context;

    }

    @NonNull
    @Override
    public PaisViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card, parent, false);
        return new PaisViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull PaisViewHolder holder, int position) {
        Pais pais = listaPaises.get(position);
        holder.txtNombre.setText(pais.getName());
        Glide.with(context)
                .load(pais.getCountryInfo())
                .into(holder.imview);
    }

    @Override
    public int getItemCount() {
        return listaPaises.size();
    }

    public class PaisViewHolder extends RecyclerView.ViewHolder {
        ImageView imview;
        TextView txtNombre;

        public PaisViewHolder(View itemView) {
            super(itemView);
            imview = itemView.findViewById(R.id.imview);
            txtNombre = itemView.findViewById(R.id.txtNombre);
        }
    }
}

