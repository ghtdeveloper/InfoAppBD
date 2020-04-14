package com.ghtdeveloper.infoapp.ui;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.ghtdeveloper.infoapp.R;
import com.ghtdeveloper.infoapp.modelo.OpenHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

/**
    En esta clase se define las informaciones
    detalalda de cada usuario asi como los temas
    segun la configuracion seleccionada por el usuario
 **/

public class DetailActivity extends AppCompatActivity
{


    //Vistas
    private TextInputEditText txtNombreUsuario;
    private TextInputEditText txtciudadNacimiento;
    private TextInputEditText txtmatricula;
    private TextInputEditText txtDescripcion;

    private TextInputLayout textInputLayoutNombre;
    private TextInputLayout textInputLayoutCiudadNacimiento;
    private TextInputLayout textInputLayoutMatricula;
    private TextInputLayout textInputLayoutDescripcion;

    //Objetos
    Intent intActivityDetail;

    //BD
    private SQLiteDatabase db;

    //Variables
    private int idUsuario;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        asignarTema();
        //**********************************************************************
        setContentView(R.layout.activity_detail);
        //Toolbar
        Objects.requireNonNull(getSupportActionBar()).setTitle("Detalle Estudiante");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //BD
        OpenHelper dbHelper = new OpenHelper(this);
        db = dbHelper.getWritableDatabase();
        db = dbHelper.getReadableDatabase();
        //Cast a la vistas del layout
        txtNombreUsuario = findViewById(R.id.txtNombreEstudiante);
        txtciudadNacimiento = findViewById(R.id.txtCiudadNacimiento);
        txtmatricula = findViewById(R.id.txtMatricula);
        txtDescripcion = findViewById(R.id.txtDescripcion);

        textInputLayoutNombre = findViewById(R.id.textInputLayoutNombre);
        textInputLayoutCiudadNacimiento = findViewById(R.id.textInputLayoutCity);
        textInputLayoutMatricula =  findViewById(R.id.textInputLayoutMatricula);
        textInputLayoutDescripcion = findViewById(R.id.textInputLayoutDescripcion);

        //Imagen
        ImageView imageView = findViewById(R.id.imgProfileUser);
        imageView.setImageResource(R.drawable.img_perfil_unknow);
        //Se recibe el intent con las informaciones de los estudiantes
        Intent intent = getIntent();
        //Nombre Usuario
        txtNombreUsuario.setText(intent.getStringExtra("nombreEsudiante"));
        //Lugar Nacimiento
        txtciudadNacimiento.setText(intent.getStringExtra("lugarNacimiento"));
        //Matricula
        txtmatricula.setText(intent.getStringExtra("matricula"));
        //Descripcion
        txtDescripcion.setText(intent.getStringExtra("descripcion"));
        //Id Usuario
        idUsuario = Integer.parseInt(Objects.requireNonNull(intent.getStringExtra
                ("idEstudiante")));
        //Por defecto los campos estan desactivados
        asignarEstatusCampos(true);

