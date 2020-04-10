package com.ghtdeveloper.infoapp.ui;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.ghtdeveloper.infoapp.R;

/**
    Clase definida para que los usuarios
    puedan selecionar los disintos temas

 Se definieron dos categorias:

    Temas personalizados: El usuario solo debe seleccionar uno de estos temas
    seguido del boton de aceptar, ademas del boton de confirmación.

    Temas por defecto: En esta ocasion el usuario solo puede seleccionar dos temas seguido
    del mismo procedimiento de los Temas personalizados.
* */

public class ActivityConfiguracion extends AppCompatActivity
{

    //SharePreference
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    //Variables
    private String temas;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);
        sharedPreferences = getSharedPreferences("temas",MODE_PRIVATE);

    }//Fin del metodo onCreate



    public void showDialogTemasDefault(View view)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        @SuppressLint("InflateParams")
        View viewDialog = inflater.inflate(R.layout.dialog_temas_default
                , null);
        //Se realiza un cast con los elementos del layout
        final RadioButton rdbDark = viewDialog.findViewById(R.id.rdbDarkTheme);
        final RadioButton rdbLight = viewDialog.findViewById(R.id.rdbLightTheme);
        builder.setView(viewDialog);
        rdbDark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rdbLight.setChecked(false);
            }
        });

        rdbLight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rdbDark.setChecked(false);
            }
        });

        builder.setTitle("Temas por defecto");
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                if(rdbDark.isChecked())
                {
                    temas = "dark";
                }

                if(rdbLight.isChecked())
                {
                    temas = "light";
                }


                editor = sharedPreferences.edit();
                editor.putString("temaSeleccionado", temas);
                editor.apply();
            }
        });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                    //Cancelar
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }//Fin del metodo showDialogTemasDefault



    /*
        Metodo definido para obtener el AlertDialog
        de los nombres de los temas personalizados
     */
    public void showDialogTemasCustom(View view)
    {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        @SuppressLint("InflateParams") final
        //Se asigna la vista al dialogo
        View viewDialog = inflater.inflate(R.layout.dialog_temas_custom,null);
        //Se realiza un cast de los elementos de la vista
        final CheckBox chkLighGreen = viewDialog.findViewById(R.id.chkBoxTemaCustom1);
        final CheckBox chkPinkFusion = viewDialog.findViewById(R.id.chkBoxTemaCustom2);
        final CheckBox chkDeepPuple = viewDialog.findViewById(R.id.chkBoxTemaCustom3);
        builder.setView(viewDialog);//Se asigna la vista al dialogo
        chkLighGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chkPinkFusion.setChecked(false);
                chkDeepPuple.setChecked(false);
            }
        });

        chkPinkFusion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chkLighGreen.setChecked(false);
                chkDeepPuple.setChecked(false);
            }
        });

        chkDeepPuple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chkLighGreen.setChecked(false);
                chkPinkFusion.setChecked(false);
            }
        });
        builder.setTitle("Temas personalizados")
                .setPositiveButton("Aceptar",new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which)
        {
            //Captura el valor seleccionado

            if(chkLighGreen.isChecked())
            {
                temas ="LightGreen";

            }

            if ((chkPinkFusion.isChecked()))
            {
                temas ="PinkFusion";

            }

            if ((chkDeepPuple.isChecked()))
            {
                temas ="DeepPuple";
            }



            editor = sharedPreferences.edit();
            editor.putString("temaSeleccionado", temas);
            editor.apply();

            /*Toast.makeText(getApplicationContext(),"Tema seleccionado"+ temaCustom,
                    Toast.LENGTH_SHORT).show();*/
        }

    });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Cancelar
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }//Fin del metodo showDialogTemasCustom






    public void mostrarPantallaDetalles(View view)
    {
        //Objetos
        Intent intentConfiguracion = new Intent(this, MainActivity.class);
        startActivity(intentConfiguracion);
    }//Fin del metodo mostrarPantallaDetalles


}//Fin de la class ActivityConfiguracion
