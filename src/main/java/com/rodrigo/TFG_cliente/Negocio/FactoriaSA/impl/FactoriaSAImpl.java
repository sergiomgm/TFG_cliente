package com.rodrigo.TFG_cliente.Negocio.FactoriaSA.impl;

import com.rodrigo.TFG_cliente.Negocio.FactoriaSA.FactoriaSA;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Usuario.Serv_aplicacion.SA_Usuario;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Usuario.Serv_aplicacion.impl.SA_UsuarioImpl;
/**
 * @Author Rodrigo de Miguel González
 * @Date 2017-2018
 * TFG - Atravesando las Capas de una Aplicación Empresarial: Demostrador Tecnológico J2EE
 */
public class FactoriaSAImpl extends FactoriaSA {
    @Override
    public SA_Usuario crearSA_Usuario(){
        return new SA_UsuarioImpl();
    }
}
