package com.almundo.consigna;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Clase encargada de administrar las llamadas y distribuirlas
 * @author German Cañon
 */
public class Dispatcher {
    
    // Constantes para definir rangos minimo y maximo de una llamada en segundos
    public final static int RANGO_MINIMO = 5;
    public final static int RANGO_MAXIMO = 10;
    
    // Lista de personas (Operadores, Supervisores, Director) que atenderan llamadas
    private List<PersonaCallCenter> personas;
    
    // Lista de llamadas en espera que serán atendidas tan pronto una persona del Call Center esté disponible
    private List<Llamada> llamadasEnCola;
    
    /**
     * Método encargado de recibir las llamadas y buscar a quien asignarla
     * @param llamada La llamada entrante
     */
    public void dispatchCall(Llamada llamada) {
        PersonaCallCenter persona = buscarPersonaDisponible();
        if (persona != null && llamada.getPersonaAtiende() == null) {
            int duracionLlamada = getDuracionLlamada();
            llamada.setPersonaAtiende(persona);
            llamada.setDuracionLlamada(duracionLlamada);
            persona.setLlamada(llamada);
            
            System.out.println("Inicio llamada '" + llamada.getCodigoLlamada()  + "' de " + duracionLlamada +  " seg: " + llamada.getFechaRecepcionLlamada() + ", atendida por " + persona);
            
            // Se crea un Hilo para atender la llamada entrante
            Thread hiloLlamada = new Thread(llamada);
            hiloLlamada.start();
        } else { // Sino hay personas disponibles, la llamada pasa a espera (Se encola)            
            // Validación para asegura que no se ponga una misma llamada en cola mas de una vez
            Llamada llamadaBuscada = llamadasEnCola.stream().filter(llam -> llam.getCodigoLlamada().intValue() == llamada.getCodigoLlamada().intValue()).findFirst().orElse(null);
            if (llamadaBuscada == null) {
                llamadasEnCola.add(llamada);
                System.out.println("Se deja llamada '" + llamada.getCodigoLlamada()  + "' en Cola!: " + llamada + ", Total llamadas en cola: " + llamadasEnCola.size());
            }
        }
    }
    
    /**
     * Método de creación de personas que trabajan en el Call Center
     */
    public void crearPersonasCallCenter() {
        llamadasEnCola = new ArrayList<>();
        personas = new ArrayList<>();
        
        // Operador 1
        PersonaCallCenter p1 = new Operador();
        p1.setCedula("12432");
        p1.setNombres("Juan");
        p1.setApellidos("Lopez");
        personas.add(p1);
        
        // Operador 2
        PersonaCallCenter p2 = new Operador();
        p2.setCedula("124455432");
        p2.setNombres("Carlos");
        p2.setApellidos("Rojas");
        personas.add(p2);
        
        // Operador 3
        PersonaCallCenter p3 = new Operador();
        p3.setCedula("413123");
        p3.setNombres("Andres");
        p3.setApellidos("Suarez");
        personas.add(p3);
        
        // Operador 4
        PersonaCallCenter p4 = new Operador();
        p4.setCedula("645123");
        p4.setNombres("Franco");
        p4.setApellidos("Morales");
        personas.add(p4);
        
        // Operador 5
        PersonaCallCenter p5 = new Operador();
        p5.setCedula("868943");
        p5.setNombres("Laura");
        p5.setApellidos("Romano");
        personas.add(p5);
        
        // Operador 6
        PersonaCallCenter p6 = new Operador();
        p6.setCedula("6112443");
        p6.setNombres("Diana");
        p6.setApellidos("Robledo");
        personas.add(p6);
        
        // Operador 7
        PersonaCallCenter p7 = new Operador();
        p7.setCedula("64234543");
        p7.setNombres("Maria");
        p7.setApellidos("Morales");
        personas.add(p7);
        
        // Supervisor 1
        PersonaCallCenter s1 = new Supervisor();
        s1.setCedula("8542112");
        s1.setNombres("Francisco");
        s1.setApellidos("Sepulveda");
        personas.add(s1);
        
        // Supervisor 2
        PersonaCallCenter s2 = new Supervisor();
        s2.setCedula("733433");
        s2.setNombres("Luisa");
        s2.setApellidos("Mendez");
        personas.add(s2);
        
        // Director 1
        PersonaCallCenter d1 = new Director();
        d1.setCedula("415623");
        d1.setNombres("Victor");
        d1.setApellidos("Mercedez");
        personas.add(d1);
        
    }
    
