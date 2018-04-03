package com.rodrigo.TFG_cliente.Negocio.FactoriaSA.impl;

import com.rodrigo.TFG_cliente.Negocio.FactoriaSA.FactoriaSA;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Usuario.Serv_aplicacion.SA_Usuario;
import com.rodrigo.TFG_cliente.Negocio.Modulo_Usuario.Serv_aplicacion.impl.SA_UsuarioImpl;

public class FactoriaSAImpl extends FactoriaSA {
    @Override
    public SA_Usuario crearSA_Usuario(){
        return new SA_UsuarioImpl();
    }
}
