package com.ghtdeveloper.infoapp.ui;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.transition.Fade;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.ghtdeveloper.infoapp.R;
import com.ghtdeveloper.infoapp.adapter.AdapterEstudiantes;
import com.ghtdeveloper.infoapp.modelo.Estudiante;
import java.util.ArrayList;
import java.util.List;

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

    //Lista con las informaciones de los estudiantes
    List<Estudiante> items;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Se inicializa  la lista
        items = new ArrayList<>();
        //Se agregan los elementos a la lista de los estudiantes
        items.add(new Estudiante(
                R.drawable.img_edison_2,"Edison Martinez",
                "San Cristobal","100265074","The Walking Dead es una serie" +
                " de televisión estadounidense de horror post-apocalíptico para AMC basada" +
                " en la serie de cómics de Robert Kirkman, Tony Moore y Charlie Adlard. " +
                "La serie presenta un gran elenco como sobrevivientes de un apocalipsis zombi," +
                " tratando de mantenerse con vida bajo la amenaza casi constante de ataques " +
                "de los zombis sin conciencia, coloquialmente conocidos como «caminantes». " +
                "Sin embargo, con la caída de la humanidad, estos sobrevivientes también " +
                "enfrentan conflictos con otros sobrevivientes que han formado grupos " +
                "y comunidades con sus propios conjuntos de leyes y morales, lo que a " +
                "menudo conduce a conflictos hostiles entre las comunidades humanas." +
                " Andrew Lincoln interpretó al personaje principal de la serie, " +
                "Rick Grimes, hasta su partida durante la novena temporada. Otros " +
                "miembros del elenco de larga data han incluido a Norman Reedus, " +
                "Steven Yeun, Chandler Riggs, Melissa McBride, Lauren Cohan," +
                "Danai Gurira, Josh McDermitt y Christian Serratos.La serie se emite" +
                " exclusivamente en AMC en los Estados Unidos e internacionalmente a " +
                "través de Fox Networks Group. La serie se estrenó el 31 de octubre de 2010. " +
                "La décima temporada se estrenó el 6 de octubre de 2019 y se ha renovado " +
                "por undécima temporada. AMC declaró su intención de continuar desarrollando " +
                "más la serie y los medios relacionados. Una serie derivada, " +
                "Fear the Walking Dead," +
                " se estrenó el 23 de agosto de 2015 y se renovó por su sexta temporada. " +
                "La segunda serie derivada, The Walking Dead: World Beyond, se estrenará en " +
                "2020. AMC ha anunciado planes para que tres películas sigan la historia de " +
                "Rick después de la partida de Lincoln."));
        items.add(new Estudiante(R.drawable.darwin_img,"Darvin Villavicencio",
                "Higüey ","100338494","Iron Man (también conocido en español " +
                "como El Hombre de Hierro) es un superhéroe ficticio que aparece en los " +
                "cómics estadounidenses publicados por Marvel Comics. El personaje fue" +
                " cocreado por el escritor y editor Stan Lee, desarrollado por el " +
                "guionista Larry Lieber y diseñado por los artistas Don Heck y Jack Kirby. " +
                "Hizo su primera aparición en Tales of Suspense # 39 (marzo de 1963), " +
                "y recibió su propio título en Iron Man #1 (mayo de 1968).Anthony Edward " +
                "Stark, más conocido como Tony Stark, un multimillonario magnate empresarial " +
                "estadounidense, playboy e ingenioso científico, sufre una grave lesión en el" +
                " pecho durante un secuestro. Cuando sus captores intentan forzarlo a construir " +
                "un arma de destrucción masiva crea, en cambio, una armadura poderosa " +
                "para salvar " +
                "su vida y escapar del cautiverio. Más tarde, Stark desarrolla su traje, " +
                "agregando " +
                "armas y otros dispositivos tecnológicos que diseñó a través de su compañía, " +
                "Industrias Stark. Él usa el traje y las versiones sucesivas para proteger " +
                "al mundo como Iron Man. Aunque al principio ocultó su verdadera identidad, " +
                "Stark finalmente declaró que era, de hecho, Iron Man en un anuncio público." +
                "Inicialmente, Iron Man no era más que un concepto de Stan Lee con el fin de" +
                " explorar los temas de la Guerra Fría, particularmente el papel de la" +
                " tecnología " +
                "y la industria estadounidenses en la lucha contra el comunismo.2 Las re" +
                " imaginaciones posteriores de Iron Man han pasado de los motivos de la " +
                "Guerra Fría a" +
                " los asuntos contemporáneos de la época,2 como lo es el terrorismo, la" +
                " corrupción y la delincuencia en general."));
        items.add(new Estudiante(R.drawable.jesus_img,"Jesus Trinidad",
                "Santo Domingo","GG3487","Superman (cuyo nombre kryptoniano " +
                "es Kal-El y su nombre terrestre es Clark Kent) es un personaje ficticio, " +
                "un superhéroe de los cómics que aparece en las publicaciones " +
                "de DC Comics.1234Creado por el escritor estadounidense Jerry Siegel y " +
                "el artista canadiense Joe Shuster en 1933 cuando ambos se encontraban " +
                "viviendo en Cleveland, Ohio; lo vendieron a Detective Comics, Inc. " +
                "en 1938 por 130  dólares estadounidenses5 y la primera aventura del " +
                "personaje fue publicada en Action Comics #1 (junio de 1938), " +
                "para luego aparecer en varios seriales de radio, programas " +
                "de televisión, películas, tiras periódicas y videojuegos. " +
                "Con el éxito de sus aventuras, Superman ayudó a crear el " +
                "género del superhéroe y estableció su primacía dentro del " +
                "cómic estadounidense.1 La apariencia del personaje es distintiva " +
                "e icónica: un traje azul y rojo, con una capa y un escudo de “S” " +
                "estilizado en su pecho,678escudo que se ha convertido en un símbolo " +
                "del personaje en todo tipo de medios de comunicación.9La historia " +
                "original de Superman relata que nació con el nombre de Kal-El en el " +
                "planeta Krypton; su padre, el científico Jor-El, y su madre Lara Lor-Van, " +
                "lo enviaron en una nave espacial con destino a la Tierra cuando era un " +
                "niño, momentos antes de la destrucción de su planeta. Fue descubierto y " +
                "adoptado por Jonathan Kent y Martha Kent, una pareja de granjeros de" +
                " Smallville, Kansas, que lo criaron con el nombre de Clark Kent y le " +
                "inculcaron un estricto código moral. El joven Kent comenzó a mostrar " +
                "habilidades superhumanas, las mismas que al llegar a su madurez" +
                " decidiría usar para el beneficio de la humanidad."));
        //Cast a la vistas
        recyclerView = findViewById(R.id.recyclerview);
        //Se inicializa el adaptador se pasa como parametro la lista
        AdapterEstudiantes adapterEstudiantes = new AdapterEstudiantes(items);
        //Se asigna el adaptador a la lista
        recyclerView.setAdapter(adapterEstudiantes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //Se asigna el metodo setOnClick  para poder redireccionar a la interfaz del detalle
        adapterEstudiantes.setOnClick(this);

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
        //Animacion para la entrada a la actividad
        Fade fade = new Fade();
        fade.setDuration(200);
        getWindow().setEnterTransition(fade);
        intentMainActivity = new Intent(this, DetailActivity.class);
        /*
            Se procede a enviar los datos a la siguiente actividad
         */
        //Se envia el nombre
        intentMainActivity.putExtra("nombreEstudiante",
                items.get(AdapterEstudiantes.positionSend).getNombre());

        //Se envia la ciudad
        intentMainActivity.putExtra("ciudadEstudiante",
                items.get(AdapterEstudiantes.positionSend).getCiudadNacimiento());

        //Se envia la Matricula
        intentMainActivity.putExtra("matriculaEstudiante",
                items.get(AdapterEstudiantes.positionSend).getMatricula());

        //Se envia la descripcion
        intentMainActivity.putExtra("direccionEstudiante",
                items.get(AdapterEstudiantes.positionSend).getDescripcion());

        //Imagen
        intentMainActivity.putExtra("imagenEstudiante",
                items.get(AdapterEstudiantes.positionSend).getImagen());

        startActivity(intentMainActivity,
                ActivityOptions.makeSceneTransitionAnimation(this).toBundle());

    }//Fin del metodo onItemClick


}//Fin de la class MainActivity