    /**
     * Método para buscar una persona disponible para atender una llamada
     * @return una perosna del Call center para atender la llamada, sino retorna null
     */
    private PersonaCallCenter buscarPersonaDisponible() {
        Operador operador = buscarOperadorDisponible();
        if (operador != null) {
            return operador;
        } else { // Pasa a buscar entre los supervisores
            Supervisor supervisor = buscarSupervisorDisponible();
            if (supervisor != null) {
                return supervisor;
            } else { // Pasa a buscar entre los directores
                Director director = buscarDirectorDisponible();
                if (director != null) {
                    return director;
                }
            }
        }
        return null;
    }
    
    /**
     * Método para buscar un operador disponible entre todas las personas que atienden
     * @return un Operador disponible si lo encuentra, sino retorna null
     */
    private Operador buscarOperadorDisponible() {
        PersonaCallCenter persona = personas.stream().filter(p -> (p instanceof Operador && p.getLlamada() == null)).findFirst().orElse(null);
        if (persona != null) {
            return (Operador) persona;
        }
        return null;
    }
    
    /**
     * Método para buscar un supervisor disponible entre todas las personas que atienden
     * @return un Supervisor disponible si lo encuentra, sino retorna null
     */
    private Supervisor buscarSupervisorDisponible() {
        PersonaCallCenter persona = personas.stream().filter(p -> (p instanceof Supervisor && p.getLlamada() == null)).findFirst().orElse(null);
        if (persona != null) {
            return (Supervisor) persona;
        }
        return null;
    }
    
    /**
     * Método para buscar un director disponible entre todas las personas que atienden
     * @return un Director disponible si lo encuentra, sino retorna null
     */
    private Director buscarDirectorDisponible() {
        PersonaCallCenter persona = personas.stream().filter(p -> (p instanceof Director && p.getLlamada() == null)).findFirst().orElse(null);
        if (persona != null) {
            return (Director) persona;
        }
        return null;
    }
    
    /**
     * Método para obtener aleatoriamente la duración de una llamada
     * @return la duración de la llamada entre RANGO_MINIMO y RANGO_MAXIMO
     */
    private int getDuracionLlamada() {
        return (int) ((Math.random() * (RANGO_MAXIMO - RANGO_MINIMO)) + RANGO_MINIMO);
    }
    
    /**
     * Método que valida si las personas del call center estan todas libres
     * @return true si estan todas libres, sino retorna false
     */
    public boolean validarLlamadasAtendidas() {
        for (PersonaCallCenter persona : personas) {
            if (persona.getLlamada() != null) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Método para atender la primera llamada que quedo en cola
     */
    public void atenderLlamadaEnCola() {
        if (!llamadasEnCola.isEmpty()) {
            Llamada llamadaEnCola = llamadasEnCola.get(0);
            if (llamadaEnCola != null && llamadaEnCola.getPersonaAtiende() == null) { // Si existe una llamada en cola y nadie la ha atendido
                llamadaEnCola.setFechaRecepcionLlamada(new Date());
                dispatchCall(llamadaEnCola);
                llamadasEnCola.remove(llamadaEnCola);
            }
        }
    }

    public List<PersonaCallCenter> getPersonas() {
        return personas;
    }

    public void setPersonas(List<PersonaCallCenter> personas) {
        this.personas = personas;
    }

    public List<Llamada> getLlamadasEnCola() {
        return llamadasEnCola;
    }

    public void setLlamadasEnCola(List<Llamada> llamadasEnCola) {
        this.llamadasEnCola = llamadasEnCola;
    }
    
}
