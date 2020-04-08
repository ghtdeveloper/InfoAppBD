package com.ghtdeveloper.infoapp.modelo;

/**
 * Clase definida para definir
 * las propiedas y metodos
 * relacionados a un estudiante
 */
public class Estudiante
{

    //Variables
    private int imagen;
    private  String nombre;

    private  String ciudadNacimiento;
    private  String matricula;
    private  String descripcion;


    //Se define el constructor de la class con parametros
    public Estudiante(int imagen, String nb,String ciudadN, String matr, String descc)
    {
        this.imagen = imagen;
        this.nombre = nb;
        this.ciudadNacimiento = ciudadN;
        this.matricula = matr;
        this.descripcion = descc;

    }//Fin del constructor


    //Getters and Setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCiudadNacimiento() {
        return ciudadNacimiento;
    }

    public void setCiudadNacimiento(String ciudadNacimiento) {
        this.ciudadNacimiento = ciudadNacimiento;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

}//Fin de la class Estudiante

