package org.example;

/**
 * Clase para almacenar los datos de cada persona
 */

public class Persona {
    private String id, nombre, ciudad;
    private int edad;

    public Persona() {
        this.id = "";
        this.nombre = "";
        this.ciudad = "";
        this.edad = 0;
    }
    public Persona(String id, String nombre, String ciudad, int edad) {
        this.id = id;
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.edad = edad;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
}
