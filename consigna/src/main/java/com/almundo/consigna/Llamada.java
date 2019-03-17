package com.almundo.consigna;

import java.util.Calendar;
import java.util.Date;

/**
 * Clase que representa una llamada que sera atendidad por un operador en un momento dado
 * @author German Cañon
 */
public class Llamada implements Runnable {
    
    // Sistema de despacho de llamadas
    private Dispatcher dispatcher;
    
    // Código que hace única una llamada
    private Integer codigoLlamada;
    
    // Persona que está atendiendo la llamada (Operador, Supervisor, Director)
    private PersonaCallCenter personaAtiende;
    
    // Fecha en que recibe y atiende una llamada
    private Date fechaRecepcionLlamada;
    
    // Duración en segundos de la llamada
    private int duracionLlamada;
    
    /**
     * Método que realiza el trabajo de una llamada en particular
     */
    @Override
    public void run() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaRecepcionLlamada);
        calendar.add(Calendar.SECOND, duracionLlamada);
        Date fechaFinLlamada = calendar.getTime();
        
        Date fechaActual;
        do {
            fechaActual = new Date();
        } while (fechaActual.before(fechaFinLlamada));
        
        // Cuando la llamada termine deja disponible la persona que atendió
        personaAtiende.setLlamada(null);
        System.out.println("Fin llamada '" + codigoLlamada  + "' de " + duracionLlamada +  " seg: " + fechaActual + ", atendida por " + personaAtiende.toString());
        
        // Intenta atender una llamada en cola si es que hay
        dispatcher.atenderLlamadaEnCola();
    }

    public Dispatcher getDispatcher() {
        return dispatcher;
    }

    public void setDispatcher(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    public Integer getCodigoLlamada() {
        return codigoLlamada;
    }

    public void setCodigoLlamada(Integer codigoLlamada) {
        this.codigoLlamada = codigoLlamada;
    }
    
    public PersonaCallCenter getPersonaAtiende() {
        return personaAtiende;
    }

    public void setPersonaAtiende(PersonaCallCenter personaAtiende) {
        this.personaAtiende = personaAtiende;
    }

    public Date getFechaRecepcionLlamada() {
        return fechaRecepcionLlamada;
    }

    public void setFechaRecepcionLlamada(Date fechaRecepcionLlamada) {
        this.fechaRecepcionLlamada = fechaRecepcionLlamada;
    }

    public int getDuracionLlamada() {
        return duracionLlamada;
    }

    public void setDuracionLlamada(int duracionLlamada) {
        this.duracionLlamada = duracionLlamada;
    }

    @Override
    public String toString() {
        return "Llamada{" + "codigoLlamada=" + codigoLlamada + ", fechaRecepcionLlamada=" + fechaRecepcionLlamada + ", duracionLlamada=" + duracionLlamada + '}';
    }

}
