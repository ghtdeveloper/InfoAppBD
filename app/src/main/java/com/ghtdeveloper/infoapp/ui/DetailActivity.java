package com.ghtdeveloper.infoapp.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.ghtdeveloper.infoapp.R;
import com.ghtdeveloper.infoapp.adapter.AdapterEstudiantes;
import java.util.Objects;

/**
    En esta clase se define las informaciones
    detalalda de cada usuario asi como los temas
    segun la configuracion seleccionada por el usuario
 **/

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        asignarTema();
        //**********************************************************************
        setContentView(R.layout.activity_detail);
        //Toolbar
        Objects.requireNonNull(getSupportActionBar()).setTitle("Detalle Estudiante");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //Cast a la vistas del layout
        EditText txtNombreUsuario = findViewById(R.id.txtNombreEstudiante);
        txtNombreUsuario.setEnabled(false);
        EditText txtciudadNacimiento = findViewById(R.id.txtCiudadNacimiento);
        txtciudadNacimiento.setEnabled(false);
        EditText txtmatricula = findViewById(R.id.txtMatricula);
        txtmatricula.setEnabled(false);
        EditText txtDescripcion = findViewById(R.id.txtDescripcion);
        txtDescripcion.setEnabled(false);
        //Imagen
        ImageView imageView = findViewById(R.id.imgProfileUser);
        //Se recibe el intent con las informaciones de los estudiantes
        Intent intent = getIntent();
        //Nombre Usuario
        String nombreUsuario = intent.getStringExtra("nombreEstudiante");
        txtNombreUsuario.setText(nombreUsuario);
        //Ciudad Nacimiento
        txtciudadNacimiento.setText(intent.getStringExtra("ciudadEstudiante"));
        //Matricula
        txtmatricula.setText(intent.getStringExtra("matriculaEstudiante"));
        //Descripcion
        txtDescripcion.setText(intent.getStringExtra("direccionEstudiante"));

        if(AdapterEstudiantes.positionSend == 0)
        {
            imageView.setImageResource(R.drawable.img_edison_2);

        }

        if(AdapterEstudiantes.positionSend == 1)
        {
            imageView.setImageResource(R.drawable.darwin_img);

        }

        if(AdapterEstudiantes.positionSend == 2)
        {
            imageView.setImageResource(R.drawable.jesus_img);

        }

    }//Fin del metodo onCreate



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void asignarTema()
    {
        //setTheme(R.style.ThemePinkFusion);
        //Usuarios preferencias
        SharedPreferences sharedPreferences = this.getSharedPreferences("temas",
                Context.MODE_PRIVATE);
        String tema =sharedPreferences.getString("temaSeleccionado","no asignado");

        if(tema.equals("LightGreen"))
        {
            setTheme(R.style.ThemeLighGreen);
        }

        if(tema.equals("PinkFusion"))
        {
            setTheme(R.style.ThemePinkFusion);
        }

        if(tema.equals("DeepPuple"))
        {
            setTheme(R.style.ThemeDeepPuple);
        }

        if(tema.equals("dark"))
        {
            setTheme(R.style.Theme_AppCompat);//Black

        }

        if(tema.equals("light"))
        {
            setTheme(R.style.Theme_AppCompat_DayNight); //Ligh
        }

        //Si el tema no esta asignado se coloca uno por defecto
       if(tema.equals("no asignado"))
        {
            setTheme(R.style.ThemeDefault);
        }

    }//Fin de la funcion asignar tema

}//Fin de la class
