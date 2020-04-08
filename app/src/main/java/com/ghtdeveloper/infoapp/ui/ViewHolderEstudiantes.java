package com.ghtdeveloper.infoapp.ui;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.ghtdeveloper.infoapp.R;

/**
   En esta clasee se utiliza
   para asignar las vistas que utilizara
   el adapter estas vistas fueron definidas en el
   list_Items.xml
 **/
public class ViewHolderEstudiantes extends RecyclerView.ViewHolder
{
    //Vistas
    public ImageView imagenEstudiante;
    public TextView textEstudiantes;

    /*
        Constructor de la class tiene como argumento una vista
        se realiza el cast con los elementos del list_item.xml
     */
    public ViewHolderEstudiantes(@NonNull View v)
    {
        super(v);
        imagenEstudiante = v.findViewById(R.id.imgPerfilEstudiante);
        textEstudiantes = v.findViewById(R.id.nombreEstudiante);
    }

}//Fin de la class ViewHolderEstudiantes
