package com.rodrigo.TFG_cliente.presentacion.actions;

import com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Excepciones.EmpleadoException;
import com.rodrigo.TFG_cliente.presentacion.Proxy.Excepciones.ProxyException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

class NumerosActionTest {
    static final Logger log = LoggerFactory.getLogger(NumerosActionTest.class);


    private static NumerosAction na;

    @BeforeAll
    static void setUp() {


        try {
            na = new NumerosAction();

        }catch (ProxyException e){
            log.error("Se lanzo ex: " + e.getMessage());
            log.error(e.getStackTrace().toString());
            fail("Se lanzo ex: " + e.getMessage());
        } catch (EmpleadoException e) {
            e.printStackTrace();
        }


    }

    @ParameterizedTest(name = "#{index} con [{arguments}]")
    @ValueSource(strings = { "Rodrigo", "Juan", "" })
    void saludar(String nombre) {

        log.info("nombre = [" + nombre + "]");

        String str = "Hola " + nombre + ", un saludo desde el servidor CXF :)";

        na.setNombre(nombre);
        log.info("na.getNombre() = [" + na.getNombre() + "]");
        log.info("na.getNombre() = '" + na.getNombre() + "'");
        na.saludar();


        assertNotNull(na.getSaludo());

        assertEquals(na.getSaludo().toString(), str);


    }



   /* @Test
    void timeoutNotExceededWithResult() {
        // The following assertion succeeds, and returns the supplied object.
        String actualResult = assertTimeout(ofMinutes(2), () -> {
            return "a result";
        });
        assertEquals("a result", actualResult);
    }


    @Test
    void timeoutExceeded() {
        // The following assertion fails with an error message similar to:
        // execution exceeded timeout of 10 ms by 91 ms
        assertTimeout(ofMillis(10), () -> {
            // Simulate task that takes more than 10 ms.
            Thread.sleep(100);
        });
    }*/



}