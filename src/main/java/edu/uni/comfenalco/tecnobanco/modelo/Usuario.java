package edu.uni.comfenalco.tecnobanco.modelo;

public class Usuario {
    // Atributos privados para proteger los datos del usuario
    // Solo se pueden acceder y modificar a través de los métodos públicos (getters y setters)
    private String nombre;
    private String clave;
    private String identificacion;
    private double saldo;

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
        this.saldo = saldo;
    }

    public double getSaldo() {
        return saldo;
    }
}
