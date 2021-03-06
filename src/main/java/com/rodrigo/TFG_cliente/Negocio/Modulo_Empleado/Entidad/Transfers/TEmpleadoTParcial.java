package com.rodrigo.TFG_cliente.Negocio.Modulo_Empleado.Entidad.Transfers;

/**
 * @Author Rodrigo de Miguel González
 * @Date 2017-2018
 * TFG - Atravesando las Capas de una Aplicación Empresarial: Demostrador Tecnológico J2EE
 */
public class TEmpleadoTParcial extends TEmpleado {


    private int horasJornada = 5;

    private int precioHora = 10;


    public TEmpleadoTParcial() {
    }

    public TEmpleadoTParcial(String nombre, String password,
                             Long departamento) {

        super(nombre, password,  departamento);
    }


    public TEmpleadoTParcial(String nombre, String email, String password,
                             Long departamento) {

        super(nombre, email, password,departamento);
    }

    public TEmpleadoTParcial(Long id, String nombre, String email, String password,
                              Long departamento) {

        super(id, nombre, email, password, departamento);
    }



    public TEmpleadoTParcial(Long id, String nombre, String email, String password,
                              Long departamento, int horasJornada, int precioHora) {

        super(id, nombre, email, password, departamento);

        this.horasJornada = horasJornada;
        this.precioHora = precioHora;
    }

    public TEmpleadoTParcial( String nombre, String email, String password,
                             Long departamento, int horasJornada, int precioHora) {

        super(nombre, email, password, departamento);

        this.horasJornada = horasJornada;
        this.precioHora = precioHora;
    }

    public TEmpleadoTParcial( String nombre, String password,
                              Long departamento, int horasJornada, int precioHora) {

        super(nombre, password, departamento);

        this.horasJornada = horasJornada;
        this.precioHora = precioHora;
    }



    public int getHorasJornada() {
        return horasJornada;
    }

    public void setHorasJornada(int horasJornada) {
        this.horasJornada = horasJornada;
    }

    public int getPrecioHora() {
        return precioHora;
    }

    public void setPrecioHora(int precioHora) {
        this.precioHora = precioHora;
    }


    @Override
    public String toString() {
        return "TEmpleadoTParcial{" +
                "horasJornada=" + horasJornada +
                ", precioHora=" + precioHora +
                "} " + super.toString();
    }
}
