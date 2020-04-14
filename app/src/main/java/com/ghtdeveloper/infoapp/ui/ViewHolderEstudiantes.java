package com.ghtdeveloper.infoapp.ui;

import android.view.View;
import android.widget.ImageButton;
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
    public ImageButton iconoMoreDetails;
    public TextView textEstudiantes;

    /*
        Constructor de la class tiene como argumento una vista
        se realiza el cast con los elementos del list_item.xml
     */
    public ViewHolderEstudiantes(@NonNull View v)
    {
        super(v);
        iconoMoreDetails = v.findViewById(R.id.btnMoreDetails);
        textEstudiantes = v.findViewById(R.id.nombreEstudiante);
    }

}//Fin de la class ViewHolderEstudiantes
