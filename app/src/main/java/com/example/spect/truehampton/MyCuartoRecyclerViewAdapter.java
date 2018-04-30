package com.example.spect.truehampton;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.spect.truehampton.CuartoFragment.OnListFragmentInteractionListener;
import com.example.spect.truehampton.dummy.DatosHabitacion;
import com.example.spect.truehampton.dummy.DummyContent.DummyItem;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyCuartoRecyclerViewAdapter extends RecyclerView.Adapter<MyCuartoRecyclerViewAdapter.ViewHolder> {

    //private final List<DummyItem> mValues;
   // private final OnListFragmentInteractionListener mListener;
    private Context contexto;
    private List<DatosHabitacion> datosHabitacions;

    /*public MyCuartoRecyclerViewAdapter(List<DummyItem> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }*/

    public MyCuartoRecyclerViewAdapter(Context contexto, List<DatosHabitacion> datosHabitacions) {
        this.contexto = contexto;
        this.datosHabitacions = datosHabitacions;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_cuarto, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        DatosHabitacion datos = datosHabitacions.get(position);
        holder.datos.setText(datos.getDatos());
        holder.imagen.setImageDrawable(contexto.getResources().getDrawable(datos.getImagen()));
        holder.title.setText(datos.getTitulo());
    }

    @Override
    public int getItemCount() {
        return datosHabitacions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imagen;
        TextView datos;
        TextView title;
        public ViewHolder(View itemView) {
            super(itemView);
            imagen = itemView.findViewById(R.id.imagenhabitacion);
            datos = itemView.findViewById(R.id.datoshabitacion);
            imagen.setScaleType(ImageView.ScaleType.FIT_XY);
            title = itemView.findViewById(R.id.idtitle);
        }


    }
}
