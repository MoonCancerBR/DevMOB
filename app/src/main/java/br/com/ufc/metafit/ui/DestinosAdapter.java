package br.com.ufc.metafit.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.ufc.metafit.R;

public class DestinosAdapter extends RecyclerView.Adapter<DestinosAdapter.ViewHolder> {
    List<Destinos> destinos;
    Context context;
    public DestinosAdapter(Context context, List<Destinos> destinos){
        this.context = context;
        this.destinos = destinos;
    }
    @NonNull
    @Override
    public DestinosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.destinos, parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull DestinosAdapter.ViewHolder holder, int position){
        holder.codigo.setText(destinos.get(position).getCodigo());
        holder.telefone.setText(destinos.get(position).getTelefone());
    }
    @Override
    public int getItemCount(){
        return destinos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView codigo, telefone;
        ImageView eliminar;

        public ViewHolder(@NonNull View view){
            super(view);
            cardView = (CardView) view.findViewById(R.id.cardView);
            codigo = (TextView) view.findViewById(R.id.codigo);
            telefone = (TextView) view.findViewById(R.id.telefone);
            eliminar = (ImageView) view.findViewById(R.id.eliminar);

        }
    }

}
