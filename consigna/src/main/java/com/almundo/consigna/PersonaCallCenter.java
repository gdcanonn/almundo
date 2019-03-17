package com.almundo.consigna;

/**
 * Clase que contiene los atributos comunes de una persona
 * @author German Cañon
 */
public class PersonaCallCenter {
    
    // Cédula que identifica la persona
    private String cedula;
    
    // Nombres completo de la persona
    private String nombres;
    
    // Apellidos completo de la persona
    private String apellidos;
    
    // Llamada que está atendiendo la persona en el momento (Si es diferente de null está disponible, sino está ocupado)
    private Llamada llamada;

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Llamada getLlamada() {
        return llamada;
    }

    public void setLlamada(Llamada llamada) {
        this.llamada = llamada;
    }

    @Override
    public String toString() {
        return "PersonaCallCenter{" + "nombres=" + nombres + ", apellidos=" + apellidos + '}';
    }

}
