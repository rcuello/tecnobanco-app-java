package edu.uni.comfenalco.tecnobanco.modelo;

/**
 * Clase que representa una cuenta de ahorro.
 */
public class CuentaAhorro {
    private String numeroCuenta; // Identificador único de la cuenta
    private double saldo; // Saldo actual de la cuenta

    /**
     * Constructor de la clase CuentaAhorro.
     * 
     * @param numeroCuenta El número de la cuenta.
     * @param saldoInicial El saldo inicial de la cuenta.
     */
    public CuentaAhorro(String numeroCuenta, double saldoInicial) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldoInicial;
    }

    // Getters y setters
    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    /**
     * Deposita una cantidad en la cuenta.
     * 
     * @param monto El monto a depositar.
     */
    public void depositar(double monto) {
        if (monto > 0) {
            saldo += monto;
        } else {
            throw new IllegalArgumentException("El monto a depositar debe ser mayor que 0.");
        }
    }

    /**
     * Retira una cantidad de la cuenta.
     * 
     * @param monto El monto a retirar.
     */
    public void retirar(double monto) {
        if (monto > 0 && monto <= saldo) {
            saldo -= monto;
        } else {
            throw new IllegalArgumentException("Monto inválido o saldo insuficiente.");
        }
    }

    @Override
    public String toString() {
        return "CuentaAhorro{" +
                "numeroCuenta='" + numeroCuenta + '\'' +
                ", saldo=" + saldo +
                '}';
    }

}
