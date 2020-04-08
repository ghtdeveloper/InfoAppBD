package com.ghtdeveloper.infoapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.ghtdeveloper.infoapp.R;
import com.ghtdeveloper.infoapp.modelo.Estudiante;
import com.ghtdeveloper.infoapp.ui.ViewHolderEstudiantes;
import java.util.List;


/**
    Esta clase es utilizada como adaptador para el recyclerview
    se realiza el cast del viewHolder con los elementos del layout
 **/

public class AdapterEstudiantes extends RecyclerView.Adapter<ViewHolderEstudiantes>
{

    //ListaItems
    private List<Estudiante> items;
    //Variables
    public static int positionSend;
    //Objetos
    private Context context;
    //Interface OnItemClickExplorar
    private onItemClickDetalles onItemClick;

    //Se define la interfaz del metodo onItemClick
    public interface  onItemClickDetalles
    {
        void onItemClick(int position);
    }


    //Constructor para ser utilizado en el Recycler
   public AdapterEstudiantes(List<Estudiante> items)
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
    @Override
    public void onBindViewHolder(@NonNull ViewHolderEstudiantes holder, final int i)
    {
        //holder.imagenEstudiante.setImageResource(imageneDataset[position]);
        holder.imagenEstudiante.setImageResource(items.get(i).getImagen());
        holder.textEstudiantes.setText(items.get(i).getNombre());
         holder.imagenEstudiante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                positionSend = i; //Se obtiene el indice de la lista seleccionado por el usuarip
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
