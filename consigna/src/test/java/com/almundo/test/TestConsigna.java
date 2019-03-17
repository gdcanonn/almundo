package com.almundo.test;

import com.almundo.consigna.Dispatcher;
import com.almundo.consigna.Llamada;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Clase donde se realizan las pruebas de diferentes escenarios para funcionalidad Consigna
 * @author German Cañon
 */
public class TestConsigna {
    
    // Sistema que atiende y administra las llamadas para la prueba pruebaDeDiezLlamadas()
    private static Dispatcher dispatcher1;
    
    // Sistema que atiende y administra las llamadas para la prueba pruebaDeDiezLlamadasConEncolamiento()
    private static Dispatcher dispatcher2;
    
    @BeforeClass
    public static void config() {
        System.out.println("Iniciando pruebas en clase TestConsigna!");
        dispatcher1 = new Dispatcher();
        dispatcher1.crearPersonasCallCenter();
        
        dispatcher2 = new Dispatcher();
        dispatcher2.crearPersonasCallCenter();
    }
    
    @AfterClass
    public static void done() {
        System.out.println("Finalizando pruebas en clase TestConsigna!");
    }
    
    @Before
    public void initTestMethod() {
        System.out.println("\nINICIO DE NUEVO METODO TEST!");
    }

    @After
    public void endTestMethod() {
        System.out.println("FIN DE METODO TEST!\n");
    }

    /**
     * Método que simulará 10 llamadas concurrentes, al existir 10 personas de Call Center, estas serán atendidas 
     * en su totalidad sin generar llamadas en cola
     */
    @Test
    public void pruebaDeDiezLlamadas() {
        // Se lanzan 10 llamadas simultaneamente para 10 personas que trabajan en el Call Center (7 operadores, 2 supervisores, 1 director)
        for (int i = 0; i < 10; i++) {
            Llamada llamada = new Llamada();
            llamada.setCodigoLlamada(i);
            llamada.setFechaRecepcionLlamada(new Date());
            llamada.setDispatcher(dispatcher1);
            dispatcher1.dispatchCall(llamada);
        }
        
        // Se realiza este while para que no se corte la prueba hasta que todas las llamadas terminen de atendersen
        while (!dispatcher1.validarLlamadasAtendidas()) {
        }
    }
    
    /**
     * Método que simulará 20 llamadas, de las cuales 10 quedarán en cola ya que solo existen 10 personas de Call Center.
     * Apenas se atienda una de las primeras llamadas se pasará a atender de inmediato la primera llamada que quedó en cola
     */
    @Test
    public void pruebaDeDiezLlamadasConEncolamiento() {
        // Se lanzan 20 llamadas simultaneamente para solamente 10 personas que trabajan en el Call Center (7 operadores, 2 supervisores, 1 director)
        for (int i = 0; i < 20; i++) {
            Llamada llamada = new Llamada();
            llamada.setCodigoLlamada(i);
            llamada.setFechaRecepcionLlamada(new Date());
            llamada.setDispatcher(dispatcher2);
            dispatcher2.dispatchCall(llamada);
        }
        
        // Se realiza este while para que no se corte la prueba hasta que todas las llamadas terminen de atendersen
        while (!dispatcher2.validarLlamadasAtendidas()) {
        }
    }
    
}
