package edu.uni.comfenalco.tecnobanco.modelo;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    // Atributos privados para proteger los datos del usuario
    // Solo se pueden acceder y modificar a través de los métodos públicos (getters y setters)
    private String nombre;
    private String clave;
    private String identificacion;
    private RolUsuario rol;
    private List<CuentaAhorro> cuentas;

    public Usuario(){
        this.cuentas = new ArrayList<CuentaAhorro>(); 
    }

    // Métodos públicos para acceder y modificar los atributos privados (getters y setters)
    // Permiten obtener y cambiar el valor de un atributo de manera controlada
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getClave() {
        return clave;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setSaldo(double saldo) {
        //El Profesor dice : 
        //De momento dejaremos este metodo vacio para evitar errores de compilacion :)
        //En otra rama manejaremos el saldo de la cuenta
        //this.saldo = saldo;
    }

    public double getSaldo() {

        double saldo=0;
        for(CuentaAhorro cuenta : cuentas){
            saldo += cuenta.getSaldo();
        }
        return saldo;
    }

    public RolUsuario getRol() {
        return rol;
    }

    public void setRol(RolUsuario rol) {
        this.rol = rol;
    }

    public void agregarCuentaAhorro(CuentaAhorro cuenta) {
        cuentas.add(cuenta);
    }

    public List<CuentaAhorro> getCuentas() {
        return cuentas;
    }
}
