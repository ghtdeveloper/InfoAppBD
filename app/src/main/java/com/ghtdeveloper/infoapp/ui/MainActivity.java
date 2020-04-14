package com.ghtdeveloper.infoapp.ui;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.ghtdeveloper.infoapp.R;
import com.ghtdeveloper.infoapp.adapter.AdapterEstudiantes;
import com.ghtdeveloper.infoapp.modelo.Estudiante;
import com.ghtdeveloper.infoapp.modelo.OpenHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import java.util.ArrayList;
import java.util.Objects;

/**
    Clase principal en donde el usuario
    puede visualizar el recyclerview con
    la imagen y el nombre de los estudiantes
* */

public class MainActivity extends AppCompatActivity implements
        AdapterEstudiantes.onItemClickDetalles
{

    //Se declaran los objetos a utilizar
    Intent intentMainActivity;

    //Vistas
    RecyclerView recyclerView;

    ArrayList<String> listaDatosBD;

    private SQLiteDatabase db;

    //Objeto Estudiante
    private Estudiante objEstudiante;

    //Adapter
    AdapterEstudiantes adapterEstudiantes;

    //Vistas
    TextInputEditText txtNombreCompleto;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Base de datos
         OpenHelper dbHelper = new OpenHelper(this);
         db = dbHelper.getWritableDatabase();
         db = dbHelper.getReadableDatabase();
         //ArrayList
        listaDatosBD = new ArrayList<>();
         //Modelo
        objEstudiante = new Estudiante();
        //Cast a la vistas
        recyclerView = findViewById(R.id.recyclerview);
        FloatingActionButton floatButtonAddUsuario = findViewById(R.id.btnAddUsuario);
        //Se inicializa el adaptador se pasa como parametro la lista
        getListaEstudiantes(); //Listado estudiantes
        adapterEstudiantes = new AdapterEstudiantes(listaDatosBD);
        //Se asigna el adaptador a la lista
        recyclerView.setAdapter(adapterEstudiantes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //recyclerView.setOnClickListener(on);
        //Se asigna el metodo setOnClick  para poder redireccionar a la interfaz del detalle
        adapterEstudiantes.setOnClick(this);
        //Listener button agregar usuario
        floatButtonAddUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Se invocoara el dialogo
                dialogoRegistrarUsuario();
            }
        });

    }//Fin del metodo onCreate


    /*
     Se define el metodo para navegar hacia
     la pantalal de configuracion
     */
    public void showConfiguracion(View view)
    {
        intentMainActivity = new Intent(this,ActivityConfiguracion.class);
        startActivity(intentMainActivity);

    } //Fin del metodo showConfiguracion


   /*
    Se le realiza on Onverride al metodo de onItemClick definido en el adapter
    en este se envian los datos a la actividad del detalle.
    */

    @Override
    public void onItemClick(int position)
    {
        getInformacionUsuarios();
        intentMainActivity = new Intent(this,DetailActivity.class);
        //Se pasan por medios de intents las informaciones obtenidas en la base de datos

        //Nombre
        intentMainActivity.putExtra("nombreEsudiante",objEstudiante.getNombreCompleto());

        //Ciudad Nacimiento
        intentMainActivity.putExtra("lugarNacimiento",objEstudiante.getCiudadNacimiento());

        //Matricula
        intentMainActivity.putExtra("matricula",objEstudiante.getMatricula());

        //Descripcion

        intentMainActivity.putExtra("descripcion",objEstudiante.getDescripcion());

        //ID estudiante para modifcacion y eliminacion
        intentMainActivity.putExtra("idEstudiante",String.valueOf(getIDEstudiante()));

        startActivity(intentMainActivity);


    }//Fin del metodo onItemClick

    /*
        Dialogo para registrar los usuarios solo se utilizara
        el primer nombre y primer apellido para un registro
        rapido
     */
    public void dialogoRegistrarUsuario()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        @SuppressLint("InflateParams") View viewDialog = inflater.inflate
                (R.layout.dialog_usuarios_fast, null);
        builder.setView(viewDialog);
        //Cast a la vistas
        txtNombreCompleto = viewDialog.findViewById(R.id.txtNombreUsuario);
        builder.setTitle("Registro Estudiantes");
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                if(validarCamposVacios() > 0)
                {
                    Toast.makeText(getApplicationContext(), "No puede dejar campos vacios",
                            Toast.LENGTH_SHORT).show();
                }

                else
                {
                    objEstudiante.setNombreCompleto(Objects.requireNonNull
                            (txtNombreCompleto.getText()).toString().trim());
                    if(db!= null)
                    {
                        ContentValues cv = new ContentValues();
                        cv.put("nombreCompleto", objEstudiante.getNombreCompleto());
                        cv.put("matricula","N/A");
                        cv.put("descripcion","N/A");
                        cv.put("lugarNacimiento","N/A");
                        db.insertOrThrow("Estudiantes",null,cv);
                        Toast.makeText(getApplicationContext(),
                                "Estudiante Agregado de forma exitosa",
                                Toast.LENGTH_SHORT).show();
                        //Realizo un refresh a la actividad principal
                        intentMainActivity = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intentMainActivity);
                    }
                }//Fin del else

            }//Fin del metodo onClick
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


            }
        });
        AlertDialog dialog = builder.create();
        dialog.getLayoutInflater();
        dialog.show();
    }//Fin del metodo dialogoRegistrarUsuario


    /*
        Metodo definido para retornar el listado
        de todos los estudiantes registrados en la app

     */

    private void getListaEstudiantes()
    {
        //listDatos.clear();
           Cursor cursor = db.rawQuery("select es.nombreCompleto from Estudiantes as es"
                   , null);
           if (cursor.moveToFirst())
           {
               do {

                  listaDatosBD.add(cursor.getString(0));
               } while (cursor.moveToNext());
           }
           cursor.close();
    }//Fin del metodo listadoEstudiante

    /*
        Metodo definido para verificar
        si hay campos vacios al momento
        de intentar registrar un estudiante
     */
    public int validarCamposVacios()
    {
        int contador = 0;
        if(Objects.requireNonNull(txtNombreCompleto.getText()).toString().isEmpty())
        {
            contador = contador + 1;
        }
        return  contador;
    }//Fin del metodo validarCamposVacios


    /*
        Metodo definido para obtener el id del estudiante
     */
    public int getIDEstudiante()
    {
        String filtro = String.valueOf(AdapterEstudiantes.nombreSend);

        //Campos que me interesa obtener
        String[] campos = new String[] {"idEstudiante"};
        //Filtro por el cual voy a buscar
        String[] arg = new String[] {filtro};
        //Query
        Cursor fila = db.query("Estudiantes",campos,"nombreCompleto=?",
                arg,null,null,null);

        if(fila.moveToFirst())
        {
            do {
                objEstudiante.setIdEstudiante(Integer.parseInt(fila.getString(0)));
            }while (fila.moveToNext());
        }
        fila.close();
        return  objEstudiante.getIdEstudiante();
    }//Fin del metodo getIDEstudiante


    /*
        Metodo definido para obtener las informaciones
        de los usuarios para ser enviadas
        a la actividad del detalle

     */
    public void getInformacionUsuarios()
    {
        String filtro = String.valueOf(getIDEstudiante());
        //Campos que me interesan obtener
        String[] campos = new String[] {"nombreCompleto","matricula","descripcion",
                "lugarNacimiento"};
        //Filtro a aplicar
        String[] arg = new String[] {filtro};
        //Query
        Cursor fila = db.query("Estudiantes",campos,"idEstudiante=?",
                arg,null,null,null);
        if(fila.moveToFirst())
        {
            do {
                objEstudiante.setNombreCompleto(fila.getString(0));//Nombre completo
                objEstudiante.setMatricula(fila.getString(1));//Matricula
                objEstudiante.setDescripcion(fila.getString(2));//Descripcion
                objEstudiante.setCiudadNacimiento(fila.getString(3));//Lugar nacimiento
            }while (fila.moveToNext());
        }
        fila.close();
    }//Fin del metodo getInformacionUsuarios




}//Fin de la class MainActivity
