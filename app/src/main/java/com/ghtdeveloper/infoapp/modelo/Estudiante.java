package com.ghtdeveloper.infoapp.modelo;

/**
 * Clase definida para definir
 * las propiedas y metodos
 * relacionados a un estudiante
 */
public class Estudiante
{

    //Variables
    private int idEstudiante;
    private  String nombreCompleto;
    private  String ciudadNacimiento;
    private  String matricula;
    private  String descripcion;
    private byte[] imagen;

    //Constructor de la class
    public Estudiante()
    {
    }

    //Getters and Setters

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
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


    public int getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public byte[] getImagen() { return imagen; }

    public void setImagen(byte[] imagen) { this.imagen = imagen; }


}//Fin de la class Estudiante

