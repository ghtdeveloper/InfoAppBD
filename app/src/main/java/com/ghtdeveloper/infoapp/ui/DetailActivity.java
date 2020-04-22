package com.ghtdeveloper.infoapp.ui;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.ghtdeveloper.infoapp.R;
import com.ghtdeveloper.infoapp.modelo.OpenHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import java.io.ByteArrayOutputStream;
import java.util.Objects;

/**
    En esta clase se define las informaciones
    detalalda de cada usuario asi como los temas
    segun la configuracion seleccionada por el usuario
 **/

public class DetailActivity extends AppCompatActivity implements  PopupMenu.OnMenuItemClickListener
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
    //Button
    private FloatingActionButton btnEditPicture;

    //ImageView
    private ImageView imgProfileEstudiante;

    //Objetos
    Intent intActivityDetail;

    //BD
    private SQLiteDatabase db;

    //Variables
    private int idUsuario;

    //Camara
    static  final  int request_IMAGE_CAPTURE = 1;
    //String rutaPhoto;

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
        btnEditPicture = findViewById(R.id.btnEditPicture);
        textInputLayoutNombre = findViewById(R.id.textInputLayoutNombre);
        textInputLayoutCiudadNacimiento = findViewById(R.id.textInputLayoutCity);
        textInputLayoutMatricula =  findViewById(R.id.textInputLayoutMatricula);
        textInputLayoutDescripcion = findViewById(R.id.textInputLayoutDescripcion);

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

        imgProfileEstudiante = findViewById(R.id.imgProfileUser);
        getImagenProfile(idUsuario);
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
        //Usuarios preferencias
        SharedPreferences sharedPreferences = this.getSharedPreferences("temas",
                Context.MODE_PRIVATE);
        String tema =sharedPreferences.getString("temaSeleccionado","no asignado");

        if(tema.equals("LightGreen")) { setTheme(R.style.ThemeLighGreen); }

        if(tema.equals("PinkFusion")) { setTheme(R.style.ThemePinkFusion); }

        if(tema.equals("DeepPuple")) { setTheme(R.style.ThemeDeepPuple); }

        if(tema.equals("dark")) { setTheme(R.style.Theme_AppCompat); }

        if(tema.equals("light")) { setTheme(R.style.Theme_AppCompat_DayNight); }

        //Si el tema no esta asignado se coloca uno por defecto
       if(tema.equals("no asignado")) { setTheme(R.style.ThemeDefault); }
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

            btnEditPicture.setVisibility(View.INVISIBLE);
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

            btnEditPicture.setVisibility(View.VISIBLE);
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
                //values.put("imagen",);
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

    /*
        Metodo definido para mostrar el Pop up del menu

     */
    public void showMenuPopup(View view)
    {
        PopupMenu popupMenu = new PopupMenu(this,view);
        MenuInflater menuInflater =  popupMenu.getMenuInflater();
        popupMenu.setOnMenuItemClickListener(this);
        menuInflater.inflate(R.menu.menu_imagenes, popupMenu.getMenu());
        popupMenu.show();
    }//Fin del menu showMenuPopup


    @Override
    public boolean onMenuItemClick(MenuItem item)
    {
     switch (item.getItemId())
     {
         case  R.id.menuTomarFoto:
             tomarImagenIntent();
             return  true;

         case R.id.menuGaleria:
             return  true;

             default:
             return false;
     }
    }//Fin del metodo onMenuItemClick

    //Camera Acces

    private void tomarImagenIntent()
    {
        Intent intentPicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(intentPicture.resolveActivity(getPackageManager())!= null)
        {
                startActivityForResult(intentPicture,request_IMAGE_CAPTURE);
        }
    }//Fin del metodo tomarImagenIntent


    @Override
    protected  void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == request_IMAGE_CAPTURE && resultCode == RESULT_OK)
        {
             Bundle extras = data.getExtras();
             assert extras != null;
            //Bitmap
             Bitmap imageBitmap = (Bitmap) extras.get("data");
             imgProfileEstudiante.setImageBitmap(imageBitmap);

             //Se comprime para luego ser insertada en la BD
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            assert imageBitmap != null;
            imageBitmap.compress(Bitmap.CompressFormat.JPEG,90,stream);

            //Filtro
            String valorObtenido =  String.valueOf(idUsuario);
            String[] arg = new String[] {valorObtenido};
             if(db != null)
             {
                 ContentValues values = new ContentValues();
                 values.put("imagen", stream.toByteArray());
                 db.update("Estudiantes",values,"idEstudiante=?",
                         arg);
             }
        }//Fin if Mian
    }//Fin del metodo onActivityResult

    /*
        Este metodo retorna la imagen
        actual que tiene configurado
        el estudiante en la base de datos

     */
    public void getImagenProfile(int idEstudiante)
    {
        String filtro = String.valueOf(idEstudiante);
        //Campos que me interesan obtener
        String[] campos = new String[] {"imagen"};
        //Filtro a aplicar
        String[] arg = new String[] {filtro};

        byte[] imagen;
        //Query
        Cursor fila = db.query("Estudiantes",campos,"idEstudiante=?",
                arg,null,null,null);
        if(fila.moveToFirst())
        {
            do {
                imagen = fila.getBlob(0);
                Bitmap bmp = BitmapFactory.decodeByteArray(imagen,0,imagen.length);
                imgProfileEstudiante.setImageBitmap(bmp);
            }while (fila.moveToNext());
        }
        fila.close();
    }
}//Fin de la class
