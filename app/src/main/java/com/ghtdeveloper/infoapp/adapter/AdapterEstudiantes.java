package com.ghtdeveloper.infoapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.ghtdeveloper.infoapp.R;
import com.ghtdeveloper.infoapp.ui.ViewHolderEstudiantes;

import java.util.ArrayList;


/**
    Esta clase es utilizada como adaptador para el recyclerview
    se realiza el cast del viewHolder con los elementos del layout
 **/

public class AdapterEstudiantes extends RecyclerView.Adapter<ViewHolderEstudiantes>
{

    //ListaItems
    private ArrayList<String> items;
    //Variables
    private static int positionSend;
    public static String nombreSend;
    //Objetos
    //Interface OnItemClickExplorar
    private onItemClickDetalles onItemClick;

    //Se define la interfaz del metodo onItemClick
    public interface  onItemClickDetalles
    {
        void onItemClick(int position);
    }


    //Constructor para ser utilizado en el Recycler
   public AdapterEstudiantes(ArrayList<String> items)
   {
       this.items = items;
       //imageneDataset = imagene;
   }

    @Override
    public int getItemCount() {
        return items.size();
    }

    /*
          A la vista se le asigna el layout correspondiente y retorna
          dicha vista
        */
    @NonNull
    @Override
    public ViewHolderEstudiantes onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        return  new ViewHolderEstudiantes(v);
    }

    /*
        Se realiza el cast del viewHolder con cada elemento de layout
        tambien se especifica la informacion que le corresponde a cada
        vista
     */
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final ViewHolderEstudiantes holder, final int i)
    {

        //holder.textEstudiantes.setText(items.get(i).getNombre() + items.get(i).getApellido());
         holder.textEstudiantes.setText(items.get(i));
         holder.iconoMoreDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
               positionSend = i; //Se obtiene el indice de la lista seleccionado por el usuario
                nombreSend = items.get(i);
                onItemClick.onItemClick(i);
            }
        });
    }
    /*
        Metodo para ser utilizada cuando el usuario de un click o seleccione
        la imagene de algunos de los usuarios
     */
    public void setOnClick(onItemClickDetalles onClick)
    {
        this.onItemClick = onClick;
    }


}//Fin de la class AdapterEstudiantes