        //Bottom bar
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item)
                    {
                        switch (item.getItemId())
                        {
                            case R.id.menu_editar:
                                asignarEstatusCampos(false);
                                break;

                            case R.id.menu_guardar:
                               actualizarEstudiante();
                                break;

                            case R.id.menu_eliminar:
                                eliminarUsuario();
                                break;
                        }//Fin del switch
                        return true;
                    }
                });
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


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void asignarEstatusCampos(Boolean flag)
    {

        if(flag)
        {
            //enable
            txtNombreUsuario.setEnabled(false);
            txtciudadNacimiento.setEnabled(false);
            txtmatricula.setEnabled(false);
            txtDescripcion.setEnabled(false);

            //Focusable
            txtNombreUsuario.setFocusable(false);
            txtciudadNacimiento.setFocusable(false);
            txtmatricula.setFocusable(false);
            txtDescripcion.setFocusable(false);

            //clickable
            txtNombreUsuario.setClickable(false);
            txtciudadNacimiento.setClickable(false);
            txtmatricula.setClickable(false);
            txtDescripcion.setClickable(false);
        }

        if(!flag)
        {
            //Nombre
            txtNombreUsuario.setEnabled(true);
            txtNombreUsuario.setFocusable(true);
            txtNombreUsuario.setFocusableInTouchMode(true);
            txtNombreUsuario.setClickable(true);
            txtNombreUsuario.setTextColor(Color.RED);
            textInputLayoutNombre.setFocusable(true);
            textInputLayoutNombre.setFocusableInTouchMode(true);

            //Ciudad nacimiento
            txtciudadNacimiento.setEnabled(true);
            txtciudadNacimiento.setFocusable(true);
            txtciudadNacimiento.setFocusableInTouchMode(true);
            txtciudadNacimiento.setClickable(true);
            txtciudadNacimiento.setTextColor(Color.RED);
            textInputLayoutCiudadNacimiento.setFocusable(true);
            textInputLayoutCiudadNacimiento.setFocusableInTouchMode(true);

            //Matricula
            txtmatricula.setEnabled(true);
            txtmatricula.setFocusable(true);
            txtmatricula.setFocusableInTouchMode(true);
            txtmatricula.setClickable(true);
            txtmatricula.setTextColor(Color.RED);
            textInputLayoutMatricula.setFocusable(true);
            textInputLayoutMatricula.setFocusableInTouchMode(true);

            //Descripcion
            txtDescripcion.setEnabled(true);
            txtDescripcion.setFocusable(true);
            txtDescripcion.setFocusableInTouchMode(true);
            txtDescripcion.setClickable(true);
            txtDescripcion.setTextColor(Color.RED);
            textInputLayoutDescripcion.setFocusable(true);
            textInputLayoutDescripcion.setFocusableInTouchMode(true);
        }
    }//fin de asignarEstatusCampos

    public void  showPantallPrincipal()
    {
        intActivityDetail = new Intent(this, MainActivity.class);
        startActivity(intActivityDetail);
    }

    /*
        Metodo definido para actualizar los datos de un estudiante
     */

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void actualizarEstudiante() {
        String valorObtenido =  String.valueOf(idUsuario);

        //Filtro
        String[] arg = new String[] {valorObtenido};
        if(validarCamposVacios() >0)
        {
            Toast.makeText(this,"Todos los campos son necesarios!",
                    Toast.LENGTH_SHORT).show();
        }
        else
        {
            if(db != null)
            {
                ContentValues values = new ContentValues();
                values.put("nombreCompleto",
                        Objects.requireNonNull(txtNombreUsuario.getText()).toString());
                values.put("lugarNacimiento",
                        Objects.requireNonNull(txtciudadNacimiento.getText()).toString());
                values.put("matricula", Objects.requireNonNull(txtmatricula.getText()).toString());
                values.put("descripcion",
                        Objects.requireNonNull(txtDescripcion.getText()).toString());
                db.update("Estudiantes",values,"idEstudiante=?",
                        arg);
                try {
                    Thread.sleep(1000);
                    Toast.makeText(this,"Los datos fueron modificados exitosamente",
                            Toast.LENGTH_SHORT).show();
                    //desactivo los campos
                    asignarEstatusCampos(true);
                    //Avanzo a la actividad principial por ahora
                    showPantallPrincipal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }

    }//Fin del metodo actualizarEstudiante

    /*
        Nueva vez se validan los campos que no esten vacios
        ninguno de ellos
     */
    public int validarCamposVacios()
    {
        int contador = 0;
        if(Objects.requireNonNull(txtNombreUsuario.getText()).toString().trim().isEmpty())
        {
            contador = contador + 1;
        }
        if(Objects.requireNonNull(txtciudadNacimiento.getText()).toString().trim().isEmpty())
        {
            contador = contador + 1;
        }
        if(Objects.requireNonNull(txtmatricula.getText()).toString().trim().isEmpty())
        {
            contador = contador + 1;
        }
        if(Objects.requireNonNull(txtDescripcion.getText()).toString().trim().isEmpty())
        {
            contador = contador + 1;
        }

        return  contador;
    }//Fin del metodo validarCamposVacios

    /*
        Metodo definido para borrar los usuarios de la
        Base de datos
     */
    public void eliminarUsuario()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("¿Deseas eliminar este estudiante?");
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                //Al aceptar el estudiante dice see yout later my friend
               String[] arg = new String[] {String.valueOf(idUsuario)};
               if(db != null)
               {
                   db.delete("Estudiantes","idEstudiante=?",arg);
               }

                try {
                    Thread.sleep(1000);
                    Toast.makeText(getApplicationContext(),"Estudiante eliminado exitosamente",
                            Toast.LENGTH_SHORT).show();
                    showPantallPrincipal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {

                //Si le doy a cancelar no hago nada


            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }//Fin del metodo eliminarUsuario

}//Fin de la class
