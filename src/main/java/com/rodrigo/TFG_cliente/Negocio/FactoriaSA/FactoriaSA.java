package com.rodrigo.TFG_cliente.Negocio.FactoriaSA;

import com.rodrigo.TFG_cliente.Negocio.FactoriaSA.impl.FactoriaSAImpl;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Usuario.Serv_aplicacion.SA_Usuario;
/**
 * @Author Rodrigo de Miguel González
 * @Date 2017-2018
 * TFG - Atravesando las Capas de una Aplicación Empresarial: Demostrador Tecnológico J2EE
 */
public abstract class FactoriaSA {

    private static FactoriaSA ourInstance = new FactoriaSAImpl();

    public static FactoriaSA getInstance() {
        return ourInstance;
    }


    public abstract SA_Usuario crearSA_Usuario();

}
